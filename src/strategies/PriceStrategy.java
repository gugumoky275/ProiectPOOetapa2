package strategies;

import entities.Producer;

import java.util.ArrayList;

public final class PriceStrategy extends EnergyChoiceStrategy {
    public PriceStrategy(ArrayList<Producer> producers) {
        this.producers = new ArrayList<>(producers);
    }

    @Override
    public boolean determineSwap(int i, int j) {
        // Best priceKW first
        if (producers.get(i).getPriceKW() < producers.get(j).getPriceKW()) {
            return false;
        }
        if (producers.get(i).getPriceKW() > producers.get(j).getPriceKW()) {
            return true;
        }

        // Largest amount of energy second
        return (producers.get(i).getEnergyPerDistributor()
                < producers.get(j).getEnergyPerDistributor());
    }
}
