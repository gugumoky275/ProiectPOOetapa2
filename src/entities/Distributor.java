package entities;

import com.fasterxml.jackson.annotation.JsonAlias;
import strategies.EnergyChoiceStrategyType;

import java.util.ArrayList;

public final class Distributor extends Entity {
    private int contractLength;

    @JsonAlias({"initialInfrastructureCost"})
    private int infrastructureCost;
    private int energyNeededKW;
    private EnergyChoiceStrategyType producerStrategy;
    private int cost;
    private ArrayList<Contract> contracts;
    private boolean reapplyStrategy;

    public Distributor() {
        super();
        contracts = new ArrayList<>();
        reapplyStrategy = false;
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
        reapplyStrategy = false;
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

    public int getCost () {
        return cost;
    }

    public void setCost (int cost) {
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

    // Observer part of class

    public void update(Producer producer) {
        reapplyStrategy = true;
    }
}
