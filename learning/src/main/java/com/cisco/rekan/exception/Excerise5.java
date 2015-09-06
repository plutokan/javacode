/**
 * Java Reading Party - Excerise 5
 * ����һ�����̳е��쳣��ϵ��Ȼ�󴴽�һ�������׳������쳣���ķ�������A��
 * ��B�̳�A�����Ҹ�д��������������׳��ڶ�����쳣��������ȥ����C�̳�B�� 
 * ��main()���洴��һ��C�Ķ����ϴ���A��Ȼ������������
 */
package com.cisco.rekan.exception;

public class Excerise5 {

	@SuppressWarnings("serial")
	class EA extends Exception {
		EA(String msg) {
			super(msg);
			System.out.println("EA");
		}
	}
	
	@SuppressWarnings("serial")
	class EB extends EA {
		EB(String msg) {
			super(msg);
			System.out.println("EB");
		}
	}
	
	@SuppressWarnings("serial")
	class EC extends EB {
		EC(String msg) {
			super(msg);
			System.out.println("EC");
		}
	}
	
	class A {
		public void testThrow(String msg) throws EA{
			throw new EA(msg);
		}
	}
	
	class B extends A {
		public void testThrow(String msg) throws EB{
			throw new EB(msg);
		}
	}
	
	class C extends B {
		public void testThrow(String msg) throws EC{
			throw new EC(msg);
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Excerise5.A a = new Excerise5().new C();
		try {
			a.testThrow("haha");
		} catch (EA e) {
			e.printStackTrace();
		}
	}

}
