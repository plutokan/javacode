package com.cisco.rekan.GUIs;
/**
 * �������������,һ���ı������(javax.swing.JTextField)��һ��"����"��ť(javax.swing.JButton), 
 * Ҫ�����ı������������һ���ַ�,���"����"��ť���ܽ��ı����Ƶ�windows�ļ������,
 * Ȼ����windows�ļ��±������п���ճ�����.
*/
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.datatransfer.*;

public class Exer06 extends JFrame implements ActionListener,ClipboardOwner {
	JTextField copyField;
	JButton copyButton;
	private Clipboard testClipboard;
	
	public Exer06() {
		copyField=new JTextField(80);
		copyButton=new JButton("����");
//		copyButton.setToolTipText("���ı�������ݸ��Ƶ�ϵͳ�������");
		copyButton.addActionListener(this);
		
		getContentPane().add(copyField,BorderLayout.NORTH);
		getContentPane().add(copyButton,BorderLayout.SOUTH);
	//	copyButton.setSize(20,10);		��ť�Ŀ�Ⱦ�Ȼ��Frame�Ŀ��һ������Ҳ�ı䲻�ˡ�

		testClipboard=getToolkit().getSystemClipboard();
	}

	public void actionPerformed(ActionEvent event) {
		StringSelection contents=new StringSelection(copyField.getText());
		testClipboard.setContents(contents,this);
		copyField.setText("");
	}
	
	public static void main(String[] args) {
		Exer06 exer06Frame=new Exer06();
		exer06Frame.setTitle("Java Exercise 6");
		
		exer06Frame.addWindowListener(new WindowAdapter() {
					public void windowClosing(WindowEvent e) {
						System.exit(0);
					}
		});
		
		exer06Frame.setBounds(300,200,500,100);
	//	exer06Frame.pack();
		exer06Frame.setVisible(true);
	}

    public void lostOwnership(Clipboard clipboard, Transferable contents) {
        System.out.println("lost ownership");
    }
}