/**
 * ����һ��Rodent��ϵ��Mouse, Gerbil, Hamster, �ȵȡ��ڻ����ж�������Rodent���еķ�����Ȼ��������������
   Rodent�ľ������͸�д��Щ���������ṩ��ͬ����Ϊ������һ��Rodent�����飬�����ø��־����Rodent����������飬
   Ȼ����û���ķ����������������еĽ��
 */
package com.cisco.rekan.classes;

public class TestRodent {

	private static java.util.Random rand = new java.util.Random();
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Rodent[] r = new Rodent[9];
		for (int i=0; i<9; i++)
			r[i] = randRodent();
		for (int i=0; i<9; i++) {
			r[i].getKind();
			r[i].getColour();
		}
	}

	public static Rodent randRodent() {
	    switch(rand.nextInt(3)) {
	    	default:
	    	case 0: return new Mouse();
	    	case 1: return new Gerbil();
	    	case 2: return new Hamster();
	    }
	}
	
}

abstract class Rodent {
	abstract void getKind();
	abstract void getColour();
}

class Mouse extends Rodent {
	void getKind() {
		System.out.println("Mouse");
	}
	
	void getColour() {
		System.out.println("Mouse's colour");
	}
}

class Gerbil extends Rodent {
	void getKind() {
		System.out.println("Gerbil");
	}
	
	void getColour() {
		System.out.println("Gerbil's colour");
	}
}

class Hamster extends Rodent {
	void getKind() {
		System.out.println("Hamster");
	}
	
	void getColour() {
		System.out.println("Hamster's colour");
	}
}
