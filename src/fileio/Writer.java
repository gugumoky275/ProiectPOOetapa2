package fileio;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import database.InitialData;
import entities.Consumer;
import entities.Contract;
import entities.Distributor;
import entities.EnergyType;
import entities.MonthlyStat;
import entities.Producer;
import strategies.EnergyChoiceStrategyType;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public final class Writer {
    private static Writer instance = null;

    private Writer() { }

    /**
     * Get instance of Singleton class Writer
     * @return the instance
     */
    public static Writer getInstance() {
        if (instance == null) {
            instance = new Writer();
        }
        return instance;
    }

    /**
     * Writes the data
     * @param path path to output file
     * @param result the output wanted
     * @throws IOException IOException
     */
    public void writeData(final String path, final InitialData result) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

        ArrayList<ConsumerOutput> consumers = new ArrayList<>();
        ArrayList<DistributorOutput> distributors = new ArrayList<>();
        ArrayList<ProducerOutput> energyProducers = new ArrayList<>();

        // Copy consumer fields to be written
        for (Consumer consumer : result.getConsumers()) {
            ConsumerOutput consumerOutput = new ConsumerOutput(consumer.getId(),
                    consumer.isBankrupt(), consumer.getBudget());

            consumers.add(consumerOutput);
        }

        // Copy distributor fields to be written
        for (Distributor distributor : result.getDistributors()) {
            DistributorOutput distributorOutput = new DistributorOutput(distributor.getId(),
                    distributor.getEnergyNeededKW(), distributor.getContractCost(),
                    distributor.getBudget(), distributor.getProducerStrategy(),
                    distributor.isBankrupt(), distributor.getContracts());

            distributors.add(distributorOutput);
        }

        // Copy producer fields to be written
        for (Producer producer : result.getProducers()) {
            ProducerOutput producerOutput = new ProducerOutput(producer.getId(),
                    producer.getMaxDistributors(), producer.getPriceKW(), producer.getEnergyType(),
                    producer.getEnergyPerDistributor(), producer.getMonthlyStats());

            energyProducers.add(producerOutput);
        }

        Output output = new Output(consumers, distributors, energyProducers);
        objectMapper.writeValue(new File(path), output);
    }
}

class Output {
    private ArrayList<ConsumerOutput> consumers;
    private ArrayList<DistributorOutput> distributors;
    private ArrayList<ProducerOutput> energyProducers;

    Output(ArrayList<ConsumerOutput> consumers, ArrayList<DistributorOutput> distributors,
                  ArrayList<ProducerOutput> energyProducers) {

        this.consumers = consumers;
        this.distributors = distributors;
        this.energyProducers = energyProducers;
    }

    public ArrayList<ConsumerOutput> getConsumers() {
        return consumers;
    }

    public void setConsumers(final ArrayList<ConsumerOutput> consumers) {
        this.consumers = consumers;
    }

    public ArrayList<DistributorOutput> getDistributors() {
        return distributors;
    }

    public void setDistributors(final ArrayList<DistributorOutput> distributors) {
        this.distributors = distributors;
    }

    public ArrayList<ProducerOutput> getEnergyProducers() {
        return energyProducers;
    }

    public void setEnergyProducers(ArrayList<ProducerOutput> energyProducers) {
        this.energyProducers = energyProducers;
    }
}

class ConsumerOutput {
    private int id;
    private boolean isBankrupt;
    private int budget;

    ConsumerOutput(int id, boolean isBankrupt, int budget) {
        this.id = id;
        this.isBankrupt = isBankrupt;
        this.budget = budget;
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public boolean getIsBankrupt() {
        return isBankrupt;
    }

    public void setIsBankrupt(final boolean bankrupt) {
        isBankrupt = bankrupt;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(final int budget) {
        this.budget = budget;
    }
}

class DistributorOutput {
    private int id;
    private int energyNeededKW;
    private int contractCost;
    private int budget;
    private EnergyChoiceStrategyType producerStrategy;
    private boolean isBankrupt;
    private ArrayList<Contract> contracts;

    DistributorOutput(int id, int energyNeededKW, int contractCost, int budget,
                             EnergyChoiceStrategyType producerStrategy, boolean isBankrupt,
                             ArrayList<Contract> contracts) {
        this.id = id;
        this.energyNeededKW = energyNeededKW;
        this.contractCost = contractCost;
        this.budget = budget;
        this.producerStrategy = producerStrategy;
        this.isBankrupt = isBankrupt;
        this.contracts = contracts;
    }

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public int getEnergyNeededKW() {
        return energyNeededKW;
    }

    public void setEnergyNeededKW(int energyNeededKW) {
        this.energyNeededKW = energyNeededKW;
    }

    public int getContractCost() {
        return contractCost;
    }

    public void setContractCost(int contractCost) {
        this.contractCost = contractCost;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(final int budget) {
        this.budget = budget;
    }

    public EnergyChoiceStrategyType getProducerStrategy() {
        return producerStrategy;
    }

    public void setProducerStrategy(EnergyChoiceStrategyType producerStrategy) {
        this.producerStrategy = producerStrategy;
    }

    public boolean getIsBankrupt() {
        return isBankrupt;
    }

    public void setIsBankrupt(final boolean bankrupt) {
        isBankrupt = bankrupt;
    }

    public ArrayList<Contract> getContracts() {
        return contracts;
    }

    public void setContracts(final ArrayList<Contract> contracts) {
        this.contracts = contracts;
    }
}

class ProducerOutput {
    private int id;
    private int maxDistributors;
    private float priceKW;
    private EnergyType energyType;
    private int energyPerDistributor;
    private ArrayList<MonthlyStat> monthlyStats;

    ProducerOutput(int id, int maxDistributors, float priceKW,
                          EnergyType energyType, int energyPerDistributor,
                          ArrayList<MonthlyStat> monthlyStats) {
        this.id = id;
        this.maxDistributors = maxDistributors;
        this.priceKW = priceKW;
        this.energyType = energyType;
        this.energyPerDistributor = energyPerDistributor;
        this.monthlyStats = monthlyStats;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMaxDistributors() {
        return maxDistributors;
    }

    public void setMaxDistributors(int maxDistributors) {
        this.maxDistributors = maxDistributors;
    }

    public float getPriceKW() {
        return priceKW;
    }

    public void setPriceKW(float priceKW) {
        this.priceKW = priceKW;
    }

    public EnergyType getEnergyType() {
        return energyType;
    }

    public void setEnergyType(EnergyType energyType) {
        this.energyType = energyType;
    }

    public int getEnergyPerDistributor() {
        return energyPerDistributor;
    }

    public void setEnergyPerDistributor(int energyPerDistributor) {
        this.energyPerDistributor = energyPerDistributor;
    }

    public ArrayList<MonthlyStat> getMonthlyStats() {
        return monthlyStats;
    }

    public void setMonthlyStats(ArrayList<MonthlyStat> monthlyStats) {
        this.monthlyStats = monthlyStats;
    }
}

