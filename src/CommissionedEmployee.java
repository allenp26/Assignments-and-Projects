

public class CommissionedEmployee extends Employee implements Comparable<CommissionedEmployee>
{
    
    private int numWeeks;
    private double baseSal;
    private double wklySales;
    private double yrSales;
    private double commRate;
    
    public CommissionedEmployee()
    {
        super();
        
        numWeeks = 0;
        baseSal = 0.0;
        wklySales = 0.0;
        yrSales = 0.0;
        commRate = 0.0;
    }
    
    public CommissionedEmployee (String name, String num, String dept, int wks, double base, double w_sales, double y_sales, double rate)
    {
        super(name, num, dept);

        numWeeks = wks;
        baseSal = base;
        wklySales = w_sales;
        yrSales = y_sales;
        commRate = rate;
    }
    
    public CommissionedEmployee (CommissionedEmployee c) {
    		super (c.getEmpName(), c.getEmpNum(), c.getDepartment().toString());
    		
    		this.numWeeks = c.getNumWeeks();
    		this.baseSal = c.getBaseSal();
    		this.wklySales = c.getWklySales();
    		this.yrSales = c.getYrSales();
    		this.commRate = c.getCommRate();
    }
    
    // getters
    
    public int getNumWeeks()
    {
        return numWeeks;
    }
    
    public double getBaseSal()
    {
        return baseSal;
    }
    
    public double getWklySales()
    {
        return wklySales;
    }
    
    public double getYrSales()
    {
        return yrSales;
    }
    
    public double getCommRate()
    {
        return commRate;
    }
    
    // setters

    public void setBaseSal(double base)
    {
        baseSal = base;
    }
    
    public void setWklySales(double w_sales)
    {
        wklySales = w_sales;
    }
    
    public void setYrSales(double y_sales)
    {
        yrSales = y_sales;
    }
    
    public void setCommRate(double rate)
    {
        commRate = rate;
    }
    
    // other methods
    public double calcWeeklySalary()
    {
        double wklySal;
        
        wklySal = baseSal + wklySales * (commRate/100.0);
        
        return wklySal;
    }
    
    public boolean topSeller()
    {
        boolean isTop;
        
        double avg;
        
        avg = (yrSales + wklySales) / (numWeeks + 1);
        if (avg > 1500.0)
            isTop = true;
        else
            isTop = false;
            
        return isTop;
    }
    
    public String toString()
    {
        String info;
        
        info = super.toString();
        
        info = info + "Employee Type:\t C\n";
        info = info + "No of Weeks: \t" + numWeeks + "\n";
        info = info + "Base Salary:\t" + baseSal + "\n";
        info = info + "Weekly Sales: \t" + wklySales + "\n";
        info = info + "Yearly Sales: \t" + yrSales + "\n";
        info = info + "Commission: \t" + commRate + "%" + "\n";
        
        return info;
    }
    
    public String getSaveString()
    {
        String info = super.getSaveString();
        info = info + ", C, " + numWeeks + " " + baseSal + " " + wklySales + " " + yrSales + " " + commRate;
        return info;
        
    }

	public void addWeekWorked() {
		this.numWeeks++;
		
	}

	public void updateAndResetSales() {
		this.yrSales += this.wklySales;
		this.wklySales = 0.0;
		
	}

	@Override
	public int compareTo(CommissionedEmployee o) {
		
		return (int) Math.round(this.yrSales - o.yrSales);
	}
}

