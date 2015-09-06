package com.cisco.rekan.classes;

public class Shape {

	public void erase() {
		System.out.println("Shape erase.");
	}
	
	public void draw() {
		System.out.println("Shape draw.");
	}
	
	public static void doStuff(Shape s) {
		s.erase();
		s.draw();
	}
	
	public static void main(String args[]){
		doStuff(new Circle());
		doStuff(new Triangle());
		doStuff(new Line());
		
		Shape s = new Circle();
		doStuff(s);
	}
}

class Circle extends Shape {
	
	public void erase() {
		System.out.println("Circle erase.");
	}
	
	public void draw() {
		System.out.println("Circle draw.");
	}
	
}

class Triangle extends Shape {
	
	public void erase() {
		System.out.println("Triangle erase.");
	}
	
	public void draw() {
		System.out.println("Triangle draw.");
	}
	
}

class Line extends Shape {
	
	//public void erase() {
	//	System.out.println("Line erase.");
	//}
	
	//public void draw() {
	//	System.out.println("Line draw.");
	//}
	
}
