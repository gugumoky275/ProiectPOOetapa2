package strategies;

import entities.Producer;

import java.util.ArrayList;

public final class QuantityStrategy extends EnergyChoiceStrategy {
    public QuantityStrategy(ArrayList<Producer> producers) {
        this.producers = new ArrayList<>(producers);
    }

    @Override
    public boolean determineSwap(int i, int j) {
        // Largest amount of energy first
        return (producers.get(i).getEnergyPerDistributor()
                < producers.get(j).getEnergyPerDistributor());
    }
}
