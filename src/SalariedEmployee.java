

public class SalariedEmployee extends Employee
{

    private double salary;
    
    // constructors
    public SalariedEmployee()
    {
        super();
        salary = 0.0;
    }
    
    public SalariedEmployee(String name, String num, String dept, double sal)
    {
        super(name, num, dept);
        salary = sal;
    }
    
    public SalariedEmployee(SalariedEmployee se) {
    		super(se.getEmpName(), se.getEmpNum(), se.getDepartment().toString());
    		salary = se.getSalary();
    }
    
    // getters
    
    public double getSalary()
    {
        return salary;
    }
    
    // setters
    public void setSalary(double sal)
    {
        salary = sal;
    }
    
    // other methods
    public double calcWeeklySalary()
    {
        double wklySal;
        
        wklySal = salary/52.0;
        return wklySal;
    }
    
    public String toString()
    {
        String info;
        
        info = super.toString();
        info = info + "Employee Type:\t S\n";
        info = info + "Yearly Salary:\t " + salary + "\n";
        
        return info;
    }
    
    public String getSaveString()
    {
        String info = super.getSaveString();
        info = info + ", S, " + salary;
        return info;
    }
}
