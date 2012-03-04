package com.Team3.Tardis.Views;

import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Date;

import com.Team3.Tardis.Models.*;

 
class TaskEditor extends JFrame implements ActionListener
{
	 //parameters
	 JButton SUBMIT,CANCEL;
	 JPanel panel;
	 JLabel taskID,tID,title,shortDesc,duration,deliverable,dueDateY,dueDateM,dueDateD,personID,superID,cSuperL;
	 TextField  tTitle,tDesc,tDuration,tDeliverable,tYear,tMonth,tDay;
	 JComboBox cPeople,cSuper;
	 int index;
	 long tIDNum;
	 
	 ArrayList<Task> tasks;
	 ArrayList<Person> people;
	 TardisShell shell;
	 
	 public int getIndex(){
		 return index;
	 }
	
	  //Constructor used for Add
	  TaskEditor(TardisShell shell, ArrayList<Task> tasks, ArrayList<Person> people){
		  super("Task Add");
		  this.index=-1;
		  this.tasks = tasks;
		  this.people = people;
		  this.shell = shell;
		  
		  String[] nameArray = new String[(people.size())];
		  for(int i=0;i<(people.size());i ++){
			  nameArray[i]=people.get(i).getFirstName();
		  }
		  
		  Long[] tIDArray = new Long[tasks.size()+1];
		  for(int i=0;i<tasks.size() ;i++){
			  tIDArray[i]= tasks.get(i).getTaskId();
		  }
		  tIDArray[(tIDArray.length-1)]=null;
		  
		  tIDNum = new Date().getTime();
		  
		  //Defining buttons and Fields
		  taskID = new JLabel("Task ID #:");
		  tID = new JLabel(""+tIDNum+"");
		
		  title = new JLabel("Task Title:");
		  tTitle = new TextField(20);
		  
		  shortDesc = new JLabel("Short description:");
		  tDesc = new TextField(20);
		  
		  duration = new JLabel("Duration (# of Hours):");
		  tDuration = new TextField(20);
		  
		  deliverable = new JLabel("Deliverable:");
		  tDeliverable = new TextField(20);
		  
		  dueDateY = new JLabel("Due date Year (YYYY):");
		  dueDateM = new JLabel("Due date Month (MM):");
		  dueDateD = new JLabel("Due date Day (DD):");
		  tYear = new TextField(4);
		  tMonth = new TextField(2);
		  tDay = new TextField(2);
		  
		  personID = new JLabel("Task Assigned to:");
		  cPeople = new JComboBox(nameArray);
		  
		  superID = new JLabel("ID of Parent task");
		  //cSuperL = new JLabel(""); only used in edit portion
		  cSuper = new JComboBox(tIDArray);
		 
		  SUBMIT=new JButton("SUBMIT");
		  
		  CANCEL = new JButton("CANCEL");
		  
		  // Constructing Panel
		  panel=new JPanel(new GridLayout(0,2));
		  panel.add(taskID);
		  panel.add(tID);
		  panel.add(title);
		  panel.add(tTitle);
		  panel.add(shortDesc);
		  panel.add(tDesc);
		  panel.add(duration);
		  panel.add(tDuration);
		  panel.add(deliverable);
		  panel.add(tDeliverable);
		  panel.add(dueDateY);
		  panel.add(tYear);
		  panel.add(dueDateM);
		  panel.add(tMonth);
		  panel.add(dueDateD);
		  panel.add(tDay);
		  panel.add(personID);
		  panel.add(cPeople);
		  panel.add(superID);
		  panel.add(cSuper);
		  panel.add(SUBMIT);
		  panel.add(CANCEL);
		  add(panel,BorderLayout.CENTER);
		  SUBMIT.addActionListener(this);
		  CANCEL.addActionListener(this);
		  setTitle("Task Add");
		  this.setSize(500,400);
		  this.setVisible(true);
		  this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	  }
	  
	//Constructor used for Edit
	  TaskEditor(TardisShell shell, ArrayList<Task> tasks, ArrayList<Person> people, int index){
		  super("Task Edit");
		  this.index=index;
		  this.tasks = tasks;
		  this.people = people;
		  this.shell = shell;
		  
		  String[] nameArray = new String[people.size()];
		  for(int i=0;i<(people.size());i ++){
			  nameArray[i]=people.get(i).getFirstName();
		  }
		  
		  Long[] tIDArray = new Long[tasks.size()+1];
		  for(int i=0;i<tasks.size() ;i++){
			  tIDArray[i]= tasks.get(i).getTaskId();
		  }
		  tIDArray[(tIDArray.length-1)]=null;
		  
		  tIDNum = new Date().getTime();
		  
		  //Defining buttons and Fields
		  taskID = new JLabel("Task ID #:");
		  tID = new JLabel(""+tIDNum+"");
		
		  title = new JLabel("Task Title:");
		  tTitle = new TextField(""+tasks.get(index).getTitle()+"",20);
		  
		  shortDesc = new JLabel("Short description:");
		  tDesc = new TextField(""+tasks.get(index).getShortDescription()+"",20);
		  
		  duration = new JLabel("Duration (# of Hours):");
		  tDuration = new TextField(""+tasks.get(index).getDuration()+"",20);
		  
		  deliverable = new JLabel("Deliverable:");
		  tDeliverable = new TextField(""+tasks.get(index).getDeliverable()+"",20);
		  
		  dueDateY = new JLabel("Due date Year (YYYY):");
		  dueDateM = new JLabel("Due date Month (MM):");
		  dueDateD = new JLabel("Due date Day (DD):");
		  tYear = new TextField(""+(tasks.get(index).getDueDate().getYear()+1900)+"",4);
		  tMonth = new TextField(""+tasks.get(index).getDueDate().getMonth()+"",2);
		  tDay = new TextField(""+tasks.get(index).getDueDate().getDate()+"",2);
		  
		  personID = new JLabel("Task Assigned to:");
		  cPeople = new JComboBox(nameArray);
		  
		  superID = new JLabel("ID of Parent task");
		  cSuper = new JComboBox(tIDArray);
		  cSuperL = new JLabel(""+tIDArray[index]+"");
		 
		  SUBMIT=new JButton("SUBMIT");
		  
		  CANCEL = new JButton("CANCEL");
		  
		  // Constructing Panel
		  panel=new JPanel(new GridLayout(0,2));
		  panel.add(taskID);
		  panel.add(tID);
		  panel.add(title);
		  panel.add(tTitle);
		  panel.add(shortDesc);
		  panel.add(tDesc);
		  panel.add(duration);
		  panel.add(tDuration);
		  panel.add(deliverable);
		  panel.add(tDeliverable);
		  panel.add(dueDateY);
		  panel.add(tYear);
		  panel.add(dueDateM);
		  panel.add(tMonth);
		  panel.add(dueDateD);
		  panel.add(tDay);
		  panel.add(personID);
		  panel.add(cPeople);
		  panel.add(superID);
		  panel.add(cSuperL);
		  panel.add(SUBMIT);
		  panel.add(CANCEL);
		  add(panel,BorderLayout.CENTER);
		  SUBMIT.addActionListener(this);
		  CANCEL.addActionListener(this);
		  setTitle("Task Edit");
		  this.setSize(500,400);
		  this.setVisible(true);
		  this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	  }
	  
	  //validation method activated upon Submit
	 public void actionPerformed(ActionEvent e){
		 String button = e.getActionCommand();
		 
		 if(button.equals("SUBMIT")){
			  boolean name = true;
			  boolean date = true;
			  boolean dura =true;
			  
			  //validating Title
			  String taskTitle = tTitle.getText();
			  for(int i=0;i<tasks.size();i++){
				  if(taskTitle.equals(tasks.get(i).getTitle())){
					  if(this.getIndex()>=0 && taskTitle.equals(tasks.get(this.getIndex()).getTitle())){
						  //do nothing when the title (that is being edited) matches up with itself
					  }
					  else
						  name =false;
				  }
			  }
			 
			  //validating date
			  String year = tYear.getText();
			  String month = tMonth.getText();
			  String day = tDay.getText();
			  int y=0,m=0,d=0;
			  if(isInteger(year) && isInteger(month) && isInteger(day) && year.length()==4 && (month.length()==2||month.length()==1) && (day.length()==2||day.length()==1)){
				  y = Integer.parseInt(year);
				  m = Integer.parseInt(month);
				  d= Integer.parseInt(day);
				  if(y<2012 || m<1 || m>12 ||d<1 || d>31)
					  date = false;
			  }
			  else
				  date =false;
			  
			  //validating duration
			  String duration = tDuration.getText();
			  int dur=0;//most descriptive name ever
			  if(!isInteger(duration)){
				  dura=false;
			  }
			  else
				  dur = Integer.parseInt(duration);
			  
			  //displaying error pop up if validation failed
			  //otherwise creating new task object
			  if(!name)
				  JOptionPane.showMessageDialog(this,"Incorrect Title (Current title is already in use.)","Error",JOptionPane.ERROR_MESSAGE);
			  else if(!date)
				  JOptionPane.showMessageDialog(this,"Incorrect Date format","Error",JOptionPane.ERROR_MESSAGE);
			  else if(!dura)
				  JOptionPane.showMessageDialog(this,"Incorrect Duration (must be an integer)","Error",JOptionPane.ERROR_MESSAGE);
			  else
			  {
				  taskCreator(index,tIDNum,taskTitle,tDesc.getText(),dur,tDeliverable.getText(),new Date(y,m,d),people.get(cPeople.getSelectedIndex()).getPersonId(),tasks.get(cSuper.getSelectedIndex()));
				  shell.update();
				  this.dispose();
			  }
			  
		 }
		 else if(button.equals("CANCEL")){
			 this.dispose();
		 }
		 else{
			 System.out.println("Unexpected Error");
		 }
	 }
	 
	 //Creates new task object
	 //or Edits an existing task object
	 public void taskCreator(int index, long taskId, String title, String shortDescription, int duration, String deliverable, Date dueDate, int personID, Task parent){
		 
		 if(this.getIndex()<0){
			 Task t = new Task();
			 t.setTaskId(taskId);
			 t.setTitle(title);
			 t.setShortDescription(shortDescription);
			 t.setDuration(duration);
			 t.setDeliverable(deliverable);
			 t.setDueDate(dueDate);
			 t.setPersonId(personID);
			 t.setSuperTask(parent);
			 tasks.add(t);
			 parent.addSubtask(parent);
		 }
		 else{
			 tasks.get(this.getIndex()).setTaskId(taskId);
			 tasks.get(this.getIndex()).setTitle(title);
			 tasks.get(this.getIndex()).setShortDescription(shortDescription);
			 tasks.get(this.getIndex()).setDuration(duration);
			 tasks.get(this.getIndex()).setDeliverable(deliverable);
			 tasks.get(this.getIndex()).setDueDate(dueDate);
			 tasks.get(this.getIndex()).setPersonId(personID);
		 }
	 }
	 
	 // checks if a string contains only integers
	 //used to validate the date
	 public boolean isInteger(String in){
		 try{
			 Integer.parseInt(in);
			 return true;
		 }
		 catch(Exception e){
			 return false;
		 }
	 } 
}