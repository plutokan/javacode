/*
 * http://www.ibm.com/developerworks/cn/java/j-enums.html
 * 
 */
package com.cisco.rekan.enumtype;

import java.io.IOException;
import java.io.PrintStream;

enum Grade {
    A, B, C, D, F, INCOMPLETE
};

public class Student {
	  private String firstName;
	  private String lastName;
	  private Grade grade;
	  public Student(String firstName, String lastName) {
	    this.firstName = firstName;
	    this.lastName = lastName;
	  }
	  public void setFirstName(String firstName) {
	    this.firstName = firstName;
	  }
	  public String getFirstName() {
	    return firstName;
	  }
	  public void setLastName(String lastName) {
	    this.lastName = lastName;
	  }
	  public String getLastName() {
	    return lastName;
	  }
	  public String getFullName() {
	    return new StringBuffer(firstName)
	           .append(" ")
	           .append(lastName)
	           .toString();
	  }
	  public void assignGrade(Grade grade) {
	    this.grade = grade;
	  }
	  public Grade getGrade() {
	    return grade;
	  }
	  public static void listGradeValues(PrintStream out) throws IOException {
		  for (Grade g : Grade.values()) {
		    out.println("Allowed value: '" + g + "'");
		  }
	}

	  public void testSwitchStatement(PrintStream out, Student student1) throws IOException {
		  StringBuffer outputText = new StringBuffer(student1.getFullName());
		  switch (student1.getGrade()) {
		    case A: 
		      outputText.append(" excelled with a grade of A");
		      break;   
		    case B: // fall through to C
		    case C: 
		      outputText.append(" passed with a grade of ")
		                .append(student1.getGrade().toString());
		      break;
		    case D: // fall through to F
		    case F:
		      outputText.append(" failed with a grade of ")
		                .append(student1.getGrade().toString());
		      break;
		    case INCOMPLETE:
		      outputText.append(" did not complete the class.");
		      break;
		    
		        default:
		      outputText.append(" has a grade of ")
		                .append(student1.getGrade().toString());
		      break;
		  }
		  out.println(outputText.toString());
		}
	
    public static void main(String[] args) {
    	Student s = new Student("Pluto", "Kan");
    	s.assignGrade(Grade.C);
    	try {
    		listGradeValues(System.out);
    		s.testSwitchStatement(System.out, s);
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    	
    }
}