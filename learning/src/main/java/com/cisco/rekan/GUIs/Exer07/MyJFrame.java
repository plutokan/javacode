/**
 * ����������ǰѧϰ��֪ʶ, ��һ��������ʾ��ɾ�� Access ��ݱ��м�¼�Ĵ���, 
 * ������һ�����ؼ� ( javax.swing.JTable ) ��һ��ɾ��ť.
 * Ҫ��, ������ʾ��ʱ��, ͨ�� JDBC ����ݿ��еļ�¼��������ʾ, 
 * ��ѡ����ĳһ����ݵ�ʱ��, �� "ɾ��" ��ť���԰�ѡ�еļ�¼ɾ��.
 */
package com.cisco.rekan.GUIs.Exer07;

import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionListener;
import java.awt.Container;
import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;

import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import java.util.Vector;

@SuppressWarnings("serial")
public class MyJFrame extends JFrame {
	private JTable myJTable;
	private JButton deleteJButton;
	private JScrollPane sPane;
	private Container c;
	private ConnectToAccess MY_Connection;
	
	private int selectedRow = 0;
	private String selectedCode = "";
	
	public MyJFrame() {
		super("Java Exercise 7");

		MY_Connection = new ConnectToAccess();
		
		myJTable = addResultSetToJTable(MY_Connection.selectQuery());
		
		deleteJButton = new JButton("ɾ��");
		deleteJButton.setEnabled(false);
		deleteJButton.addActionListener(new myActionListener());
        
		sPane = new JScrollPane(myJTable);
		c = getContentPane();
        c.add(sPane, BorderLayout.CENTER);
		c.add(deleteJButton, BorderLayout.SOUTH); 
		pack();
		show();
		addWindowListener(new WindowAdapter() {
					public void windowClosing(WindowEvent e) {
						MY_Connection.close();
						System.exit(0);
					}
		});
	}
	
	private JTable addResultSetToJTable(ResultSet rs) {
		Vector columnHeads = new Vector();
		Vector rows = new Vector();
		ResultSetMetaData rsmd = null;
		
		try {
			rsmd = rs.getMetaData();
			for (int i=1; i<=rsmd.getColumnCount(); ++i) {
				columnHeads.addElement(rsmd.getColumnName(i));
			}
			while (rs.next()) {
				Vector currentRow = new Vector();
				for (int i=1; i<=rsmd.getColumnCount(); ++i) {
					currentRow.addElement(rs.getString(i));
				}
				rows.addElement(currentRow);
			}
		} catch(SQLException e) {
			System.err.println("SQLException: " + e.getMessage());
		}
		JTable tempJTable = new JTable(rows,columnHeads);
        tempJTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tempJTable.getSelectionModel().addListSelectionListener(new myListSelectionListener());
		return tempJTable;
	}
	
	class myListSelectionListener implements ListSelectionListener {
		public void valueChanged(ListSelectionEvent e) {
			if (e.getValueIsAdjusting()) {
				return;
			}
			ListSelectionModel tempLSM = (ListSelectionModel) e.getSource();
			if (tempLSM.isSelectionEmpty()) {
				System.out.println("No row are selected.");
			} else {
				selectedRow = tempLSM.getMinSelectionIndex();
				System.out.println("Row " + (selectedRow+1) + " is now selected.");
				selectedCode = (String) myJTable.getValueAt(selectedRow,0);
				deleteJButton.setEnabled(true);
			}
		}
	}

	class myActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			deleteJButton.setEnabled(false);//((JButton) e.getSource()).setEnabled(false);
			
			MY_Connection.deleteQuery(selectedCode);
			System.out.println("Row " + (selectedRow+1) + " is deleted.");
			
			c.remove(sPane);
			myJTable = addResultSetToJTable(MY_Connection.selectQuery());
			sPane = new JScrollPane(myJTable);
			c.add(sPane,BorderLayout.CENTER);
			c.validate();
		}
	}

	public static void main(String[] args) {
		new MyJFrame();
	}
}		