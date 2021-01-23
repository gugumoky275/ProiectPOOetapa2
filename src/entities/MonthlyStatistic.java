package entities;

import java.util.ArrayList;

public class MonthlyStatistic {
    private int month;
    private ArrayList<Integer> distributorsIds;

    public MonthlyStatistic(int month, ArrayList<Distributor> distributors) {
        this.month = month;
        distributorsIds = new ArrayList<>();
        for (Distributor distributor : distributors) {
            distributorsIds.add(distributor.getId());
        }
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public ArrayList<Integer> getDistributorsIds() {
        return distributorsIds;
    }

    public void setDistributorsIds(ArrayList<Integer> distributorsIds) {
        this.distributorsIds = distributorsIds;
    }
}
