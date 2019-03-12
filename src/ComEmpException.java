public class ComEmpException extends EmpException {
    private double baseWeekSal;
    private double comRate;

    public ComEmpException(double weekSal, double comRate) {
        super("Invalid Commission Employee with base weekly salary of " +weekSal+
            " and commission rate of " +comRate);
        this.baseWeekSal = weekSal;
        this.comRate = comRate;
    }

    public double getBaseWeekSal() {
        return baseWeekSal;
    }

    public double getComRate() {
        return comRate;
    }
}
