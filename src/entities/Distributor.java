package entities;

import com.fasterxml.jackson.annotation.JsonAlias;
import strategies.EnergyChoiceStrategy;
import strategies.EnergyChoiceStrategyType;

import java.util.ArrayList;

public final class Distributor extends Entity {
    private static final int DIVCONST = 10;
    private int contractLength;

    @JsonAlias({"initialInfrastructureCost"})
    private int infrastructureCost;
    private int energyNeededKW;
    private EnergyChoiceStrategyType producerStrategy;
    private int cost;
    private ArrayList<Contract> contracts;
    private boolean reapplyStrategy;
    private int contractCost;

    public Distributor() {
        super();
        contracts = new ArrayList<>();
        reapplyStrategy = true;
        cost = 0;
    }

    public  Distributor(int id, int budget, boolean isBankrupt, int contractLength,
                        int infrastructureCost, int energyNeededKW,
                        EnergyChoiceStrategyType producerStrategy) {
        super(id, budget, isBankrupt);
        this.contractLength = contractLength;
        this.infrastructureCost = infrastructureCost;
        this.energyNeededKW = energyNeededKW;
        this.producerStrategy = producerStrategy;
        contracts = new ArrayList<>();
        reapplyStrategy = true;
        cost = 0;
    }

    public int getContractLength() {
        return contractLength;
    }

    public void setContractLength(final int contractLength) {
        this.contractLength = contractLength;
    }

    public int getInfrastructureCost() {
        return infrastructureCost;
    }

    public void setInfrastructureCost(final int infrastructureCost) {
        this.infrastructureCost = infrastructureCost;
    }

    public int getEnergyNeededKW() {
        return energyNeededKW;
    }

    public void setEnergyNeededKW(final int energyNeededKW) {
        this.energyNeededKW = energyNeededKW;
    }

    public EnergyChoiceStrategyType getProducerStrategy() {
        return producerStrategy;
    }

    public void setProducerStrategy(EnergyChoiceStrategyType producerStrategy) {
        this.producerStrategy = producerStrategy;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public ArrayList<Contract> getContracts() {
        return contracts;
    }

    public void setContracts(final ArrayList<Contract> contracts) {
        this.contracts = contracts;
    }

    public boolean getReapplyStrategy() {
        return reapplyStrategy;
    }

    public void setReapplyStrategy(boolean reapplyStrategy) {
        this.reapplyStrategy = reapplyStrategy;
    }

    public int getContractCost() {
        return contractCost;
    }

    public void setContractCost(int contractCost) {
        this.contractCost = contractCost;
    }

    // Observer part of class

    /**
     * Updates this Observer if any Observable (associated Producer) has changed
     */
    public void update() {
        reapplyStrategy = true;
    }

    // Strategy part of class

    /**
     * Assigns this Distributor to the producer it needs, based on given strategy
     * @param energyChoiceStrategy the Strategy chosen
     */
    public void chooseProducers(EnergyChoiceStrategy energyChoiceStrategy) {
        int i, j, currentEnergy = 0;
        ArrayList<Producer> producers = energyChoiceStrategy.getProducers();

        // Sort producers by strategy
        for (i = 0; i < producers.size() - 1; i++) {
            for (j = i + 1; j < producers.size(); j++) {
                if (energyChoiceStrategy.determineSwap(i, j)) {
                    Producer temp = producers.get(i);
                    producers.set(i, producers.get(j));
                    producers.set(j, temp);
                }
            }
        }

        // Choose the producers in order until demand is fulfilled
        i = 0;
        float aux = 0;
        while (currentEnergy < getEnergyNeededKW()) {
            if (producers.get(i).getDistributors().size()
                    == producers.get(i).getMaxDistributors()) {
                i++;
                continue;
            }

            producers.get(i).addObserver(this);
            aux += producers.get(i).getPriceKW()
                    * producers.get(i).getEnergyPerDistributor();

            currentEnergy += producers.get(i).getEnergyPerDistributor();
            i++;
        }
        setCost((int) Math.round(Math.floor(aux / DIVCONST)));
    }
}
