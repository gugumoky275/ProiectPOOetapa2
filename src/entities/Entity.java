package entities;

import com.fasterxml.jackson.annotation.JsonAlias;

public class Entity {
    protected int id;

    @JsonAlias({"initialBudget"})
    protected int budget;
    protected boolean isBankrupt;

    public Entity() {
        isBankrupt = false;
    }

    public Entity(int id, int budget, boolean isBankrupt) {
        this.id = id;
        this.budget = budget;
        this.isBankrupt = isBankrupt;
    }

    /**
     * Gets the id
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the id
     * @param id the id
     */
    public void setId(final int id) {
        this.id = id;
    }

    /**
     * Gets the budget
     * @return the budget
     */
    public int getBudget() {
        return budget;
    }

    /**
     * Sets the budget
     * @param budget the budget
     */
    public void setBudget(final int budget) {
        this.budget = budget;
    }

    /**
     * Gets the bankrupt state
     * @return true if it is bankrupt, false otherwise
     */
    public boolean isBankrupt() {
        return isBankrupt;
    }

    /**
     * Sets the bankrupt state
     * @param bankrupt the state
     */
    public void setBankrupt(final boolean bankrupt) {
        isBankrupt = bankrupt;
    }
}
