package com.cisco.rekan.thread;

class A
{
	synchronized void foo(B b)
	{
		String name=Thread.currentThread().getName();
		System.out.println(name + " entered A.foo ");
		try
		{
			Thread.sleep(1000);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		System.out.println(name + " trying to call B.last()");
		
		//b.last();
		
	}
	synchronized void last()
	{
		System.out.println("inside A.last ");
	}
}

class B extends Thread
{
	
	synchronized void bar(A a)
	{
		String name = Thread.currentThread().getName();
		System.out.println(name + " entered B.bar");
		try 
		{
			Thread.sleep(1000);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		System.out.println(name + " trying to call A.last()");
		
		//a.last();
	}
	synchronized  void last()
	{
		System.out.println("inside B.last ");
	}
}

public class Deadlock2
{

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		final A a=new A();
		final B b=new B();
		new Thread() {
			public void run() {
				setName("MainThread");
				b.bar(a);
				System.out.println("back in main thread");
			}
		}.start();
		new Thread() {
			public void run() {
				setName("RacingThread");
				a.foo(b);
				System.out.println("back in other thread");
			}
		}.start();
	}

}
