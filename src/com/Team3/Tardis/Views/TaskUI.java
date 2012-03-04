package com.Team3.Tardis.Views;

//Appropriate Swing Libraries
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import com.Team3.Tardis.Models.Person;
import com.Team3.Tardis.Models.Task;
import com.Team3.Tardis.Views.TaskEditor;

//Table View of Tasks
public class TaskUI extends JPanel implements ActionListener
{	
	//Stores the array of Task Objects
	private TardisShell shell;
	private ArrayList<Task> tasks;
	private ArrayList<Person> people;
	
	//Names of the columns
	private String[] columnNames = {"Identifier",
            						"Title",
            						"Description",
            						"Duration",
            						"Deliverable",
            						"Deadline",
            						"Assigned Person"};
	
	//Stores the Task information
	private Object[][] taskInfo;
	
	//Store the elements that form the Table View of Tasks
	private JPanel taskTablePanel;
	private JPanel taskButtonPanel;
	private JTable taskTable;
	
	//Stores the default model
	private DefaultTableModel model;
	
	//Constructor
	public TaskUI(TardisShell shell, ArrayList<Task> tasks, ArrayList<Person> people)
	{
		//Fills the taskInfo array with the values stored in the ArrayList
		this.shell = shell;
		this.tasks = tasks;
		this.people = people;
		
		setTaskInfo();
		
		setLayout(new BorderLayout());
		
		//Creates the table part
		taskTablePanel();
		
		//Creates the button part
		taskButtonPanel();

		//Adds the two to the existing panel
		add(taskTablePanel, BorderLayout.NORTH);
		add(taskButtonPanel, BorderLayout.SOUTH);
	}
	
	//Creates the table
	private void taskTablePanel() 
	{
		taskTablePanel = new JPanel();
		taskTablePanel.setLayout(new GridLayout(1, 0));
		
		model = new DefaultTableModel(taskInfo, columnNames);
		taskTable = new JTable(model)
		{
			//Set so that the cells cannot be edited without using the edit button
			public boolean isCellEditable(int rowIndex, int colIndex)
			{
				return false;
			}
		};
        
        taskTable.setPreferredScrollableViewportSize(new Dimension(1300, 600));
        taskTable.setFillsViewportHeight(true);
        taskTable.setAutoCreateRowSorter(true);
        
        //Set so that the user can only select one row at a time to simplify
        //deletion and editing
        taskTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(taskTable);
        
        //Create the scroll pane, add the table to it and add the scroll pane to the panel.
        add(scrollPane);
	}
	
	//Creates the button Panel
	private void taskButtonPanel() 
	{
		taskButtonPanel = new JPanel();
		taskButtonPanel.setLayout(new FlowLayout());
		
		JButton addButton = new JButton("Add");
		addButton.addActionListener(this);
		taskButtonPanel.add(addButton);
		
		JButton editButton = new JButton("Edit");
		editButton.addActionListener(this);
		taskButtonPanel.add(editButton);
		
		JButton deleteButton = new JButton("Delete");
		deleteButton.addActionListener(this);
		taskButtonPanel.add(deleteButton);
	}
	
	//Used to initialize the taskInfo array during the construction of the object
	//or after an edit or delete
	private void setTaskInfo()
	{		
		taskInfo = new Object[tasks.size()][7];
		
		for (int i = 0; i != taskInfo.length; ++i)
		{
			taskInfo[i][0] = tasks.get(i).getTaskId();			
			taskInfo[i][1] = tasks.get(i).getTitle();
			taskInfo[i][2] = tasks.get(i).getShortDescription();
			taskInfo[i][3] = tasks.get(i).getDuration();
			taskInfo[i][4] = tasks.get(i).getDeliverable();	
			taskInfo[i][5] = tasks.get(i).getDueDate();	
			taskInfo[i][6] = tasks.get(i).getPersonId();
		}
	}
	
	//Used to update the table
	public void update()
	{
		//The array is filled with the updated values
		setTaskInfo();
		
		//The model is reset
		model = new DefaultTableModel(taskInfo, columnNames);
		taskTable.setModel(model);
	}
	
	//Listens for the button presses
	public void actionPerformed(ActionEvent e)
	{
		String buttonString = e.getActionCommand();
		
		if (buttonString.equals("Add"))
		{
			TaskEditor taskAdd = new TaskEditor(shell, tasks, people);
			
			//update();
		}
		else if (buttonString.equals("Edit"))
		{
			int row = taskTable.getSelectedRow();
			
			if (row == -1)
			{
				//Error Pop Up
				JOptionPane.showMessageDialog(this, "No row selected!", "Error", JOptionPane.ERROR_MESSAGE);
			}
			else
			{
				TaskEditor taskAdd = new TaskEditor(shell, tasks, people, row);
				
				//update();
			}
		}
		else if (buttonString.equals("Delete"))
		{
			int row = taskTable.getSelectedRow();
			
			if (row == -1)
			{
				//Error Pop Up
				JOptionPane.showMessageDialog(this, "No row selected!", "Error", JOptionPane.ERROR_MESSAGE);
			}
			else
			{
				//Confirm Delete Pop Up
				int n = JOptionPane.showConfirmDialog(this,
						"Are you sure you want to delete the task?", "Deletion Confirmation", JOptionPane.YES_NO_OPTION);

				if (n == JOptionPane.YES_OPTION)
				{
					delete(row);
				}
				else if (n == JOptionPane.NO_OPTION)
				{
					//Nothing happens
				}
				else
				{
					System.out.println("Unexpected error.");
				}
			}
		}
		else
		{
			System.out.println("Unexpected error.");
		}
	}
	
	//Deletes the appropriate Task from the array list
	private void delete(int row)
	{
		boolean breakIf = false;
		Task tempTask = tasks.get(row);
		Task tempSuperTask = tempTask.getSuperTask();
		
		if (tempSuperTask != null)
		{
			//Remove the task from the super task's list of sub tasks
			for (int i = 0; i != tempSuperTask.getSubtasks().size() && !breakIf;)
			{
				if (tempSuperTask.getSubtasks().get(i).getTaskId() == tempTask.getTaskId())
				{
					tempSuperTask.getSubtasks().remove(i);
					breakIf = true;
				}
				else
				{
					++i;
				}
			}
		}
		
		breakIf = false;
		
		if (tempTask.getSubtasks() != null)
		{
			//Store the subtask index
			int index = 0;
			
			for (int i = 0; i != tempTask.getSubtasks().size(); ++i)
			{
				for (int j = 0; j != tasks.size() && !breakIf; ++j)
				{
					if (tempTask.getSubtasks().get(i).getTaskId() == tasks.get(j).getTaskId())
					{
						index = j;
						breakIf = true;
					}
				}
				
				//Call remove on each subtask
				tasks.remove(index);
			}
		}
		else
		{
			tasks.remove(row);	//Original Code
		}						
		
		shell.update();			//
	}
}