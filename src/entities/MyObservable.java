package entities;

public interface MyObservable {

    /**
     * Adds an Observer to the Observer list
     * @param distributor the Distributor that needs to be added to Observers
     */
    void addObserver(Distributor distributor);

    /**
     * Eliminates an Observer from the Observer list
     * @param distributor the Distributor that needs to be eliminated from Observers
     */
    void deleteObserver(Distributor distributor);

    /**
     * Notifies all Observers that Observable has changed
     */
    void notifyObservers();
}
