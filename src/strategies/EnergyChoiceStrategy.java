package strategies;

import entities.Distributor;
import entities.Producer;

import java.util.ArrayList;

public abstract class EnergyChoiceStrategy {
    ArrayList<Producer> producers;

    abstract void chooseProducers(Distributor distributor);
}
