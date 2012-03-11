package com.Team3.Tardis.Views.Tests;
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import com.Team3.Tardis.Models.Person;
import com.Team3.Tardis.Models.Task;
import com.Team3.Tardis.Models.XML.PeopleReader;
import com.Team3.Tardis.Models.XML.TaskReader;
import com.Team3.Tardis.Util.InputValidator;
import com.Team3.Tardis.Views.TaskEditor;
import com.Team3.Tardis.Views.TaskUI;

public class TaskIUTests {
	
	@Test
	public void TestDeleteAndSub()
	{
		int index = 0;//the delete index
		try{
			//setup
			InputValidator validator = new InputValidator();
			PeopleReader peopleReader = new PeopleReader(validator);
			TaskReader taskReader = new TaskReader(validator);	
			ArrayList<Person> people = peopleReader.loadPeople(Common.PEOPLE_FILE);
			ArrayList<Task> tasks = taskReader.loadTasks(Common.TASKS_FILE);
			
			TaskUIWrapper testTaskUI = new TaskUIWrapper(new TardisShellMock(), tasks, people);
			
			assertTrue(testDelAndSub(tasks, testTaskUI, index));//test
		}
		catch (Exception e)
		{
		}
	}
	@Test
	public void TestDeleteAndSuper()
	{
		int index = 3;//the delete index
		try{
			//setup
			InputValidator validator = new InputValidator();
			PeopleReader peopleReader = new PeopleReader(validator);
			TaskReader taskReader = new TaskReader(validator);	
			ArrayList<Person> people = peopleReader.loadPeople(Common.PEOPLE_FILE);
			ArrayList<Task> tasks = taskReader.loadTasks(Common.TASKS_FILE);
			
			TaskUIWrapper testTaskUI = new TaskUIWrapper(new TardisShellMock(), tasks, people);
			
			assertTrue(testDelAndSuper(tasks, testTaskUI, index));//test
		}
		catch (Exception e)
		{
		}
	}
	
	private boolean testDelAndSub(ArrayList<Task> taskList, TaskUIWrapper testTaskUI, int index)
	{
		Task temp = testTaskUI.getTasks().get(index);
		testTaskUI.delect(index);
		
		ArrayList<Integer> delIndex = new ArrayList<Integer>();
		delIndex.add(index);
		
		if(!testTaskUI.getTasks().contains(temp))//check for element deleted
		{
			for(int i=0; i<temp.getSubtasks().size(); ++i)//check for subtasks deleted
			{
				if(!testTaskUI.getTasks().contains(temp.getSubtasks().get(i)))
				{
					delIndex.add(taskList.indexOf(temp.getSubtasks().get(i)));//add index of the subtasks in tasks to delIndex
					continue;
				}
				else
				{
					return false;//subtasks not deleted
				}
			}
			for(int i=0; i<taskList.size(); ++i)//test for every other element than the deleted one remains in the list 
			{
				if(delIndex.contains(i) ||
						testTaskUI.getTasks().contains(taskList.get(i)))
				{
					continue;
				}
				else
				{
					return false;
				}
			}
		}
		else
		{
			return false;
		}
		return true;//delete runs as expected
	}
	
	private boolean testDelAndSuper(ArrayList<Task> taskList, TaskUIWrapper testTaskUI, int index)
	{
		Task temp = testTaskUI.getTasks().get(index);
		testTaskUI.delect(index);
		
		if(!testTaskUI.getTasks().contains(temp))//check for element deleted
		{
			
			for(int i=0; i<taskList.size(); ++i)//test for every other element than the deleted one remains in the list 
			{
				if(i==index ||
						testTaskUI.getTasks().contains(taskList.get(i)))
				{
					continue;
				}
				else
				{
					return false;
				}
			}
		}
		else
		{
			return false;
		}
		if(temp.getSuperTask()!=null)//check for subtask reference in super task deleted
		{
			if(testTaskUI.getTasks().get(testTaskUI.getTasks().indexOf(temp.getSuperTask())).getSubtasks().contains(temp))
			{
				return false;//super task reference not deleted
			}
		}
		return true;//delete runs as expected
	}
	
	private void TestAdd()
	{
		try
		{
			//setup
			InputValidator validator = new InputValidator();
			PeopleReader peopleReader = new PeopleReader(validator);
			TaskReader taskReader = new TaskReader(validator);	
			ArrayList<Person> people = peopleReader.loadPeople(Common.PEOPLE_FILE);
			ArrayList<Task> tasks = taskReader.loadTasks(Common.TASKS_FILE);
			
			TaskEditor taskEdit = new TaskEditor(new TardisShellMock(), tasks, people, 0);
			
			
		}
		catch (Exception ex)
		{
			
		}
	}
}

