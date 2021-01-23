package entities;

public interface MyObservable {
    void addObserver(Distributor distributor);

    void deleteObserver(Distributor distributor);

    void notifyObservers();

    boolean hasChanged();

    void setChanged();

    void clearChanged();
}
