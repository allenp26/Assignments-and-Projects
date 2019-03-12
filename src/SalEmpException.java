public class SalEmpException extends EmpException {
    private double sal;

    public SalEmpException(double salary) {
        super("Invalid Annual Salary: " +salary+ " ");
        this.sal = salary;
    }

    public double getSal() {
        return sal;
    }
}
