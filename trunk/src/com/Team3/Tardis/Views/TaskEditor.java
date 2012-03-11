package com.Team3.Tardis.Views;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Date;

import com.Team3.Tardis.Models.*;

public class TaskEditor extends JFrame implements ActionListener {
	// parameters
	private JButton SUBMIT, CANCEL;
	private JPanel panel;
	protected JLabel taskID, tID, title, shortDesc, duration, deliverable, dueDateY, dueDateM, dueDateD, personID, superID, cSuperL;
	protected TextField tTitle, tDesc, tDuration, tDeliverable, tYear, tMonth, tDay;
	protected JComboBox cPeople, cSuper;
	private int index;
	private long tIDNum;

	private ArrayList<Task> tasks;
	private ArrayList<Person> people;
	private ITardisShell shell;

	// Constructor used for Add
	public TaskEditor(ITardisShell shell, ArrayList<Task> tasks, ArrayList<Person> people) {
		super("Task Add");
		this.index = -1;
		this.tasks = tasks;
		this.people = people;
		this.shell = shell;

		// creating an array of first names for the combobox (dropdown menu)
		// that will select personID
		String[] nameArray = new String[(people.size())];
		for (int i = 0; i < (people.size()); i++) {
			nameArray[i] = people.get(i).getFirstName();
		}

		// creating an array of the task IDs to choose from in the combobox that
		// will select the parentID
		Long[] tIDArray = new Long[tasks.size() + 1];
		for (int i = 0; i < tasks.size(); i++) {
			tIDArray[i] = tasks.get(i).getTaskId();
		}
		tIDArray[(tIDArray.length - 1)] = null;

		tIDNum = new Date().getTime();

		// Defining buttons and Fields
		taskID = new JLabel("Task ID #:");
		tID = new JLabel("" + tIDNum + "");

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
		cSuper = new JComboBox(tIDArray);

		SUBMIT = new JButton("SUBMIT");

		CANCEL = new JButton("CANCEL");

		// Constructing Panel
		panel = new JPanel(new GridLayout(0, 2));
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
		add(panel, BorderLayout.CENTER);
		SUBMIT.addActionListener(this);
		CANCEL.addActionListener(this);
		setTitle("Task Add");
		this.setSize(500, 400);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	// Constructor used for Edit
	public TaskEditor(ITardisShell shell, ArrayList<Task> tasks, ArrayList<Person> people, int index) {
		super("Task Edit");
		this.index = index;
		this.tasks = tasks;
		this.people = people;
		this.shell = shell;

		// creating an array of first names for the combobox (drop down menu)
		// that will select the personID
		String[] nameArray = new String[people.size()];
		for (int i = 0; i < (people.size()); i++) {
			nameArray[i] = people.get(i).getFirstName();
		}

		// Defining buttons and Fields
		taskID = new JLabel("Task ID #:");
		tID = new JLabel("" + tasks.get(index).getTaskId() + "");
		tID.setName("id");

		title = new JLabel("Task Title:");
		tTitle = new TextField("" + tasks.get(index).getTitle() + "", 20);
		tTitle.setName("title");

		shortDesc = new JLabel("Short description:");
		tDesc = new TextField("" + tasks.get(index).getShortDescription() + "", 20);
		tDesc.setName("desc");

		duration = new JLabel("Duration (# of Hours):");
		tDuration = new TextField("" + tasks.get(index).getDuration() + "", 20);
		tDuration.setName("duration");

		deliverable = new JLabel("Deliverable:");
		tDeliverable = new TextField("" + tasks.get(index).getDeliverable() + "", 20);
		tDeliverable.setName("deliverable");

		dueDateY = new JLabel("Due date Year (YYYY):");
		dueDateM = new JLabel("Due date Month (MM):");
		dueDateD = new JLabel("Due date Day (DD):");
		tYear = new TextField((tasks.get(index).getDueDate().getYear() + 1900) + "", 4);
		tYear.setName("year");

		tMonth = new TextField((tasks.get(index).getDueDate().getMonth() + 1) + "", 2);
		tMonth.setName("tMonth");

		tDay = new TextField(tasks.get(index).getDueDate().getDate() + "", 2);
		tDay.setName("day");

		personID = new JLabel("Task Assigned to:");
		cPeople = new JComboBox(nameArray);
		cPeople.setName("people");

		superID = new JLabel("ID of Parent task");
	
		if (tasks.get(index).getSuperTask() == null)
			cSuperL = new JLabel("No Parent");
		else
			cSuperL = new JLabel("" + tasks.get(index).getSuperTask().getTaskId() + "");

		cSuperL.setName("superL");
		
		SUBMIT = new JButton("SUBMIT");

		CANCEL = new JButton("CANCEL");

		// Constructing Panel
		panel = new JPanel(new GridLayout(0, 2));
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
		add(panel, BorderLayout.CENTER);
		SUBMIT.addActionListener(this);
		CANCEL.addActionListener(this);
		this.setSize(500, 400);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	public void updateTask() {

		String taskTitle = tTitle.getText();

		String year = tYear.getText();
		String month = tMonth.getText();
		String day = tDay.getText();
		int y = 0, m = 0, d = 0;
		y = Integer.parseInt(year);
		m = Integer.parseInt(month);
		d = Integer.parseInt(day);

		Date dueDate = new Date(y, m, d);

		String sDuration = tDuration.getText();
		int duration = Integer.parseInt(sDuration);

		if (index == -1) {
			if (cSuper.getSelectedIndex() == -1) {
				// gets called if tasks has a parent
				taskCreator(tIDNum, taskTitle, tDesc.getText(), duration, tDeliverable.getText(), dueDate, people.get(cPeople.getSelectedIndex()).getPersonId(), null);
			} else {
				// gets called if task does not have a parent
				taskCreator(tIDNum, taskTitle, tDesc.getText(), duration, tDeliverable.getText(), dueDate, people.get(cPeople.getSelectedIndex()).getPersonId(), tasks.get(cSuper.getSelectedIndex()));
			}
		} else
			taskCreator(tIDNum, taskTitle, tDesc.getText(), duration, tDeliverable.getText(), dueDate, people.get(cPeople.getSelectedIndex()).getPersonId(), null);
	}

	private boolean validateTitle() {
		String taskTitle = tTitle.getText();

		for (int i = 0; i < tasks.size(); i++) {
			if (taskTitle.equals(tasks.get(i).getTitle())) {
				if (index >= 0 && taskTitle.equals(tasks.get(index).getTitle())) {
					// do nothing when the title (that is being edited)
					// matches up with itself
				} else
					return false;
			}
		}
		
		return true;
	}

	private boolean validateDate() {
		// validating date does not work for leap years(there is no feb
		// 29th)
		String year = tYear.getText();		
		String month = tMonth.getText();
		String day = tDay.getText();

		// checks if year month and day are entered as numbers and that they
		// are the correct length. EX. year has 4 digits and month has 2...
		if (isInteger(year) && isInteger(month) && isInteger(day) && year.length() == 4 && (month.length() == 2 || month.length() == 1) && (day.length() == 2 || day.length() == 1))
		{
			int y = 0, m = 0, d = 0;

			y = Integer.parseInt(year);
			m = Integer.parseInt(month);
			d = Integer.parseInt(day);
			
			if (y < 2012 || m < 1 || m > 12 || d < 1 || d > 31)
			{
				return false;
			}
			else
			{
				// checks for months with only 30 days
				if ((m == 4 || m == 6 || m == 9 || m == 11) && d == 31)
				{
					return false;
				}
				// checks that feb would have only 28 days(no leap years)
				else if (m == 2 && d > 28)
				{
					return false;
				}
			}
		}
		else
		{
			return false;
		}
		
		return true;
	}

	private boolean validateDuration() {
		String sDuration = tDuration.getText();
		if (!isInteger(sDuration))
			return false;

		return true;
	}

	// validation method activated upon Submit
	public void actionPerformed(ActionEvent e) {
		String button = e.getActionCommand();

		if (button.equals("SUBMIT")) {

			if (!validateDate())
				JOptionPane.showMessageDialog(this, "Incorrect Date format", "Error", JOptionPane.ERROR_MESSAGE);
			else if (!validateTitle())
				JOptionPane.showMessageDialog(this, "Incorrect Title (Current title is already in use.)", "Error", JOptionPane.ERROR_MESSAGE);
			else if (!validateDuration())
				JOptionPane.showMessageDialog(this, "Incorrect Duration (must be an integer)", "Error", JOptionPane.ERROR_MESSAGE);
			else {
				updateTask();

				// updating the display and closing the TaskEditor Window
				shell.update();
				this.dispose();
			}
		}
		// when Cancel button is pressed closes task editor window no object is
		// made
		else if (button.equals("CANCEL")) {
			this.dispose();
		} else {
			System.out.println("Unexpected Error");
		}
	}

	// Creates new task object
	// or Edits an existing task object
	private void taskCreator(long taskId, String title, String shortDescription, int duration, String deliverable, Date dueDate, int personID, Task parent) {

		// creates new object
		if (index < 0) {
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
			if (parent != null)
				parent.addSubtask(t);
		}
		// edits existing object
		else {
			tasks.get(index).setTitle(title);
			tasks.get(index).setShortDescription(shortDescription);
			tasks.get(index).setDuration(duration);
			tasks.get(index).setDeliverable(deliverable);
			tasks.get(index).setDueDate(dueDate);
			tasks.get(index).setPersonId(personID);
		}
	}

	// returns true if string can be resolved to an integer.
	// used to validate the date and duration.
	public boolean isInteger(String in) {
		try {
			Integer.parseInt(in);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}