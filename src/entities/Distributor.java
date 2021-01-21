package entities;

import com.fasterxml.jackson.annotation.JsonAlias;
import java.util.ArrayList;

public final class Distributor extends Entity {
    private int contractLength;

    @JsonAlias({"initialInfrastructureCost"})
    private int infrastructureCost;

    @JsonAlias({"initialProductionCost"})
    private int productionCost;
    private ArrayList<Contract> contracts;

    public Distributor() {
        contracts = new ArrayList<>();
        isBankrupt = false;
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

    public int getProductionCost() {
        return productionCost;
    }

    public void setProductionCost(final int productionCost) {
        this.productionCost = productionCost;
    }

    public ArrayList<Contract> getContracts() {
        return contracts;
    }

    public void setContracts(final ArrayList<Contract> contracts) {
        this.contracts = contracts;
    }
}
