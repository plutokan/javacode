/**
 * Java Reading Exercise 2
 * ���ո��� Lunch.java дһ�� ConnectionManager �࣬Ȼ������ȥ����һ��̶������� Connection ����
   Ҫ�������ͻ�����Ա����ֱ�Ӵ�������ֻ��ͨ�� ConnectionManager �� static ��������ȡ Connection ����
   �� ConnectionManager �޶���ɷ����ʱ����᷵�� null �� reference ���� main() �����ԡ�
 */

package com.cisco.rekan.classes;

public class ConnectionManager {
	
	Connection[] al;
	int num;
	
	public ConnectionManager(int num) {
		this.num = num;
		al = new Connection[this.num];
		for (int i=0; i<this.num; i++) {
			al[i] = Connection.makeConn();
		}
	}
	
	public Connection getConn() {
		for (int i=0; i<num; i++) {
			if (! al[i].flag) {
				al[i].flag = true;
				return al[i];
			}
		}
		return null;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ConnectionManager cm = new ConnectionManager(4);
		for (int i=0; i<5; i++) {
			Connection conn = cm.getConn();
			if (conn == null) {
				System.out.println("Get a null!" + i);
			} else {
				System.out.println(conn);
			}
		}
	}

}

class Connection {
	private static int count = 0;
	protected boolean flag;
	
	private Connection() {
		flag = false;
		count++;
		System.out.println("Connection constructor " + count);
	}
	
	public String toString() {
		return "Connection:" + count + ".";
	}
	
	public static Connection makeConn() {
		return new Connection();
	}
}