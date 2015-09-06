package com.cisco.rekan.classes;
import java.text.SimpleDateFormat;
import java.lang.Integer;
import java.util.*;

class Employee{
	public Employee(String n,double s, Date d){
		name = n;
		salary = s;
		hireDay = d;
	}
	
	public void print(){
		System.out.println(name+" " + salary +" " + hireYear());
	}
	
	public void raiseSalary(double byPercent){
		salary *= 1 + byPercent/100;
	}
	
	@SuppressWarnings("deprecation")
	public int hireYear(){
		return hireDay.getYear();
	}
	
	private String name;
	private double salary;
	private Date hireDay;
		
}

class Manager extends Employee{
	public Manager(String n,double s,Date d){
		super(n,s,d);
		secretaryName = "";
	}
	
	public void raiseSalary(double byPercent){
		// add 50% bonus for every year of service
		Date today = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
		String dateString = formatter.format(today);
		System.out.println("date:"+dateString);
		int thisYear = Integer.parseInt(dateString.substring(0,4));
		double bonus = 0.5*(thisYear - hireYear());
		System.out.println("today.getYear()="+thisYear+" and hireYear()= "+hireYear() );
		super.raiseSalary(byPercent + bonus);
	}
	
	public String getSecretaryName(){
		return secretaryName;
	}
	
	public void setSecretaryName(String name){
		secretaryName = name;
	}
	
	private String secretaryName;
}

public class ManagerTest{
	@SuppressWarnings("deprecation")
	public static void main(String[] args){
		Manager boss = new Manager("Harry Hacker",35000,new Date(1990,10,1));
		boss.setSecretaryName("Harry Hacker");
		
		Employee[] staff = new Employee[3];
		staff[0] = boss;
		staff[1] = new Employee("Carl Cracker",75000,new Date(1987,12,15));
		staff[2] = new Employee("Tony Tester",38000,new Date(2002,3,15));
		
		int i;
		for (i = 0;i<3; i++) staff[i].raiseSalary(5);
		for (i = 0;i<3; i++) staff[i].print();
		
		System.out.println("The department secretary is :" + boss.getSecretaryName());
	}
}