package com.Team3.Tardis.Views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.Team3.Tardis.Models.Person;
import com.Team3.Tardis.Models.Task;

public class TardisShell extends JFrame {

JTabbedPane tabbedPane = new JTabbedPane();

//initializing task and person array lists
private ArrayList<Task> tasks;
private ArrayList<Person> people;
  
private TaskUI taskPanel;
private PeopleUI peoplePanel;


//constructor
  public TardisShell(ArrayList<Task> tasks, ArrayList<Person> people) {
    super("TARDIS Task Manager");
    
    this.tasks = tasks;
	this.people = people;
    
//creation of file menu	
	JMenuBar menuBar = new JMenuBar();
	setJMenuBar(menuBar);
	JMenu fileMenu = new JMenu("File");
	menuBar.add(fileMenu);

//menu items	
	JMenuItem save = new JMenuItem("Save");
	JMenuItem printSubMenu = new JMenu("Print");
	JMenuItem exit = new JMenuItem("Exit");
	JMenuItem printTask = new JMenuItem("Print Tasks");
	JMenuItem printPeople = new JMenuItem("Print People");
	
	
//creation of tabs    
    taskPanel = new TaskUI(this, this.tasks, this.people);
    JLabel taskLabel = new JLabel();
    taskLabel.setText("Tasks");
    
    peoplePanel = new PeopleUI(this, this.tasks, this.people);
    JLabel peopleLabel = new JLabel();
    peopleLabel.setText("People");
    
    JPanel treePanel = new JPanel();
    JLabel treeLabel = new JLabel();
    treeLabel.setText("Tree");
    
    JPanel ganttPanel = new JPanel();
    JLabel ganttLabel = new JLabel();
    ganttLabel.setText("GANTT");
    
	fileMenu.add(save);
	fileMenu.add(printSubMenu);
	fileMenu.add(exit);
	printSubMenu.add(printTask);
	printSubMenu.add(printPeople);
    
//adding tabs to frame
    tabbedPane.addTab("Tasks", taskPanel);
    tabbedPane.addTab("People", peoplePanel);
    tabbedPane.addTab("Tree", treePanel);
    tabbedPane.addTab("GANTT", ganttPanel);
    

//action listener for the save function
	save.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e)
		{
			System.out.println("Save function here");
		}
	});
	
//action listener for the exit function	
	exit.addActionListener(new ActionListener(){
	  public void actionPerformed(ActionEvent e)
	  {
		  System.exit(0);
	  }
	}); 
	
//printing action listeners
	printTask.addActionListener(new ActionListener(){
	  public void actionPerformed(ActionEvent e)
	  {
		  taskPrinter();
		  System.out.println("Print tasks here");
	  }
	}); 

	printPeople.addActionListener(new ActionListener(){
	  public void actionPerformed(ActionEvent e)
	 {
		  peoplePrinter();
		  System.out.println("People list printed");
	 }
   }); 
	
//change listener for tabs    
    tabbedPane.addChangeListener(new ChangeListener() {
      public void stateChanged(ChangeEvent e) 
      {
    	  tabbedPane.revalidate();
      }
    });
   
    
    getContentPane().add(tabbedPane);
  }
  
  public void update() {
	  taskPanel.update();
	  peoplePanel.update();
  }
  
  
  public void peoplePrinter(){
	  PeopleView peopleView = new PeopleView();
	  peopleView.view("Table View of People.txt", this.people, this.tasks);
	}
	
	
  
//create and show
  public static void createAndShowGUI(ArrayList<Task> tasks, ArrayList<Person> people)
	{
		JFrame frame = new TardisShell(tasks, people);
		
	    frame.addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				System.exit(0);
			}
		});
			
		frame.setSize(1200, 600);
		frame.setVisible(true);
	}
}