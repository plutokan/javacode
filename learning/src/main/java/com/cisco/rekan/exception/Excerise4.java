/**
 * Java Reading Party - Excerise 4
 * ����һ����f()��g()�������������ࡣ��g()�����׳�һ�����¶�����쳣��
 * ��f()����g()��׽���쳣֮��Ҫ��catch�Ӿ����׳���һ���쳣���㶨��ĵڶ����쳣������main()�����ԡ�
 * 
 * �ظ�ǰһ����ϰ��������catch�Ӿ��Ҫ��RuntimeException��g()���쳣��������
 */
package com.cisco.rekan.exception;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

public class Excerise4 {

    /**
     * Get detail exception message from Throwable class.
     * 
     * @param e Throwable
     * @return e.printStaceTrace()
     * @author <a:href mailto="pluto@hf.webex.com">Pluto Kan</a>
     * @since GW 3.3, 08/12/2008
     */
    public static String getStackMsg(Throwable e) {
        OutputStream os = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(os, true); 
        e.printStackTrace(ps);
        String msg = os.toString();
        ps.close();
        try {
            os.close();
        } catch (IOException e1) {
            System.out.println(e);
        }
        
        return msg;
    }

	public void g() throws Exception2 {
		throw new Exception2();
	}
	
	public void f() {
		try {
			g();
		} catch (Exception2 e) {
			e.printStackTrace(System.out);
			throw new Exception3(e);
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Excerise4 exce = new Excerise4();
		try {
			exce.f();
		} catch (Exception3 e) {
            System.out.println();
            System.out.println(e.getMessage());
            System.out.println();
            System.out.println(e.toString());
            System.out.println();
			e.printStackTrace(System.out);
            System.out.println();
            for (StackTraceElement ste : e.getStackTrace()) {
                System.out.println(ste.toString());
            }
            System.out.println();
            System.out.println(getStackMsg(e));
		}
	}

}

@SuppressWarnings("serial")
class Exception2 extends Exception {
	
}

@SuppressWarnings("serial")
class Exception3 extends RuntimeException {
	Exception3(Exception2 e) {
		super(e);
	}
}
