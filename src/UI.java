import java.io.*;
import java.util.*;

public class UI
{
    public static void main(String args[]) throws IOException
    {
        // define necessary variables here
    		boolean done = false;
        String fileName ="";
        String ans;
        String choice = "";
        Scanner kb = new Scanner(System.in);
        boolean fileCheck = false;

        do {
            try {
                System.out.print("Please enter the data filename: ");
                fileName = kb.nextLine();

                if (fileName.isEmpty()) {
                    fileName = "employee.data";
                }

                Payroll pay = new Payroll(new File(fileName));
                fileCheck = true;
            }
            catch (FileNotFoundException e) {
                if (fileName.equalsIgnoreCase("q")) {
                    break;
                }
                System.out.println("Not able to open file " + fileName);
                System.out.println("Please try again or enter \"Q\" to exit the program \n");
            }
        } while (!fileCheck);


        System.out.println("Ready:");
        Payroll pay = new Payroll(new File(fileName));
        while (!done)
        {
        		System.out.println("Menu:");
            showMenu();
            System.out.println ("Please enter your choice");
            ans = kb.nextLine();
            choice = ans.toUpperCase();

            switch (choice)
            {
                case "A":
                    pay.addEmp();
                    break;
                case "Z":
                		pay.printAll();
                		break;
                case "I":
                    pay.printInfo();
                    break;
                case "U":
                		pay.update();
                		break;
                case "D":
                    pay.remove();
                    break;
                case "S":
                    pay.printWklySal();
                    break;
                case "T":
                    pay.printTopSellers(3);
                    break;
                case "P":
                    pay.salaryRpt();
                    break;
                case "W":
                    pay.endOfWeek();
                    break;
                case "Q":
                		done = true;
                    break;
                default:
                    System.out.println ("That is not a valid choice.  Please choose again");
            }

        }

        pay.writeToFile();
        kb.close();
        System.out.println ("Thank you for using the Payroll Processing System");
    }
   
      
    //   The Payroll processing menu
     
    public static void showMenu()
    {
        System.out.println("\nMENU:");
        System.out.println("A - Add a new employee");
        System.out.println("I - Print information of an individual employee");
        System.out.println("Z - Print all employee information");
        System.out.println("U - Update an employee's information");
        System.out.println("D - Remove an employee from payroll");
        System.out.println("S - Calculate and print the weekly pay of an employee");
        System.out.println("T - Top Sellers list");
        System.out.println("P - Print salary report");
        System.out.println("W - End of week processing");
        System.out.println();
        System.out.println("Q - Quit the system");
    }

}