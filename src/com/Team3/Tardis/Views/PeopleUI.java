package com.Team3.Tardis.Views;

import com.Team3.Tardis.Models.*;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;

//Table View of Tasks
public class PeopleUI extends JPanel
{	
	//Stores the array of Task Objects and People Objects
	private TardisShell shell;
	private ArrayList<Task> tasks;
	private ArrayList<Person> people;
	
	//Names of the columns
	private String[] columnNames = {"ID",
									"First Name",
									"Last Name",
									"Phone Number",
									"Address",
									"City",
									"Postal Code",
									"Province",
									"Country",
            						"Tasks Assigned (by ID)"};
	
	//Stores the People information
	private Object[][] peopleInfo;
	
	//Store the elements that form the Table View of People
	private JPanel peopleTablePanel;
	private JTable peopleTable;
	
	//Stores the default model
	private DefaultTableModel model;
	
	//Constructor
	public PeopleUI(TardisShell shell, ArrayList<Task> tasks, ArrayList<Person> people)
	{
		//Sets the member variables appropriately
		//Shallow copies are used so that changes are applied to the original arrayLists in the main
		this.tasks = tasks;
		this.people = people;
		this.shell = shell;
		
		//Fills the peopleInfo array with the values stored in the ArrayList
		setPeopleInfo();
		
		setLayout(new BorderLayout());
		
		//Creates the table part
		peopleTablePanel();

		//Adds the two to the existing panel
		add(peopleTablePanel, BorderLayout.NORTH);
	}
	
	//Creates the table
	private void peopleTablePanel() 
	{
		peopleTablePanel = new JPanel();
		peopleTablePanel.setLayout(new GridLayout(1, 0));
		
		model = new DefaultTableModel(peopleInfo, columnNames);
		peopleTable = new JTable(model)
		{
			//Set so that the cells cannot be edited
			public boolean isCellEditable(int rowIndex, int colIndex)
			{
				return false;
			}
		};
        
		//Sets the size and the sorting
		peopleTable.setPreferredScrollableViewportSize(new Dimension(1300, 600));
		peopleTable.setFillsViewportHeight(true);
		peopleTable.setAutoCreateRowSorter(true);
        
        //Set so that the user can only select one row at a time
		peopleTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(peopleTable);
        
        //Create the scroll pane, add the table to it and add the scroll pane to the panel.
        add(scrollPane);
	}
	
	//Used to initialize the peopleInfo array during the construction of the object
	//or after an edit or delete
	private void setPeopleInfo()
	{		
		peopleInfo = new Object[people.size()][10];
		
		for (int i = 0; i != peopleInfo.length; ++i)
		{			
			//Array list to store input list of people
        	ArrayList<Task> subTaskList = people.get(i).getTasks(tasks);
			
        	peopleInfo[i][0] = people.get(i).getPersonId();
        	peopleInfo[i][1] = people.get(i).getFirstName();
        	peopleInfo[i][2] = people.get(i).getLastName();	
        	peopleInfo[i][3] = people.get(i).getPhoneNumber();
        	peopleInfo[i][4] = people.get(i).getAddress();
        	peopleInfo[i][5] = people.get(i).getCity();
			peopleInfo[i][6] = people.get(i).getPostalCode();
			peopleInfo[i][7] = people.get(i).getProvince();
			peopleInfo[i][8] = people.get(i).getCountry();
            
            //String to store task ID's
            String tList ="";
            
            for(int j=0;j<subTaskList.size();j++)
            {
            	tList+=subTaskList.get(j).getTaskId()+" ";
            }
            
            peopleInfo[i][9] = tList;
		}
	}
	
	//Used to update the table
	public void update()
	{
		//The array is filled with the updated values
		setPeopleInfo();
		
		//The model is reset
		model = new DefaultTableModel(peopleInfo, columnNames);
		peopleTable.setModel(model);
	}
}