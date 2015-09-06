package com.cisco.rekan.classes;

public class InterfaceMain {

	public static void doStuff(Shape3 s) {
		s.erase();
		s.draw();
	}
	
	/**
	 * @param args
	 */
	public static void main(String args[]){
		// Why the next line code can not compile?
		//doStuff(new Shape2());
		//doStuff(new Shape3());
		doStuff(new Circle3());
		doStuff(new Triangle3());
		doStuff(new Line3());
	}

}

interface Shape3 {

	// !We can not define method in interface, only can declare.
	//public void erase() {
	//	System.out.println("Shape3 erase.");
	//}
	
	//public void draw() {
	//	System.out.println("Shape3 draw.");
	//}
	
	// Why we do not need the modifiers "abstract public"?
	/* abstract public */ void erase();
	/* abstract public */ void draw();
}

class Circle3 implements Shape3 {
	
	public void erase() {
		System.out.println("Circle3 erase.");
	}
	
	public void draw() {
		System.out.println("Circle3 draw.");
	}
	
}

class Triangle3 implements Shape3 {
	
	public void erase() {
		System.out.println("Triangle3 erase.");
	}
	
	public void draw() {
		System.out.println("Triangle3 draw.");
	}
	
}

class Line3 implements Shape3 {
	
	public void erase() {
		System.out.println("Line3 erase.");
	}
	
	public void draw() {
		System.out.println("Line3 draw.");
	}
	
}
