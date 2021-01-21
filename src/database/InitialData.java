package database;

import entities.Consumer;
import entities.Distributor;

import java.util.ArrayList;

public final class InitialData {
    private ArrayList<Consumer> consumers;
    private ArrayList<Distributor> distributors;

    public InitialData() {
        this.consumers = new ArrayList<>();
        this.distributors = new ArrayList<>();
    }

    public ArrayList<Consumer> getConsumers() {
        return consumers;
    }

    public void setConsumers(final ArrayList<Consumer> consumers) {
        this.consumers = consumers;
    }

    public ArrayList<Distributor> getDistributors() {
        return distributors;
    }

    public void setDistributors(final ArrayList<Distributor> distributors) {
        this.distributors = distributors;
    }
}
