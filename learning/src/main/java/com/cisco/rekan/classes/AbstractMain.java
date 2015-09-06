package com.cisco.rekan.classes;

public class AbstractMain {

	public static void doStuff(Shape2 s) {
		s.erase();
		s.draw();
	}
	
	/**
	 * @param args
	 */
	public static void main(String args[]){
		// Why the next line code can not compile?
		//doStuff(new Shape2());
		doStuff(new Circle2());
		doStuff(new Triangle2());
		//doStuff(new Line2());
	}

}

abstract class Shape2 {

	//public void erase() {
	//	System.out.println("Shape2 erase.");
	//}
	
	//public void draw() {
	//	System.out.println("Shape2 draw.");
	//}
	
	abstract public void erase();
	abstract public void draw();
}

class Circle2 extends Shape2 {
	
	public void erase() {
		System.out.println("Circle2 erase.");
	}
	
	public void draw() {
		System.out.println("Circle2 draw.");
	}
	
}

class Triangle2 extends Shape2 {
	
	public void erase() {
		System.out.println("Triangle2 erase.");
	}
	
	public void draw() {
		System.out.println("Triangle2 draw.");
	}
	
}

abstract class Line2 extends Shape2 {
	
	//public void erase() {
	//	System.out.println("Line2 erase.");
	//}
	
	public void draw() {
		System.out.println("Line2 draw.");
	}
	
}
