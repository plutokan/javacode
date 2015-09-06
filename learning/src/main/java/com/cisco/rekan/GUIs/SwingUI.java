package com.cisco.rekan.GUIs;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.event.*;
import javax.swing.*;

class SwingUI extends JFrame
		 implements ActionListener {

   /**
	 * 
	 */
	private static final long serialVersionUID = -1367590368191389173L;
	JLabel text,text2, clicked;
   JButton button,button2, clickButton;
   JPanel panel;
   private boolean _clickMeMode = true;

   SwingUI(){ //Begin Constructor
     text = new JLabel("I'm a Simple Program");
     text2 = new JLabel("text2");
     button = new JButton("Click Me");
     button2 = new JButton("Button2 alert");
     button.addActionListener(this);
     button2.addActionListener(this);

     panel = new JPanel();
     panel.setLayout(new BorderLayout());
     panel.setBackground(Color.white);
     getContentPane().add(panel);
     panel.add(BorderLayout.CENTER, text);
     panel.add(BorderLayout.NORTH, text2);
     panel.add(BorderLayout.CENTER, button);
     panel.add(BorderLayout.EAST, button2);
   } //End Constructor

   public void actionPerformed(ActionEvent event){
        Object source = event.getSource();
        
        
        if (_clickMeMode) {
          if (source == (Object)button2)
          {
          	button2.setText(source.getClass().getName());
          }
          text.setText("Button Clicked");
          button.setText("Click Again");
          _clickMeMode = false;
        } else {
          if (source == (Object)button2)
          {
          	button2.setText("Button2 alert");
          }
  	
          text.setText("I'm a Simple Program");
          button.setText("Click Me");
          _clickMeMode = true;
        }
   }

   public static void main(String[] args){
     SwingUI frame = new SwingUI();
     frame.setTitle("Example");
     WindowListener l = new WindowAdapter() {
       public void windowClosing(WindowEvent e) {
         System.exit(0);
       }
     };

     frame.addWindowListener(l);
     frame.pack();
     frame.setVisible(true);
  }

}
