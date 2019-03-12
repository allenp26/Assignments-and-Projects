### Note : Did not include Employee, Salary, Hourly, and Commission class since majority of processing is done in UserInterface and payrollProessor class

## UserInterface
Test Case | Input | Output
----------|----------|----------
Pass in the data file with the wrong name | pass in "X" into loadData| FileNotFoundException, ask for file name again
Pass in the data file with the correct name | pass in "data" into loadData | Read in & populate arrayList accordingly
Pass in invalid choice | type in "X" into handleUsrSelction | error message, prompt for user input again
Pass in valid choice | type in "A", "UH", "UC", "D", "P", "I", "T", "W", or "Q" to quit program | responding methodss should be called, addEmp, weeklyHourEntry, weeklySalesEntry, deleteEmp, printAllEmpData, printEmpData, printTopThreeCommissionEmp, weeklyUpdater, or exit program
Pass in invalid input to quit program | type in "X" into usrChooseToCont | return false
Pass in valid input to quit program | type in "Q" into usrChooseToCont | return true
Pass in invalid employee type | type in "X" into addEmp | empTypeValidator return false, back to menu 
Pass in valid employee type | type in "S", "H", "C" into addEmp | empTypeValidator return true, call responding methods, requestSalaryEmpInfo, requestHourlyEmpInfo, requestCommissionEmpInfo
Pass in invalid choice to delete employee from payroll | type in "X" into requestUsrAction | return false 
Pass in valid choice to delete employee from payroll | type in "Yes" into requestUsrAction | return true
Pass in invalid employee id, empIdValidator | type in "X" | return false
Pass in valid employee id, empIdValidator | type in "111-111-111" | return true
Pass in valid employee details, requestSalaryEmpInfo | type in "Allen, 444-444-444, Finance, 60000" | add employee to empList with details accordingly
Pass in invalid employee details, requestSalaryEmpInfo | type in "Allen, 444-444-444, Finance, x" | inputMismatchException, prompt user to enter Annual Salary again
Pass in valid employee details, requestHourlyEmpInfo | type in "Allen, 444-444-444, Finance, 15, 10, 10" | add employee to empList with details accordingly
Pass in invalid employee details, requestHourlyEmpInfo | type in "Allen, 444-444-444, Finance, x, 10, 10" | inputMismatchException, prompt user to enter hourly pay again
Pass in valid employee details, requestCommissionEmpInfo | type in "Allen, 444-444-444, Finance, 500, 10, 50000, 2.5, 10" | add employee to empList with details accordingly 
Pass in invalid employee details, requestCommissionEmpInfo | type in "Allen, 444-444-444, Finance, x, 10, 50000, 2.5, 10" | inputMismatchException, prompt user for base weekly salary again
Pass in valid hourly employee id number and hours worked this week, weeklyHourEntry  | type in "333-333-333" and "50" | employee, with id number "333-333-333", hours worked this week changes to "50"
Pass in invalid hourly employee id nubmer and hours worked this week, weeklyHourEntry | type in "x" and "50" | hourlyEmpValidator return false thus nested if loop does not execute
Pass in valid hourly employee id number, hourlyEmpValidator | type in "333-333-333" | return true 
Pass in invalid hourly emoloyee id number, hourlyEmpValidator | type in "x" | print: This employee is not an hourly employee, return false
Pass in valid commission employee id number, commissionedEmpValidator | type in "111-111-111" | return true
Pass in invalid commission employee id number, commissionedEmpValidator | type in "x" | print: This employee is not a commissioned employee, return false
Pass in valid commission employee id number with number of sales, weeklySalesEntry | type in "111-111-111" and "20" | employee with id number "111-111-11" number of sales this week changes to "20"
Pass in invalid commission employee id with number of sales, weeklySalesEntry | type in "X" and "20" | commissionedEmpValidator return false thus nested if look does not execute 
Pass in valid employee id, deleteEmp | type in "111-111-111" | method, requestUsrAction, should be called
pass in invalid employee id, deleteEmp | type in "x" | does nothing, since empValidator return false thus nested if loop does not execute 
Pass in valid employee id, printEmpData | type in "111-111-111" | call printEmpDetail method in payrollProcessor class which prints out the employee's detail

## PayrollProcessor
Test Case | Input | Output
----------|----------|----------
Pass in valid employee data strings | pass in "Sally, 333-333-333, Finance, H, 15.75, 0.0, 10" into loadData | add employee to arrayList based on empType, index 3, which include "S", "H", "C"
Pass in invalid employee data strings | pass in "Sally, 333-333-333, H, 15.75, 0.0, 10" | inputMismatchException, skip to next line in data file, print: "Cannot parse Line X: Sally, "333-333-333, H, 15.75, 0.0, 10" missing employee department"
Pass in valid employee department, based on enum variables | pass in "Finance", "Appliances", or "Human Resources" into findEmpDepartment | return matching enum variable names, "FINANCE", "APPLIANCES", "HUMAN_RESOURCES"
Pass in invalid employee department | pass in "X" into findEmpDepartment | return ""
Pass in valid employee details, adding salary employee | pass in "Arnie, 222-222-222, Human Resources, 52000.00" | empList now has new emplyee data
Pass in invalid employee details, adding salary employee | pass in "Arnie, 222-222-222, Human Resources" | throws inputMismatchException
Pass in valid employee details, adding hourly employee | pass in "Sally, 333-333-333, Finance, 15.75, 0.0, 10" | empList should now have new employee data
Pass in invalid employee details, adding hourly employee | pass in "333-333-333, Finance, H, 15.75, 0.0, 10" | throws inputMismatchException 
Pass in vaid employee details, adding commission employee | pass in "Bobby, 111-111-111, Appliances, 300.0, 0.0, 10000.0, 0.025, 10" | empList should now have new employee data
Pass in invalid employee details, adding commission employee | pass in "111-111-111, Appliances, C, 300.0, 0.0, 10000.0, 0.025, 10" | throws inputMismatchException
Pass in valid employee type, empTypeValidator | pass in "S" | return true
Pass in invalid employee type, empTypeValidator | pass in "X" | return false
Pass in valid employee id, within empLIst, empIdValidator | pass in "111-111-111" | return true
Pass in invalid employee id, empIdValidator | pass in "0" | return false
Pass in hourly employee id, hourlyEmpValidator | pass in "333-333-333" | return true 
Pass in wrong employee id, hourlyEmpValidator | pass in "0" | return false
Pass in commission employee id, commissionEmpValidator | pass in "111-111-111" | return true 
Pass in wrong employee id, commissionEmpValidator | pass in "0" | return false
Pass in valid employee id, removeEmp | pass in "111-111-111" | remove employee data with matching employee id in the empList
Pass in invalid employee id, removeEmp | pass in "0" | does nothing, since empIdValidator should return false, thus this method will not run
Pass in valid employee id, printEmpDetail | pass in "111-111-111" | print: Employee Name: Bobby Employee ID: 111-111-111 Employee department: Appliances Base Weekly Salary: 300 Sales this week: 0 Year total sales: 10000 Commission rate: 0.025 Number of weeks worked: 10
Print all employee's detials | run printAllEmpDetail | should print all employee with matching details 
Pass in valid employee and number of hours, setEmpHoursWorked | pass in "333-333-333", "50" | employee, with id number "333-333-333", number of hours worked should change to "50"
Pass in invalid employee id and number of hours, setEmpHoursWorked | pass in "333", "50" | do nothing, since hourlyEmpValidator should return false thus this method will not run
Pass in valid employee id and sales this week, setEmpWeeklySales | pass in "111-111-111", "100" |  employee, with id number "111-111-111", weekly sales changes to "100"
Pass in invalid employee id and sales this week, setEmpWeeklySales | pass in "1", "100" | do nothing since no such id, "1", is in the list
