package strategies;

import entities.Producer;

import java.util.ArrayList;

public abstract class EnergyChoiceStrategy {
    ArrayList<Producer> producers;

    public abstract boolean determineSwap (int i, int j);

    public ArrayList<Producer> getProducers() {
        return producers;
    }
}
