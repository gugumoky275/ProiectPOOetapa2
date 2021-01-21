package database;

import entities.Consumer;

import java.util.ArrayList;

public final class MonthlyUpdate {
    private ArrayList<Consumer> newConsumers;
    private ArrayList<CostChange> costsChanges;

    public MonthlyUpdate() {
        newConsumers = new ArrayList<>();
        costsChanges = new ArrayList<>();
    }

    public ArrayList<Consumer> getNewConsumers() {
        return newConsumers;
    }

    public void setNewConsumers(final ArrayList<Consumer> newConsumers) {
        this.newConsumers = newConsumers;
    }

    public ArrayList<CostChange> getCostsChanges() {
        return costsChanges;
    }

    public void setCostsChanges(final ArrayList<CostChange> costsChanges) {
        this.costsChanges = costsChanges;
    }
}
