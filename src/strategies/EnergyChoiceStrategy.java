package strategies;

import entities.Producer;

import java.util.ArrayList;

public abstract class EnergyChoiceStrategy {
    protected ArrayList<Producer> producers;

    /**
     * Each strategy implements a sort, comparing Producer i with Producer j with this function
     * @param i the i-th Producer
     * @param j the j-th Producer
     * @return whether Producers i and j should be swapped, based on a strategy
     */
    public abstract boolean determineSwap(int i, int j);

    /**
     * @return The producers to be used inside the strategy in Distributor
     */
    public ArrayList<Producer> getProducers() {
        return producers;
    }
}
