package com.project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Employee {

	static String empId;
	static int ch;
	static String fName="";
	static String lName="";
	static String mob="";
	static String designation="";
	static String salary="";
	static String city="";

	static Scanner sc = new Scanner(System.in);
	static DataStoreHelp dsh = new DataStoreHelp();

	public Employee(String empId, String fName, String lName, String mob, String designation, String salary,
			String city) {
		Employee.empId = empId;
		Employee.fName = fName;
		Employee.lName = lName;
		Employee.mob = mob;
		Employee.designation = designation;
		Employee.salary = salary;
		Employee.city = city;
	}

	public static String empId() {
		String alphabets = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String digits = "0123456789";

		Random r = new Random();
		String generate = "";

		while (generate.length() != 7) {
			int rPick = r.nextInt(4);
			if (rPick == 1) {
				generate += alphabets.charAt(r.nextInt(25));
			} else if (rPick == 3) {
				generate += alphabets.charAt(r.nextInt(9));
			}
		}
		empId = "GIT_" + generate;
		System.out.println("\n Generated Employee ID : " + empId);
		return empId;
	}

	static String mobile() {
		System.out.println("\n Enter mobile number : ");
		mob = sc.next();
		if (mob.matches("\\d{10}")) {
			System.out.println("Mobile number is accepted");
		} else {
			System.out.println("Enter valid Mobile Number");
		}
		return mob;
	}

	static String select_desig() {
		do {
			try {
				System.out.println("-----------------------");
				System.out.println("Select your Designation");
				System.out.println("-----------------------");
				System.out.println("1. Software Engineer\n2. Consultant\n3. Manager\n4. Other");
				System.out.println("-----------------------");
				System.out.println("Enter Your Choice");
				ch = sc.nextInt();
			} catch (InputMismatchException ime) {
				System.out.println("\nWrong choice entered...Please try again");
				select_desig();
			}
			switch (ch) {
			case 1: {
				designation = "Software Engineer";
				salary = "85000";
				break;
			}
			case 2: {
				designation = "Consultant";
				salary = "45000.00";
				break;
			}
			case 3: {
				designation = "Manager";
				salary = "60000";
				break;
			}
			case 4: {
				designation = "Other";
				salary = "25000";
				break;
			}
			default:
				System.out.println("\nWrong choice entered...Please try again");
			}
		} while (ch != 1 & ch != 2 & ch != 3 & ch != 4);
		return designation;
	}

	static String city() {
		String regex = "^[a-zA-Z]+[ ]+$";
		System.out.println("\nEnter Your City ");
		city = sc.next();
		if (city.matches(regex) && city.length() >= 3) {
			System.out.println("City name is Accepted");
		} else {
			System.out.println("Invalid city \n(contains only alphabets not numbers)");
			city();
		}
		return city;
	}

	static String firstName() {
		String regex = "^[a-zA-Z]+$";
		System.out.println("\nEnter first name ");
		fName = sc.next();

		if (fName.matches(regex) && fName.length() >= 2) {
			System.out.println("First Name is Accepted");
		} else {
			System.out.println("Invalid First Name\n(Contains only alphabets)");
			firstName();
		}
		return fName;
	}

	static String lastName() {
		String regex = "^[a-zA-Z]+$";
		System.out.println("\nEnter last name ");
		lName = sc.next();

		if (lName.matches(regex) && lName.length() >= 2) {
			System.out.println("Last Name is Accepted");
		} else {
			System.out.println("Invalid Last Name\n(Contains only alphabets)");
			firstName();
		}
		return lName;
	}

	public static void mainMenu() throws Exception {
		do {
			try {
				System.out.println("-----------------------------");
				System.out.println("Welcome to Geeta IT Solutions");
				System.out.println("-----------------------------");
				System.out.println("1. Admin\n2. Sign In\n3. Sign Up\n4. exit");
				System.out.println("-----------------------------");
				System.out.println("Enter Your Choice ");
				ch = sc.nextInt();
			} catch (InputMismatchException ime) {
				System.out.println("\nWrong Choice Entered");
				mainMenu();
			}
			switch (ch) {
			case 1:

				System.out.println("\nEnter Username ");
				String uName = sc.next();
				System.out.println("\nEnter Password ");
				String password = sc.next();

				if (uName.equalsIgnoreCase("admin") && password.equalsIgnoreCase("admin@123")) {
					do {
						System.out.println("-------------------");
						System.out.println(
								"1. Display all employees\n2. Update Info\n3. Delete all account\n4. Go to Main Menu");
						System.out.println("--------------------");
						System.out.println("Enter Your Choice ");
						try {
							ch = sc.nextInt();
						} catch (InputMismatchException ime) {
							System.out.println("\nWrong choice entered...Please try again");
						}
						switch (ch) {
						case 1:

							try {
								dsh.viewAllEmployee(empId);
							} catch (Exception e) {
								System.out.println(e);
							}
							break;
						case 2:
							break;
						case 3:
							try {
								dsh.dropAllEmployee();
							} catch (Exception e) {
								System.out.println(e);
							}
							break;
						case 4:
							mainMenu();
						default:
							System.out.println("\nWrong choice entered...Please try again");
						}
					} while (ch != 4);
				} else {
					System.out.println("\nWrong Username or Password");
					mainMenu();
				}
				break;
			case 2:
				System.out.println("\nEnter Employee ID");
				String emp = sc.next();

				if (emp.equals(empId)) {
					do {
						System.out.println("--------------------");
						System.out.println("1. Display Info\n2. Update Info\n3. Remove Account\n4. Go to Main Menu");
						System.out.println("--------------------");
						System.out.println("Enter Your Choice");

						try {
							ch = sc.nextInt();
						} catch (InputMismatchException ime) {
							System.out.println("\nWrong Choice Entered...Please try again");
						}
						switch (ch) {
						case 1:
							try {
								dsh.viewEmployee(empId);
							} catch (Exception e) {
								System.out.println(e);
							}
							break;
						case 2:
							dsh.updateEmployee(empId);
							break;
						case 3:
							dsh.removeEmployee(empId);
							break;
						case 4:
							mainMenu();
						default:
							System.out.println("\nWrong Choice Entered...Please try again");
						}
					} while (ch != 4);
				} else {
					mainMenu();
				}
				System.out.println(empId);
				break;
			case 3:
				empId();
				firstName();
				lastName();
				mobile();
				select_desig();
				city();

				Employee e = new Employee(empId, fName, lName, mob, designation, salary, city);
				try {
					dsh.addEmployee(e);
				} catch (Exception e2) {
					System.out.println(e2);
				}
				break;
			case 4:
				System.exit(0);
			default:
				System.out.println("\nWrong choice entered...Please try again");
			}
		} while (ch != 4);
	}

	public static void main(String[] args) throws Exception {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Please load mysql jdbc driver");
			e.printStackTrace();
			return;
		}
		Connection con;
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/connection", "root", "root");
			System.out.println("Connection established");
		} catch (SQLException e) {
			System.out.println("Connection failed");
			e.printStackTrace();
			return;
		}
		Statement stmt = con.createStatement();
		stmt.executeUpdate("create table employees(empId varchar(20) primary key,fName varchar(20),lName varchar(20),Mob varchar(20),designation varchar(20),salary int(20),city varchar(15))");
		System.out.println("Employees table created successfully...");
		mainMenu();
	}
}
