package com.Team3.Tardis.Controller;

import java.util.ArrayList;

import com.Team3.Tardis.Models.*;
import com.Team3.Tardis.Models.XML.*;
import com.Team3.Tardis.Util.InputValidator;
import com.Team3.Tardis.Util.Logger;
import com.Team3.Tardis.Views.TardisShell;

/**
 * @author Alex Landovskis,Babacar Ndiaye
 * @description The main controller that runs the TARDIS task manager.
 *
 */
public class TardisController {

	public static final String PEOPLE_FILE = "xml/people.xml";
	public static final String TASKS_FILE = "xml/tasks.xml";
	static final String VIEW_FILE = "report/report.txt";
	/**
	 * @param args: The system arguments.
	 */
	public static void main(String[] args) {

		// Load the people.
		InputValidator validator = new InputValidator();
		PeopleReader peopleReader = new PeopleReader(validator);
		TaskReader taskReader = new TaskReader(validator);
		ArrayList<Person> people;
		ArrayList<Task> tasks;
		
		try {
			// Load people.
			people = peopleReader.loadPeople(PEOPLE_FILE);
			
			// Load tasks
			tasks = taskReader.loadTasks(TASKS_FILE);
			
			//Set status
			for (Task tempTask : tasks)
			{
				//if task has no predecessors
				if (tempTask.getPredecessors().isEmpty()&&tempTask.getCompletionPercentage()==0)
				{
					tempTask.setStatus("WaitingToRun");
				}
				else if(tempTask.getPredecessors().isEmpty()&&tempTask.getCompletionPercentage()==100){
					tempTask.setStatus("Complete");
					//other option: maybe delete from list of predecessor of its successor
				}
				else if(tempTask.getPredecessors().isEmpty()&&tempTask.getCompletionPercentage()!=0){
					tempTask.setStatus("In progress");
				}
				
				
				//if task has predecessors 
				else if(!tempTask.getPredecessors().isEmpty()){		
					//compute percentage in predecessor array list
					int temp=0;
					for(int i=0;i<tempTask.getPredecessors().size();i++){
					temp=temp+(tempTask.getPredecessors().get(i).getCompletionPercentage());
					}
					int result=temp/(tempTask.getPredecessors().size());
					
					//if all predecessors are completed,task is ready to start
					if(result==100&&tempTask.getCompletionPercentage()==0)
					tempTask.setStatus("WaitingToRun");	
					
					//if all predecessors are completed and task ran to completion
					else if(result==100&&tempTask.getCompletionPercentage()==100)
					tempTask.setStatus("Complete");	
					
					//if all predecessors are completed and task is running
					else if(result==100&&tempTask.getCompletionPercentage()!=0)
					tempTask.setStatus("In progress");
					
					//if predecessors are not completed yet
					else if (result!=100){
					tempTask.setStatus("WaitingForPredecessorToComplete");
					tempTask.setCompletionPercentage(0);
					}
				}
								
				else {
					tempTask.setStatus("Unknown/Invalid Status");
				}				
			}
			
			//Create the interactive GUI
			TardisShell.createAndShowGUI(tasks, people);
			
		} catch (Exception e) {
			Logger.log(TardisController.class.getName(), e.toString());
		}
	}
}