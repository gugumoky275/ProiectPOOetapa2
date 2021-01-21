package entities;

public final class Consumer extends Entity {
    private int monthlyIncome;
    private int debt = 0;
    private Distributor debtDistributor = null;

    public Consumer() {
        isBankrupt = false;
    }

    public int getMonthlyIncome() {
        return monthlyIncome;
    }

    public void setMonthlyIncome(final int monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    public int getDebt() {
        return debt;
    }

    public void setDebt(final int debt) {
        this.debt = debt;
    }

    public Distributor getDebtDistributor() {
        return debtDistributor;
    }

    public void setDebtDistributor(final Distributor debtDistributor) {
        this.debtDistributor = debtDistributor;
    }
}
