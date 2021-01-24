package entities;

import java.util.ArrayList;

public final class Producer implements MyObservable {
    private int id;
    private EnergyType energyType;
    private int maxDistributors;
    private float priceKW;
    private int energyPerDistributor;
    private ArrayList<Distributor> distributors;
    private ArrayList<MonthlyStat> monthlyStats;

    public Producer() {
        distributors = new ArrayList<>();
        monthlyStats = new ArrayList<>();
    }

    public Producer(int id, EnergyType energyType, int maxDistributors, float priceKW,
                    int energyPerDistributor) {
        this.id = id;
        this.energyType = energyType;
        this.maxDistributors = maxDistributors;
        this.priceKW = priceKW;
        this.energyPerDistributor = energyPerDistributor;
        distributors = new ArrayList<>();
        monthlyStats = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public EnergyType getEnergyType() {
        return energyType;
    }

    public void setEnergyType(EnergyType energyType) {
        this.energyType = energyType;
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

    // Observable part of class

    public ArrayList<Distributor> getDistributors() {
        return distributors;
    }

    public void setDistributors(ArrayList<Distributor> distributors) {
        this.distributors = distributors;
    }

    @Override
    public synchronized void addObserver(Distributor distributor) {
        distributors.add(distributor);
    }

    @Override
    public synchronized void deleteObserver(Distributor distributor) {
        distributors.remove(distributor);
    }

    @Override
    public void notifyObservers() {
        for (Distributor distributor : distributors) {
            distributor.update();
        }
    }
}
