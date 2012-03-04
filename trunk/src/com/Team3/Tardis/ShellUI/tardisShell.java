package com.Team3.Tardis.ShellUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class tardisShell extends JFrame {

JTabbedPane tabbedPane = new JTabbedPane();
  
  public tardisShell() {
    super("TARDIS Task Manager");
    
    tabbedPane = new JTabbedPane();
    
    JPanel taskPanel = new JPanel();
    JLabel taskLabel = new JLabel();
    taskLabel.setText("Tasks");
    
    JPanel peoplePanel = new JPanel();
    JLabel peopleLabel = new JLabel();
    peopleLabel.setText("People");
    
    JPanel treePanel = new JPanel();
    JLabel treeLabel = new JLabel();
    treeLabel.setText("Tree");
    
    JPanel ganttPanel = new JPanel();
    JLabel ganttLabel = new JLabel();
    ganttLabel.setText("GANTT");
    
    
    JButton exitButton1 = new JButton("Exit");
    exitButton1.addActionListener(new ActionListener(){
    	public void actionPerformed(ActionEvent e){
    		System.exit(0);
    	}
    });
    
    JButton exitButton2 = new JButton("Exit");
    exitButton1.addActionListener(new ActionListener(){
    	public void actionPerformed(ActionEvent e){
    		System.exit(0);
    	}
    });
    
    JButton exitButton3 = new JButton("Exit");
    exitButton1.addActionListener(new ActionListener(){
    	public void actionPerformed(ActionEvent e){
    		System.exit(0);
    	}
    });
    
    JButton exitButton4 = new JButton("Exit");
    exitButton1.addActionListener(new ActionListener(){
    	public void actionPerformed(ActionEvent e){
    		System.exit(0);
    	}
    });
    
    
    tabbedPane.addTab("Tasks", taskPanel);
    tabbedPane.addTab("People", peoplePanel);
    tabbedPane.addTab("Tree", treePanel);
    tabbedPane.addTab("GANTT", ganttPanel);
    
    taskPanel.add(exitButton1);
    peoplePanel.add(exitButton2);
    treePanel.add(exitButton3);
    ganttPanel.add(exitButton4);
    
   
    
    tabbedPane.addChangeListener(new ChangeListener() {
      public void stateChanged(ChangeEvent e) {
        tabbedPane.revalidate();
      }
    });
    getContentPane().add(tabbedPane);
  }

  
  public static void main(String[] args) {

    JFrame frame = new tardisShell();
    frame.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        System.exit(0);
      }
    });
    frame.setSize(400, 400);
    frame.setVisible(true);
  }
}