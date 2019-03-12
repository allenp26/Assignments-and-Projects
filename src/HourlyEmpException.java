public class HourlyEmpException extends EmpException {
    private double hourlyPay;
    private double numOfHoursWorked;

    public HourlyEmpException(double hourlyPay, double numOfHoursWorked) {
        super("Invalid Hourly Employee: \nHourly Pay:" +hourlyPay+ "\n" +
            "Number of Hours Wokred: " +numOfHoursWorked);
        this.hourlyPay = hourlyPay;
        this.numOfHoursWorked = numOfHoursWorked;
    }

    public double getHourlyPay() {
        return hourlyPay;
    }

    public double getNumOfHoursWorked() {
        return numOfHoursWorked;
    }
}
