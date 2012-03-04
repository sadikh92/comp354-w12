package com.Team3.Tardis.Views;

import com.Team3.Tardis.Models.*;
import com.Team3.Tardis.Controller.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

//Modified version of JTable template found at http://docs.oracle.com/javase/tutorial/uiswing/components/table.html#data

/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */ 
/*
public class PeopleUI extends JPanel {
    private boolean DEBUG = false;

    public PeopleUI(ArrayList<Person> personList, ArrayList<Task> taskList) {
        super(new GridLayout(1,0));
        
        //Array list to store input list of people
        ArrayList<Person> pList=personList;
        
        //Number of people
        int numOfPeople = pList.size();
        
        //2D array to store table data
        Object[][] list = new Object[numOfPeople][10];
        
        //Populate the data table
        for(int i=0;i<numOfPeople;i++)
        {
        	//Array list to store input list of tasks
        	ArrayList<Task> subTaskList = pList.get(i).getTasks(taskList);
        	
        	list[i][0] = pList.get(i).getPersonId();
        	list[i][1] = pList.get(i).getFirstName();
        	list[i][2] = pList.get(i).getLastName();	
        	list[i][3] = pList.get(i).getPhoneNumber();
        	list[i][4] = pList.get(i).getAddress();
        	list[i][5] = pList.get(i).getCity();
            list[i][6] = pList.get(i).getPostalCode();
            list[i][7] = pList.get(i).getProvince();
            list[i][8] = pList.get(i).getCountry();
            
            //String to store task ID's
            String tList ="";
            
            for(int j=0;j<subTaskList.size();j++)
            {
            	tList+=subTaskList.get(j).getTaskId()+" ";
            }
            
            list[i][9] = tList;
        }
        
        JTable table = new JTable(new MyTableModel(list));
        table.setPreferredScrollableViewportSize(new Dimension(800, 200));
        table.setFillsViewportHeight(true);
        
        //Allow the auto sorting
        table.setAutoCreateRowSorter(true);

        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);

        //Add the scroll pane to this panel.
        add(scrollPane);
    }
    
 

    class MyTableModel extends AbstractTableModel {
        
    	//Table column titles
    	private String[] columnNames = {"ID",
        								"First Name",
                                        "Last Name",
                                        "Phone Number",
                                        "Address",
                                        "City",
                                        "Postal Code",
                                        "Province",
                                        "Country",
                                        "Tasks"
                                        };
        
    	//2D array of data
        private Object[][] data;
	   
        //Constructor
        public MyTableModel(Object[][] temp)
        {
        	data=temp;
        }

        public int getColumnCount() {
            return columnNames.length;
        }

        public int getRowCount() {
            return data.length;
        }

        public String getColumnName(int col) {
            return columnNames[col];
        }

        public Object getValueAt(int row, int col) {
            return data[row][col];
        }

        
          //JTable uses this method to determine the default renderer/
          //editor for each cell.  If we didn't implement this method,
          //then the last column would contain text ("true"/"false"),
          //rather than a check box.
         
        public Class getColumnClass(int c) {
            return getValueAt(0, c).getClass();
        }

        
          //Don't need to implement this method unless your table's
          //editable.
         
        public boolean isCellEditable(int row, int col) {
            //Note that the data/cell address is constant,
            //no matter where the cell appears onscreen.
            if (col < 9) {
                return false;
            } else {
                return true;
            }
        }

        
          //Don't need to implement this method unless your table's
          //data can change.
         
        public void setValueAt(Object value, int row, int col) {
            if (DEBUG) {
                System.out.println("Setting value at " + row + "," + col
                                   + " to " + value
                                   + " (an instance of "
                                   + value.getClass() + ")");
            }

            data[row][col] = value;
            fireTableCellUpdated(row, col);

            if (DEBUG) {
                System.out.println("New value of data:");
                printDebugData();
            }
        }

        private void printDebugData() {
            int numRows = getRowCount();
            int numCols = getColumnCount();

            for (int i=0; i < numRows; i++) {
                System.out.print("    row " + i + ":");
                for (int j=0; j < numCols; j++) {
                    System.out.print("  " + data[i][j]);
                }
                System.out.println();
            }
            System.out.println("--------------------------");
        }
    }

    
      //Create the GUI and show it.  For thread safety,
      //this method should be invoked from the
      //event-dispatching thread.
     
    public static void createAndShowPeopleGUI(ArrayList<Person> personList, ArrayList<Task> taskList) {
        //Create and set up the window.
        JFrame frame = new JFrame("People View");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        PeopleUI newContentPane = new PeopleUI(personList, taskList);
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }   
}*/

//Table View of Tasks
public class PeopleUI extends JPanel implements ActionListener
{	
	//Stores the array of Task Objects and People Objects
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
            						"Tasks"};
	
	//Stores the People information
	private Object[][] peopleInfo;
	
	//Store the elements that form the Table View of People
	private JPanel peopleTablePanel;
	private JPanel peopleButtonPanel;
	private JTable peopleTable;
	
	//Stores the default model
	private DefaultTableModel model;
	
	//Constructor
	public PeopleUI(ArrayList<Task> tasks, ArrayList<Person> people)
	{
		//Fills the peopleInfo array with the values stored in the ArrayList
		this.tasks = tasks;
		this.people = people;
		
		setTaskInfo();
		
		setLayout(new BorderLayout());
		
		//Creates the table part
		taskTablePanel();
		
		//Creates the button part
		taskButtonPanel();

		//Adds the two to the existing panel
		add(peopleTablePanel, BorderLayout.NORTH);
		add(peopleButtonPanel, BorderLayout.SOUTH);
	}
	
	//Creates the table
	private void taskTablePanel() 
	{
		peopleTablePanel = new JPanel();
		peopleTablePanel.setLayout(new GridLayout(1, 0));
		
		model = new DefaultTableModel(peopleInfo, columnNames);
		peopleTable = new JTable(model)
		{
			//Set so that the cells cannot be edited without using the edit button
			public boolean isCellEditable(int rowIndex, int colIndex)
			{
				return false;
			}
		};
        
		peopleTable.setPreferredScrollableViewportSize(new Dimension(1300, 600));
		peopleTable.setFillsViewportHeight(true);
		peopleTable.setAutoCreateRowSorter(true);
        
        //Set so that the user can only select one row at a time to simplify
        //deletion and editing
		peopleTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(peopleTable);
        
        //Create the scroll pane, add the table to it and add the scroll pane to the panel.
        add(scrollPane);
	}
	
	//Creates the button Panel
	private void taskButtonPanel() 
	{
		peopleButtonPanel = new JPanel();
		peopleButtonPanel.setLayout(new FlowLayout());
		
		JButton addButton = new JButton("Add");
		addButton.addActionListener(this);
		peopleButtonPanel.add(addButton);
		
		JButton editButton = new JButton("Edit");
		editButton.addActionListener(this);
		peopleButtonPanel.add(editButton);
		
		JButton deleteButton = new JButton("Delete");
		deleteButton.addActionListener(this);
		peopleButtonPanel.add(deleteButton);
	}
	
	//Used to initialize the taskInfo array during the construction of the object
	//or after an edit or delete
	private void setTaskInfo()
	{		
		peopleInfo = new Object[people.size()][10];
		
		for (int i = 0; i != peopleInfo.length; ++i)
		{			
			//Array list to store input list of tasks
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
		setTaskInfo();
		
		//The model is reset
		model = new DefaultTableModel(peopleInfo, columnNames);
		peopleTable.setModel(model);
	}
	
	//Listens for the button presses
	public void actionPerformed(ActionEvent e)
	{
		String buttonString = e.getActionCommand();
		
		if (buttonString.equals("Add"))
		{
			TaskEditor taskAdd = new TaskEditor(tasks, people);
			
			update();
		}
		else if (buttonString.equals("Edit"))
		{
			int row = peopleTable.getSelectedRow();
			
			if (row == -1)
			{
				//Error Pop Up
				JOptionPane.showMessageDialog(this, "No row selected!", "Error", JOptionPane.ERROR_MESSAGE);
			}
			else
			{
				TaskEditor taskAdd = new TaskEditor(tasks, people, row);
				
				update();
			}
		}
		else if (buttonString.equals("Delete"))
		{
			int row = peopleTable.getSelectedRow();
			
			if (row == -1)
			{
				//Error Pop Up
				JOptionPane.showMessageDialog(this, "No row selected!", "Error", JOptionPane.ERROR_MESSAGE);
			}
			else
			{
				//Confirm Delete Pop Up
				int n = JOptionPane.showConfirmDialog(this,
						"Are you sure you want to delete the person?", "Deletion Confirmation", JOptionPane.YES_NO_OPTION);

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
		tasks.remove(row);
		
		update();
	}
}














