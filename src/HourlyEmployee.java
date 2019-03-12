public class HourlyEmployee extends Employee
{
    private double hrRate;
    private double hrsWorked;
    private int weeksWorked;
    
     // constructors
    public HourlyEmployee()
    {
        super();
        hrRate = 0.0;
        hrsWorked = 0.0;
    }
    
    public HourlyEmployee(String name, String num, String dept, double rate, double hrs, int weeks)
    {
        super(name, num, dept);
        this.hrRate = rate;
        this.hrsWorked = hrs;
        this.weeksWorked = weeks;
    }
    
    public HourlyEmployee(HourlyEmployee he) {
    		super(he.getEmpName(), he.getEmpNum(), he.getDepartment().toString());
    		this.hrRate = he.hrRate;
    		this.hrsWorked = he.hrsWorked;
    		this.weeksWorked = he.weeksWorked;
    }
    
    
    public double getHrRate()
    {
        return hrRate;
    }
    
    public double getHrsWorked()
    {
        return hrsWorked;
    }
    
    // setters
    public void setHrRate(double rate)
    {
        hrRate = rate;
    }
    
    public void setHoursWorked(double hours) {
    		this.hrsWorked = hours;
    }
    
    public void resetHoursWorked() {
    		this.hrsWorked = 0.0;
    }
    
    public void addWeekWorked() {
    		weeksWorked++;
    }
    
    // other methods
    
    public double calcWeeklySalary()
    {
        double wklySal;
        
        if (hrsWorked <= 40.0)
        {
            wklySal = hrRate * hrsWorked;
        }
        else
        {
            wklySal = hrRate * 40.0 + (hrsWorked - 40.0) * hrRate * 1.5;
        }
        
        return wklySal;
    }
    
    
    public String toString()
    {
        String info;
        
        info = super.toString();
        info = info + "Employee Type:\t Hourly\n";
        info = info + "Hourly Rate:\t " + hrRate + "\n";
        info = info + "Hourse Worked:\t" + hrsWorked + "\n";
        info = info + "Weeks Worked:\t" + weeksWorked + "\n";
        
        return info;
    }
    
    public String getSaveString()
    {
        String info = super.getSaveString();
        info = info + ", H, " + hrRate + ", " + hrsWorked + ", " + weeksWorked;
        return info;
    }
    
    
}
