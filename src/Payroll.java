import jdk.internal.util.xml.impl.Input;

import java.util.*;
import java.io.*;

public class Payroll
{
    ArrayList <Employee> personnel;
    Scanner kb = new Scanner(System.in);
    
    File dataFile;
    
    static final double MIN_SALARY = 24000;
    static final double MAX_SALARY = 480000;
    static final double MIN_HOURLY_HOUR = 0;
    static final double MAX_HOURLY_HOUR = 70;
    static final double MIN_HOURLY_PAY = 15;
    static final double MIN_COM_BASE = 400;
    static final double MIN_COM_RATE = 0;
    static final double MAX_COM_RATE = 0.20;
    
    public Payroll (File dataFile) throws IOException
    {
        this.personnel = new ArrayList <Employee> ();
        this.dataFile = dataFile;
        loadData();
    }
    
    // processing methods
    
    // adding an employee (Option A)
    public void addEmp()
    {
        Employee e;
        String nbr;
        
        System.out.println("Please enter the employee number you wish to add: ");
        nbr = kb.nextLine();
        
        e = validateNum(nbr);
        if (e != null)
        {
            System.out.println("That employee number is already in the payroll.  Cannot add employee");
        }
        else
        {
            e = readData(nbr);
            personnel.add(e); 
            System.out.println ("Employee " + nbr + " added to the Payroll");
        }
    }
    
    // printing individual employee info (Option I)
    public void printInfo()
    {
        Employee e;
        String nbr;
        
        System.out.println ("Please enter the employee number ");
        nbr = kb.nextLine();
        
        e = validateNum(nbr);
        if (e != null)
        {
            System.out.println (e.toString());
        }
        else
        {
            System.out.println("Employee does not exist in the payroll");
        }
    }
    
    // removing an employee (Option D)
    public void remove()
    {
        Employee e;
        String nbr;
        char ans;
        
        System.out.println ("Please enter the employee number to be removed ");
        nbr = kb.nextLine();
        
        e = validateNum(nbr);
        if (e != null)
        {
            System.out.println ("Do you really want to remove " + e.getEmpName() + " with number " + e.getEmpNum() + " Y or N?");
            ans = kb.nextLine().charAt(0);
            if (ans == 'Y')
            {
                personnel.remove(e);
                System.out.println ("Employee " + nbr + " removed from Payroll");
            }
            else
            {
                System.out.println ("You answered 'N'. " + e.getEmpName() + " will not be removed.");
            }
        }
        else
        {
            System.out.println("Employee does not exist in the payroll.  None removed");
        }
        
    }
    
    // prints the weekly salary of a particular employee (Option S)
    public void printWklySal()
    {
        Employee e;
        String nbr;
        
        System.out.println ("Please enter the employee number ");
        nbr = kb.nextLine();
        
        e = validateNum(nbr);
        if (e != null)
        {
            System.out.println ("The weekly salary of " + e.getEmpName() + " is " + e.calcWeeklySalary());
        }
        else
        {
            System.out.println("Employee does not exist in the payroll");
        }
    }
    

    public void printTopSellers(int topCount)
    {
        
    		List<CommissionedEmployee> topSellers = new ArrayList<>();
    		
    		
    		
    		for (Employee e : personnel) {
        		if (e instanceof CommissionedEmployee) {
        			topSellers.add((CommissionedEmployee) e);
        		}
        }
        
         
        for (int i = 0; i < topCount && i < topSellers.size(); i++) {
        		System.out.println(topSellers.get(i).getEmpName());
        		System.out.println();
        }
        
        
        
    }
    
    
    // prints the weekly salary report
    public void salaryRpt()
    {
        for (Employee e : personnel) {
            System.out.println (e.getEmpName() + "\t" + e.getEmpNum() + "\t" + e.getClass() + "\t" + e.calcWeeklySalary());
        }
    }
   
    // end of week (Option W) (for now now writing to file)
    public void endOfWeek() throws IOException
    {
        for (Employee e : personnel)
        {
            if (e instanceof HourlyEmployee) {
            		HourlyEmployee he = (HourlyEmployee) e;
                    he.resetHoursWorked();
                    he.addWeekWorked();
            }
            if (e instanceof CommissionedEmployee) {
            		CommissionedEmployee ce = (CommissionedEmployee) e;
            		ce.addWeekWorked();
            		ce.updateAndResetSales();
            }
        }
    }
    
    // helper methods
    // validate employee number
    public Employee validateNum(String num)
    {
        Employee emp = null;
        boolean found = false; 
        int i = 0;
        
        while (i < personnel.size() && !found)
        {
            if (personnel.get(i).getEmpNum().equals(num))
            {
                emp = personnel.get(i);
                found = true;
            }
            i++;
        }
        return emp;
    }
    
    //validate salary employee data
    public boolean validateSalaryEmpSalary(double salary) {

        if (salary <= MAX_SALARY)
        {
            return true;
        }
        else if (salary >= MIN_SALARY)
        {
            return true;
        }


        return false;
    }

    //validate hourly employee data
    public boolean validateHourlyEmpHoursNPay(double hours, double hourlyRate) {
        double payout;


        if (hours >= MIN_HOURLY_HOUR && hours <= MAX_HOURLY_HOUR)
        {
            payout = hours * hourlyRate;

            if (payout >= MIN_HOURLY_PAY) {
                return true;
            }
            else {
                System.out.println("The pay out for this employee is less than minimum: 15");
                return false;
            }

        }
        else if (hours < MIN_HOURLY_HOUR) {
            System.out.println("The number of hours you have entered is below minimum: 0");

            return false;
        }
        else if (hours >MAX_HOURLY_HOUR) {
            System.out.println("The number of hours you have entered is above maximum: 70");

            return false;
        }

        return false;
    }
    
    // reading data of employee from keyboard
    public Employee readData(String n1)
    {
        Employee e1 = null;
        String name;
        String dept;
        char type;    
                
        System.out.println ("Please enter the employee type: S, H, or C");
        type = kb.nextLine().charAt(0); 
        
        if (type == 'S' || type == 'H' || type == 'C') 
        {

            System.out.println ("Please enter the name of the employee");
            name = kb.nextLine();
            System.out.println ("Please enter the department of " + name);
            dept = kb.nextLine();
            
            switch (type)
            {
                case 'S':
                    try {
                        System.out.println("Please enter the yearly salary of " + name);
                        double sal = kb.nextDouble();
                        e1 = new SalariedEmployee(name, n1, dept, sal);
                    } catch (InputMismatchException e) {
                        System.out.println("Employee was not added to the system");
                        System.out.println("Expected a double for salary but got something else.");
                    }
                    break;
                case 'H':
                    String errorHourly = "";
                    String expectedInputHourly = "";

                    try {
                        System.out.println("Please enter the hourly rate of " + name);
                        errorHourly = "Hourly Rate";
                        expectedInputHourly = "Double value";
                        double rate = kb.nextDouble();
                        System.out.println("Please enter the number of hours worked by " + name + " this week");
                        errorHourly = "Number of hours worked";
                        double hrs = kb.nextDouble();
                        System.out.println("Please enter the number of weeks worked by " + name + ".");
                        errorHourly = "Number of weeks worked";
                        expectedInputHourly = "Integer value";
                        int weeks = kb.nextInt();
                        e1 = new HourlyEmployee(name, n1, dept, rate, hrs, weeks);
                    } catch (InputMismatchException e) {
                        System.out.println("Employee was not added to the system: \n Expected: " +
                                expectedInputHourly + "\n for " + errorHourly);
                    }
                    break;
                case 'C':
                    String errorCom = "";
                    String expectedInputCom = "";

                    try {
                        System.out.println("Please enter the number of weeks " + name + " has been working");
                        errorCom = "Number of weeks";
                        expectedInputCom = "Integer Value";
                        int wks = kb.nextInt();
                        System.out.println("Please enter the base salary of " + name);
                        errorCom = "Base Salary";
                        expectedInputCom = "Double Value";
                        double base = kb.nextDouble();
                        System.out.println("Please enter this week's sales for " + name);
                        errorCom = "This week's sale";
                        double w_sales = kb.nextDouble();
                        System.out.println("Please enter the year to date sales for " + name);
                        errorCom = "This year to date sale";
                        double y_sales = kb.nextDouble();
                        System.out.println("Please enter the commission rate of " + name);
                        errorCom = "Commission rate";
                        double comm = kb.nextDouble();
                        e1 = new CommissionedEmployee(name, n1, dept, wks, base, w_sales, y_sales, comm);
                    } catch (InputMismatchException e) {
                        System.out.println("Employee was not added to the system: \n Expected: " +
                                expectedInputCom + "\n for " + errorCom);
                    }
                    break;                
            }
            kb.nextLine();
        }
        return e1;
    }
    
    // prints info about all employees
    public void printAll()
    {
        for (Employee e: personnel)
        {
            
            
            System.out.println (e);
            System.out.println();
            
        }
    }
    
    //prints all employee numbers
    public void printAllNum()
    {
        int i = 0;
        while (i < personnel.size())
        {
            Employee e;
            e = personnel.get(i);
            System.out.println (e.getEmpNum());
            i++;
        }
    }
    
    // loads data from file
    public void loadData() throws IOException, FileNotFoundException
    {
        
        Scanner in = new Scanner (dataFile);
        
        
        int fileLineNum = 1;
        System.out.println("Reading data from: " + dataFile.getName());
        while(in.hasNextLine())
        {
        		Employee e;
        		String line = in.nextLine();
        		String[] data = line.split(",");


            String name = data[0].trim();
            String number = data[1].trim();
            String department = data[2].trim();
            String type = data[3].trim();

            try {
            for (Employee empList : personnel) {
                if (number.equals(empList.getEmpNum())) {
                    throw new DuplicateEmpException(number);
                }
            }
            switch(type) {
                case "H":
                    if (data.length != 7) {
                        throw new EmpException(number);
                    }
                    double rate = Double.parseDouble(data[4].trim());
                    double hours = Double.parseDouble(data[5].trim());
                    int weeks = Integer.parseInt(data[6].trim());
                    if (rate < MIN_HOURLY_PAY || hours < MIN_HOURLY_HOUR || hours > MAX_HOURLY_HOUR) {
                        throw new HourlyEmpException(rate, hours);
                    }
                    e = new HourlyEmployee(name, number, department, rate, hours, weeks);
                    System.out.println("Adding Employee " + e.getEmpName());
                    personnel.add(e);
                    break;
                case "S":
                    if (data.length != 5) {
                        throw new EmpException(number);
                    }
                    double salary = Double.parseDouble(data[4].trim());
                    if (salary < MIN_SALARY || salary > MAX_SALARY) {
                        throw new SalEmpException(salary);
                    }
                    e = new SalariedEmployee(name, number, department, salary);
                    System.out.println("Adding Employee " + e.getEmpName());
                    personnel.add(e);
                    break;
                case "C":
                    if (data.length != 9) {
                        throw new EmpException(number);
                    }
                    double basePay = Double.parseDouble(data[4].trim());
                    double weeklySales = Double.parseDouble(data[5].trim());
                    double yearlySales = Double.parseDouble(data[6].trim());
                    double commissionRate = Double.parseDouble(data[7].trim());
                    weeks = Integer.parseInt(data[8].trim());
                    if (basePay < MIN_COM_BASE || commissionRate < MIN_COM_RATE || commissionRate > MAX_COM_RATE) {
                        throw new ComEmpException(basePay, commissionRate);
                    }
                    e = new CommissionedEmployee(name, number, department, weeks, basePay, weeklySales, yearlySales, commissionRate);
                    System.out.println("Adding Employee " + e.getEmpName());
                    personnel.add(e);
                    break;
                default:
                    System.out.println(type + " is not valid");
            }
            } catch (NumberFormatException ex) {
                    String lineNum = "";
                    for (String lineItem : data) {
                        lineNum += lineItem + ", ";
                    }
                    System.out.println("Line " + fileLineNum + ": Unable to parse line containing: " + lineNum);
                } catch (SalEmpException Exce) {
                    System.out.print("Line " + fileLineNum + ": ");
                    System.out.print(Exce.getMessage());
                    if (Exce.getSal() > MAX_SALARY) {
                        System.out.println("Maximum Salary: $480,000 ");
                    } else if (Exce.getSal() < MIN_SALARY) {
                        System.out.println("Minimum Salary: $24,000");
                    }
                } catch (HourlyEmpException Exception) {
                    System.out.print("Line " + fileLineNum + ": ");
                    System.out.print(Exception.getMessage());
                    if (Exception.getHourlyPay() < MIN_HOURLY_PAY) {
                        System.out.println("Minimum Hourly Pay: $15");
                    } else if (Exception.getNumOfHoursWorked() > MAX_HOURLY_HOUR) {
                        System.out.println("Maximum Hours Worked: 70");
                    } else if (Exception.getNumOfHoursWorked() < MIN_HOURLY_HOUR) {
                        System.out.println("Minimum Hours Worked: 0");
                    }
                } catch (ComEmpException exc) {
                    System.out.print("Line " + fileLineNum + ": ");
                    System.out.print(exc.getMessage());
                    if (exc.getBaseWeekSal() < MIN_COM_BASE) {
                        System.out.println("Minimum Base Salary: $400");
                    } else if (exc.getComRate() > MAX_COM_RATE) {
                        System.out.println("Maximum Commission Rate: 20%");
                    } else if (exc.getComRate() < MIN_COM_BASE) {
                        System.out.println("Mini Commission Rate: 0%");
                    }

                } catch (EmpException ee) {
                System.out.print("Line " + fileLineNum + ": ");
                System.out.print(ee.getMessage());
                System.out.println(" Wrong type of employee: " + type);

            } catch(ArrayIndexOutOfBoundsException aioobe){
                    System.out.print("Line " + fileLineNum + ": ");
                    System.out.println("May have included space in data.");
                } catch(DuplicateEmpException d){
                    System.out.print("Line " + fileLineNum + ": ");
                    System.out.print(d.getMessage());
                }finally {
                    fileLineNum++;
                }

            
        }
        in.close();
        System.out.println("Data Read");
        

    }
   
    
    // writing data to file
    public void writeToFile() throws IOException
    {
        PrintWriter output;
        
        
        output = new PrintWriter(dataFile);

        for (Employee e: personnel)
        {
            output.println(e.getSaveString());
        }
        output.close();
    }

	public void update() {
		
        
        
        System.out.println ("Please enter the name of the employee");
        String id = kb.nextLine();
        
        Employee e = validateNum(id);
    
        if (e instanceof HourlyEmployee) {
        		HourlyEmployee he = (HourlyEmployee) e;
        		try {

                    System.out.println("Please enter the number of hours worked by " + e.getEmpName() + " this week");
                    double hrs = kb.nextDouble();
                    he.setHoursWorked(hrs);
                } catch (InputMismatchException ex) {
                    System.out.println("Employee was not updated: Expected a double value for hours worked.");
                }
        } if (e instanceof CommissionedEmployee) {
        		CommissionedEmployee ce = (CommissionedEmployee) e;
        		try {
                    System.out.println("Please enter this week's sales for " + e.getEmpName());
                    double w_sales = kb.nextDouble();
                    ce.setWklySales(w_sales);
                } catch (InputMismatchException exc) {
                    System.out.println("Employee was not updated: Expected a double value for this week's sales.");
                }
        }
         
        kb.nextLine();
  
		
	}
    
}
