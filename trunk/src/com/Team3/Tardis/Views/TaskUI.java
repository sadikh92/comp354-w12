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
	private ITardisShell shell;
	private ArrayList<Task> tasks;
	private ArrayList<Person> people;
	
	//Names of the columns
	private String[] columnNames = {"Identifier",
            						"Title",
            						"Description",
            						"Duration",
            						"Deliverable",
            						"Deadline",
            						"Assigned Person",
            						"Successor",
            						"Completion(%)"};
	
	//Stores the Task information
	private Object[][] taskInfo;
	
	//Store the elements that form the Table View of Tasks
	private JPanel taskTablePanel;
	private JPanel taskButtonPanel;
	private JTable taskTable;
	
	//Stores the default model
	private DefaultTableModel model;
	
	//Constructor
	public TaskUI(ITardisShell shell, ArrayList<Task> tasks, ArrayList<Person> people)
	{
		//Fills the taskInfo array with the values stored in the ArrayList
		this.shell = shell;
		this.tasks = tasks;
		this.people = people;
		
		//Sets the two dimensional array of tasks
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
		
		//Creates the table using the array of tasks and the array of column names
		model = new DefaultTableModel(taskInfo, columnNames);
		taskTable = new JTable(model)
		{
			//Set so that the cells cannot be edited without using the edit button
			public boolean isCellEditable(int rowIndex, int colIndex)
			{
				return false;
			}
		};
        
		//Sets the size and sorting of the table
        taskTable.setPreferredScrollableViewportSize(new Dimension(1300, 600));
        taskTable.setFillsViewportHeight(true);
        //taskTable.setAutoCreateRowSorter(true);
        
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
		
		//Adds the add button
		JButton addButton = new JButton("Add");
		addButton.addActionListener(this);
		taskButtonPanel.add(addButton);
		
		//Adds the Edit button
		JButton editButton = new JButton("Edit");
		editButton.addActionListener(this);
		taskButtonPanel.add(editButton);
		
		//Adds the Delete button
		JButton deleteButton = new JButton("Delete");
		deleteButton.addActionListener(this);
		taskButtonPanel.add(deleteButton);
	}
	
	//Used to initialize the taskInfo array during the construction of the object
	//or after an edit or delete
	private void setTaskInfo()
	{		
		//Sets the two dimensional array to the correct size
		taskInfo = new Object[tasks.size()][9];
		boolean breakIf = false;
		
		//Loads the array with the correct values
		for (int i = 0; i != taskInfo.length; ++i)
		{
			taskInfo[i][0] = tasks.get(i).getTaskId();			
			taskInfo[i][1] = tasks.get(i).getTitle();
			taskInfo[i][2] = tasks.get(i).getShortDescription();
			taskInfo[i][3] = tasks.get(i).getDuration();
			taskInfo[i][4] = tasks.get(i).getDeliverable();	
			taskInfo[i][5] = tasks.get(i).getDueDate();
			taskInfo[i][8] = tasks.get(i).getCompletionPercentage();
			if(tasks.get(i).getSuccessor()!=null)
			taskInfo[i][7] = tasks.get(i).getSuccessor().getTaskId();
			
			//Rather than adding a person's ID, the ID is used to find
			//the person in the people arrayList to print to show their name instead
			for (int j = 0; j != people.size() && !breakIf; ++j)
			{
				//When the person's ID matches the ID of the person in the people list
				if (people.get(j).getPersonId() == tasks.get(i).getPersonId())
				{
					//Their name is placed in the array
					taskInfo[i][6] = people.get(j).getFirstName();
					
					//The search stops
					breakIf = true;
				}
			}
			
			breakIf = false;
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
		
		//If add was pressed
		if (buttonString.equals("Add"))
		{
			new TaskEditor(shell, tasks, people);
		}
		//If edit was pressed
		else if (buttonString.equals("Edit"))
		{
			int row = taskTable.getSelectedRow();
			
			//If no row is selected (Swing returns -1)
			if (row == -1)
			{
				//Error Pop Up
				JOptionPane.showMessageDialog(this, "No row selected!", "Error", JOptionPane.ERROR_MESSAGE);
			}
			else
			{				
				//The task is edited
				new TaskEditor(shell, tasks, people, row);
			}
		}
		//If delete was pressed
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
						"Deleting a task will also delete any of its subtasks. " +
						"Are you sure you want to delete the task?",
						"Deletion Confirmation", JOptionPane.YES_NO_OPTION);

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
	//Delete will call itself recursively to delete any subtasks
	private void delete(int row)
	{
		boolean breakIf = false;
		int index = row;
		
		//If the task has a super task
		if (tasks.get(index).getSuperTask() != null)	
		{			
			//The task will be removed from the super task's list of subtasks
			for (int i = 0; i != tasks.get(index).getSuperTask().getSubtasks().size() && !breakIf;)
			{
				if (tasks.get(index).getSuperTask().getSubtasks().get(i).getTaskId() == tasks.get(index).getTaskId())
				{
					tasks.get(index).getSuperTask().getSubtasks().remove(i);
					breakIf = true;
				}
				else
				{
					++i;
				}
			}
		}
		
		//BreakIf is reset
		breakIf = false;
		
		//If the task has subtasks,
		//The delete method is called recursively on each of its children
		if (!tasks.get(index).getSubtasks().isEmpty())
		{
			//Store the subtask index
			int subIndex = -1;
			
			//For each of the task's subtasks
			//(i is never incremented because the arrayList will be updated after every deletion)
			for (int i = 0; i != tasks.get(index).getSubtasks().size();)
			{
				//Find the subtask's location in the arrayList so that it can be deleted
				for (int j = 0; j != tasks.size() && !breakIf;)
				{
					if (tasks.get(index).getSubtasks().get(i).getTaskId() == tasks.get(j).getTaskId())
					{
						subIndex = j;
						breakIf = true;
					}
					else
					{
						++j;
					}
				}
				
				//Call delete recursively on the subtask
				if (subIndex != -1)
				{
					delete(subIndex);
				
					breakIf = false;
				}
			}
		}

		//Once all of the subtasks have been deleted, the task itself is deleted
		tasks.remove(index);			
		
		//The shell is updated after each deletion
		shell.update();
	}
}