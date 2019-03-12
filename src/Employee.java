
public abstract class Employee
{
	
	public enum Department {NONE, HUMAN_RESOURCES, FINANCE, APPLIANCES, FURNITURE, INFORMATION_TECHNOLOGY};
	
    private String empName;
    private String empNum;
    private Department department;
    
    public Employee()
    {
        empName = "";
        empNum = "";
        department = Department.NONE;
    }
    
    public Employee (String name, String num, String dept)
    {
        empName = name;
        empNum = num;
        department = parseDepartment(dept);
    }
    
    private Department parseDepartment(String dept) {
    		String adjusted = dept.trim().toUpperCase().replaceAll(" ", "_");
    		return Department.valueOf(adjusted);
	}

	// getters
    public String getEmpName()
    {
        return empName;
    }
    
    public String getEmpNum()
    {
        return empNum;
    }
    
    public Department getDepartment()
    {
        return department;
    }
    
    // setters
    public void setEmpName (String name)
    {
        empName = name;
    }
    
    public void setEmpNum (String num)
    {
        empNum = num;
    }
    
    public void setDepartment(String dept)
    {
        department = parseDepartment(dept);
    }
    
    // other methods
    
    // calculates weekly salary
    public abstract double calcWeeklySalary();
    
    
    // the equals method
    public boolean equals (Employee e)
    {
        boolean isEqual;
        
        if (e.getEmpNum().equals(empNum))
            isEqual = true;
        else
            isEqual = false;
         
        return isEqual;
    }
    
    // the toString method
    public String toString()
    {
        String info;
        
        info = "Employee Name:\t " + empName + "\n";
        info = info + "Employee No:\t " + empNum + "\n";
        info = info + "Department:\t " + department + "\n";
        
        return info;
    }
    
    public String getSaveString()
    {
        return empName + ", " + empNum + ", " + department;
    }
    
}   

    