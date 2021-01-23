package strategies;

import entities.Distributor;
import entities.Producer;

import java.util.ArrayList;

public class GreenStrategy extends EnergyChoiceStrategy {
    public GreenStrategy(ArrayList<Producer> producers) {
        this.producers = producers;
    }

    @Override
    public boolean determineSwap (int i, int j) {
        // Green energy first
        if (producers.get(i).getEnergyType().isRenewable()
                && !producers.get(j).getEnergyType().isRenewable()) {
            return false;
        }
        if (!producers.get(i).getEnergyType().isRenewable()
                && producers.get(j).getEnergyType().isRenewable()) {
            return true;
        }

        // Best priceKW second
        if (producers.get(i).getPriceKW() < producers.get(j).getPriceKW()) {
            return false;
        }
        if (producers.get(i).getPriceKW() > producers.get(j).getPriceKW()) {
            return true;
        }

        // Largest amount of energy third
        return (producers.get(i).getEnergyPerDistributor()
                < producers.get(j).getEnergyPerDistributor());
    }
}
