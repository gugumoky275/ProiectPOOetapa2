package fileio;

import database.InitialData;
import database.MonthlyUpdate;

import java.util.ArrayList;

public final class Input {
    private int numberOfTurns;
    private InitialData initialData;
    private ArrayList<MonthlyUpdate> monthlyUpdates;

    public Input() {
        initialData = new InitialData();
        monthlyUpdates = new ArrayList<>();
    }

    public int getNumberOfTurns() {
        return numberOfTurns;
    }

    public void setNumberOfTurns(final int numberOfTurns) {
        this.numberOfTurns = numberOfTurns;
    }

    public InitialData getInitialData() {
        return initialData;
    }

    public void setInitialData(final InitialData initialData) {
        this.initialData = initialData;
    }

    public ArrayList<MonthlyUpdate> getMonthlyUpdates() {
        return monthlyUpdates;
    }

    public void setMonthlyUpdates(final ArrayList<MonthlyUpdate> monthlyUpdates) {
        this.monthlyUpdates = monthlyUpdates;
    }
}
