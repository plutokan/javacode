package com.cisco.rekan.GUIs;
/**	��дһ�������Java Applet����,
	ʹ�ø�����Complex��֤��������1+2i��3+4i ��Ӳ���һ���µĸ���4+6i��  
	(1)������Complex�������У�
	RealPart: int�ͣ���?���ʵ���;
	ImaginPart:  int�ͣ���?��������.
	(2)������Complex�ķ����У�
	Complex(): ���캯������ʵ�����鲿����0;
	Complex(int r,int i) : ���캯���β�rΪʵ���ĳ�ֵ��iΪ�鲿�ĳ�ֵ;
	Complex complexAdd(Complex a): ����ǰ����������βθ��������ӣ���õĽ������һ������ֵ�����ظ�˷����ĵ�����;
	String ToString(): �ѵ�ǰ��������ʵ�����鲿��ϳ�a+bi���ַ���ʽ������a��b�ֱ�Ϊʵ�����鲿����ݡ�
*/


/*	Application
public class Exer02
{	public static void main(String[] arg)
	{	Complex a=new Complex(1,2);
		Complex b=new Complex(3,4);
		System.out.println("a="+a.toString());
		System.out.println("b="+b.toString());

		a.complexAdd(b);
		System.out.println("a+b="+a.toString());
	}
}
*/

//	Applet
import java.awt.Graphics;
import java.applet.Applet; 

public class Exer02 extends Applet
{	/**
	 * 
	 */
	private static final long serialVersionUID = -7327791393527016091L;

	Complex a = new Complex(1,2);
	Complex b = new Complex(3,4);
	String astring,bstring,cstring;
	
	public void init(){
		astring=a.toString();
		bstring=b.toString();
		a.complexAdd(b);
		cstring=a.toString();
	}

	public void paint(Graphics g){
		g.drawString(astring+"  +"+bstring+"   ="+cstring,100,100);
	}
}

class Complex{
	int realpart;
	int imaginpart;
	
	public Complex(){
		realpart=0;
		imaginpart=0;
	}
	
	public Complex(int r,int i){
		realpart=r;
		imaginpart=i;
	}
	
	public Complex complexAdd(Complex a){
		realpart+=a.realpart;
		imaginpart+=a.imaginpart;
		return this;
	}
	
	public String toString(){
		String comstr=null;
		comstr=realpart+"+"+imaginpart+"i";
		return comstr;
	}
}