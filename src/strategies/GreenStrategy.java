package strategies;

import entities.Distributor;
import entities.Producer;

import java.util.ArrayList;

public class GreenStrategy extends EnergyChoiceStrategy {

    public GreenStrategy(ArrayList<Producer> producers) {
        this.producers = producers;
    }

    @Override
    public void chooseProducers (Distributor distributor) {

    }
}
