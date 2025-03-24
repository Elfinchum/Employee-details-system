package com.project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Scanner;

public class DataStoreHelp {

	public void addEmployee(Employee e) throws Exception {
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/connection", "root", "root");
		Statement statement = con.createStatement();
		statement.executeUpdate("insert into Employees values ('" + Employee.empId + "','" + Employee.fName + "','"
				+ Employee.lName + "','" + Employee.mob + "','" + Employee.designation + "','" + Employee.salary + "','"
				+ Employee.city + "')");
		System.out.println("\nValues Inserted Successfully");
	}

	public void viewEmployee(String empId) throws Exception {
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/connection", "root", "root");
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("select * from employees where empId='" + empId + "'");
		if (rs.next() == true) {
			System.out.println(rs.getString(2) + "'s Information");
			System.out.println("--------------");
			System.out.println("EmpID : " + rs.getString(1) + "\nFirst Name : " + rs.getString(2) + "\nLast Name : "
					+ rs.getString(3) + "\nMobile no. : " + rs.getString(4) + "\nDesignation : " + rs.getString(5)
					+ "\nCurrent salary : " + rs.getString(6) + "\nCity : " + rs.getString(7));
		}else {
			System.out.println("Record not found");
		}
	}
	
	public void viewAllEmployee(String empId) {
		int count = 0;
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/connection", "root", "root");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("select * from employees");
			System.out.println("-----------------------");
			while(rs.next()) {
				System.out.println("EmpId : "+rs.getString(1)+"\nFirst Name : "+rs.getString(2)+ "\nLast Name : "
						+ rs.getString(3) + "\nMobile no. : " + rs.getString(4) + "\nDesignation : " + rs.getString(5)
						+ "\nCurrent salary : " + rs.getString(6) + "\nCity : " + rs.getString(7));
				System.out.println("-----------------");
				count++;
			}
			System.out.println("Total number of Records : "+count );
		} catch (Exception e) {
			System.out.println("Database is empty...");
		}
	}
	
	public void removeEmployee(String empId) throws Exception {
		
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/connection", "root", "root");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("delete * from employees where empId='"+empId+"'");
			System.out.println("Employee removed successfully...");	
	}
	
	public void updateEmployee(String empId) throws Exception {
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/connection", "root", "root");
		Statement stmt = con.createStatement();
		Scanner sc = new Scanner(System.in);
		int ch;
		try {
			System.out.println("-----------------");
			System.out.println("Update your information");
			System.out.println("-----------------");
			System.out.println("1. Mobile no.\n2. Designation\n3. City");
			System.out.println("-----------------");
			ch = sc.nextInt();
			switch (ch) {
			case 1:
				String mob = Employee.mobile();
				stmt.executeUpdate("Update employees set mob='"+mob+"'where empId='"+empId+"'");
				break;
			case 2:
				String designation = Employee.select_desig();
				stmt.executeUpdate("Update employees set designation='"+designation+"' where empId='"+empId+"'");
				break;
			case 3:
				String city = Employee.city();
				stmt.executeUpdate("Update employees set city='"+city+"' where empId='"+empId+"'");
				break;
			default:
				System.out.println("\nWrong choice entered...Pleare try again");
			}
		} catch (InputMismatchException ime) {
			System.out.println("Entered wrong choice...Please try again");
			Employee.select_desig();
		}
		System.out.println("Record Updated Successfully");
	}
	
	public void updateEmployeeDesig(String empId,String designation) throws Exception {
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/connection", "root", "root");
		con.createStatement();
		System.out.println("Record Updated Successfully");
	}
	
	public void dropAllEmployee() throws Exception {
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/connection", "root", "root");
		Statement stmt = con.createStatement();
		stmt.executeUpdate("Drop table employees");
		System.out.println("All Employees Removed Successfully");
	}
}
