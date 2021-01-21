package game;

import database.CostChange;
import database.InitialData;
import database.MonthlyUpdate;
import entities.Consumer;
import entities.Contract;
import entities.Distributor;
import fileio.Input;

import java.util.ArrayList;

public final class Simulation {
    private static Simulation instance = null;

    private static final double RATIO = 0.2;
    private static final double INTEREST = 1.2;

    private Input input;

    private Simulation() { }

    /**
     * Get instance of Singleton class Simulation
     * @return the instance
     */
    public static Simulation getInstance() {
        if (instance == null) {
            instance = new Simulation();
        }
        return instance;
    }

    public void setInput(final Input input) {
        this.input = input;
    }

    private int calculateCost(final Distributor distributor) {

        int profit, contractsCount;

        // Calculate price for distributor by formulas
        profit = (int) Math.round(Math.floor(RATIO * distributor.getProductionCost()));
        contractsCount = distributor.getContracts().size() > 0
                ? distributor.getContracts().size() : 1;

        return (int) Math.round(Math.floor(
                (float) distributor.getInfrastructureCost() / contractsCount)
                + distributor.getProductionCost()
                + profit);

    }

    private Distributor getBestDeal(final Consumer consumer, final ArrayList<Integer> prices) {

        if (input.getInitialData().getDistributors() == null) {
            return  null;
        }

        boolean ok = true;
        int minimum = -1;
        int index, bestIndex = 0;

        // Search for an active contract
        for (Distributor distributor : input.getInitialData().getDistributors()) {
            if (distributor.isBankrupt()) {
                continue;
            }
            for (Contract contract : distributor.getContracts()) {
                if (contract.getConsumerId() == consumer.getId()
                        && contract.getRemainedContractMonths() > 0) {
                    ok = false;
                    break;
                }
            }

            index = input.getInitialData().getDistributors().indexOf(distributor);

            // If found offer is better than the last one
            if (minimum == -1 || minimum > prices.get(index)) {
                minimum = prices.get(index);
                bestIndex = index;
            }
        }

        if (ok) {
            return input.getInitialData().getDistributors().get(bestIndex);
        }

        return null;
    }

    private void payTaxes() {

        // Each consumer pays price and eventually debt, also receives income
        for (Consumer consumer : input.getInitialData().getConsumers()) {
            if (consumer.isBankrupt()) {
                continue;
            }
            consumer.setBudget(consumer.getBudget() + consumer.getMonthlyIncome());

            for (Distributor distributor : input.getInitialData().getDistributors()) {
                for (Contract contract : distributor.getContracts()) {
                    if (contract.getConsumerId() == consumer.getId()) {
                        int price = contract.getPrice();
                        int debt = consumer.getDebt();
                        int budget = consumer.getBudget();


                        if (debt > 0 && consumer.getDebtDistributor().isBankrupt()) {
                            consumer.setDebt(0);
                            consumer.setDebtDistributor(null);
                        }

                        if (debt > 0) {

                            if ((budget - debt - price) < 0) {
                                consumer.setBankrupt(true);
                                break;
                            } else {
                                consumer.setBudget(budget - debt - price);
                                distributor.setBudget(distributor.getBudget() + price);

                                consumer.getDebtDistributor().setBudget(
                                        consumer.getDebtDistributor().getBudget() + debt);

                                consumer.setDebt(0);
                                consumer.setDebtDistributor(null);
                            }
                        } else {
                            if (budget < price) {
                                consumer.setDebt((int) Math.round(Math.floor(INTEREST * price)));
                                consumer.setDebtDistributor(distributor);
                            } else {
                                consumer.setBudget(budget - price);
                                distributor.setBudget(distributor.getBudget() +  price);
                            }
                        }

                        break;
                    }
                }
            }
        }

        // Each distributor pays price by formula and gets profit by formula
        for (Distributor distributor : input.getInitialData().getDistributors()) {
            if (distributor.isBankrupt()) {
                continue;
            }

            int payment = distributor.getInfrastructureCost()
                    + distributor.getProductionCost()
                    * distributor.getContracts().size();

            distributor.setBudget(distributor.getBudget() - payment);

            if (distributor.getBudget() < 0) {
                distributor.setBankrupt(true);
            }
        }

        // Update remaining contract months
        for (Distributor distributor : input.getInitialData().getDistributors()) {
            if (distributor.isBankrupt()) {
                continue;
            }

            for (Contract contract : distributor.getContracts()) {
                contract.setRemainedContractMonths(contract.getRemainedContractMonths() - 1);
            }
        }
    }

    private boolean resolveBankrupts() {

        boolean ok = true;

        // Check if gams should end due to all distributors have bankrupt
        for (Distributor distributor : input.getInitialData().getDistributors()) {
            if (!distributor.isBankrupt()) {
                ok = false;
                break;
            }
        }

        // Search to remove contracts for bankrupt entities
        for (Consumer consumer : input.getInitialData().getConsumers()) {
            Contract removeContract = null;

            for (Distributor distributor : input.getInitialData().getDistributors()) {
                for (Contract contract : distributor.getContracts()) {
                    if (contract.getConsumerId() == consumer.getId()
                            && (consumer.isBankrupt() || distributor.isBankrupt())) {
                        removeContract = contract;
                        break;
                    }
                }

                if (removeContract != null) {
                    distributor.getContracts().remove(removeContract);
                }
            }
        }

        return ok;
    }

    private void addEntities(final int i) {

        // Add the monthly new consumers and cost changes
        MonthlyUpdate update = input.getMonthlyUpdates().get(i);
        InitialData data = input.getInitialData();
        data.getConsumers().addAll(update.getNewConsumers());

        for (CostChange costChange : update.getCostsChanges()) {
            for (Distributor distributor : data.getDistributors()) {
                if (distributor.getId() == costChange.getId()) {
                    distributor.setInfrastructureCost(costChange.getInfrastructureCost());
                    distributor.setProductionCost(costChange.getProductionCost());
                }
            }
        }

        input.setInitialData(data);

    }

    /**
     * Run the game simulation based on Input
     * @return the output
     */
    public InitialData runSimulation() {

        // For each month there is in game
        for (int i = 0; i <= input.getNumberOfTurns(); i++) {
            ArrayList<Integer> prices = new ArrayList<>();

            // For each distributor recalculate the contract price
            for (Distributor distributor : input.getInitialData().getDistributors()) {
                prices.add(calculateCost(distributor));
            }

            // For each consumer find best deal and apply if there is no current one
            for (Consumer consumer : input.getInitialData().getConsumers()) {
                if (consumer.isBankrupt()) {
                    continue;
                }

                Distributor bestDistributor = getBestDeal(consumer, prices);
                if (bestDistributor != null) {

                    // Search for old contract
                    Contract old = null;
                    for (Distributor distributor : input.getInitialData().getDistributors()) {
                        for (Contract contract : distributor.getContracts()) {
                            if (contract.getConsumerId() == consumer.getId()) {
                                old = contract;
                                break;
                            }
                        }
                        distributor.getContracts().remove(old);
                    }



                    // Add the new contract
                    Contract contract = new Contract();

                    contract.setConsumerId(consumer.getId());
                    contract.setPrice(prices.get(
                            input.getInitialData().getDistributors().indexOf(bestDistributor)));
                    contract.setRemainedContractMonths(bestDistributor.getContractLength());

                    bestDistributor.getContracts().add(contract);
                }
            }

            // Apply monthly payments
            payTaxes();

            // Check and resolve bankrupts
            if (resolveBankrupts()) {
                break;
            }

            if (i != input.getNumberOfTurns()) {
                // Apply end of round changes
                addEntities(i);
            }
        }

        return input.getInitialData();
    }
}