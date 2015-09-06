/**
 * ���� string �� == �� equal ���
 * 1. "=="���������ߵ�������ͱ�����һ�µĻ������ϵ��equals����Ҫ��
 * 2. int a=10 �� long b=10L �� double c=10.0������ͬ�ģ�==Ϊtrue����
 *    ��Ϊ���Ƕ�ָ���ַΪ10�Ķ�ջ��
 * @author rhkan
 *
 */
package com.cisco.rekan;

public class TestEquals {
	private int a;
	private int b;

	/* Object �е� equals ֱ�� (return (this == obj)) */
	private boolean equals(TestEquals a2) {
		if ((this.a== a2.a) && (this.b == a2.b)) 
			return true;
		return false;
	}
	
	public static void main(String[] args) {
		String s1 = "abc";
		String s2 = "abc";
		// ���� s1 �� s2 ����ʹ��new�����ģ�
		// ����ָ���ڴ���е�ͬһ���ַ�����������ַʵ��������ͬ��
		System.out.println(s1 == s2);		// true!!!!!!!
		// String ����� equals �����Ѿ����ǣ�ֻ�Ƚ�ֵ
		System.out.println(s1.equals(s2));	// true
		
		String s3 = new String("def");
		String s4 = new String("def");
		System.out.println(s3 == s4);		// false
		System.out.println(s3.equals(s4));	// true
		
		TestEquals a1 = new TestEquals();
		a1.a = 3;
		a1.b = 4;
		TestEquals a2 = new TestEquals();
		a2.a = 3;
		a2.b = 4;
		TestEquals a3 = a1;
		System.out.println(a1==a2);			// false
		System.out.println(a1==a3);			// true
		System.out.println(a1.equals(a2));	// true
		System.out.println(a1.equals(a3));	// true
	}
	
}
