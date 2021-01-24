package database;

import entities.Consumer;

import java.util.ArrayList;

public final class MonthlyUpdate {
    private ArrayList<Consumer> newConsumers;
    private ArrayList<DistributorChange> distributorChanges;
    private ArrayList<ProducerChange> producerChanges;

    public MonthlyUpdate() {
        newConsumers = new ArrayList<>();
        distributorChanges = new ArrayList<>();
        producerChanges = new ArrayList<>();
    }

    public ArrayList<Consumer> getNewConsumers() {
        return newConsumers;
    }

    public void setNewConsumers(final ArrayList<Consumer> newConsumers) {
        this.newConsumers = newConsumers;
    }

    public ArrayList<DistributorChange> getDistributorChanges() {
        return distributorChanges;
    }

    public void setDistributorChanges(ArrayList<DistributorChange> distributorChanges) {
        this.distributorChanges = distributorChanges;
    }

    public ArrayList<ProducerChange> getProducerChanges() {
        return producerChanges;
    }

    public void setProducerChanges(ArrayList<ProducerChange> producerChanges) {
        this.producerChanges = producerChanges;
    }
}
