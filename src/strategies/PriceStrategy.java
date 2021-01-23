package strategies;

import entities.Distributor;
import entities.Producer;

import java.util.ArrayList;

public class PriceStrategy extends EnergyChoiceStrategy {
    public PriceStrategy(ArrayList<Producer> producers) {
        this.producers = producers;
    }

    @Override
    public boolean determineSwap (int i, int j) {
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
