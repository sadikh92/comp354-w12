package com.Team3.Tardis.Views.Tests;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;

import com.Team3.Tardis.Controller.TardisController;
import com.Team3.Tardis.Models.Person;
import com.Team3.Tardis.Models.Task;
import com.Team3.Tardis.Models.XML.PeopleReader;
import com.Team3.Tardis.Models.XML.TaskReader;
import com.Team3.Tardis.Models.XML.TaskWriter;
import com.Team3.Tardis.Util.InputValidator;
import com.Team3.Tardis.Views.TaskEditor;
import com.Team3.Tardis.Views.TaskUI;

/**
 * @author Joe Yip, David Campbell
 * @Description Tests the TaskUI methods
 * @Last modified 7/4/12 8:55
 */
public class TaskUITests {
	
	/**
	 * @Description Tests the delete on a task that has no super tasks, but has subtasks
	 */
	@Test
	public void TestDeleteAndSub()
	{
		int index = 0;//the delete index
		
		try
		{
			//setup
			InputValidator validator = new InputValidator();
			PeopleReader peopleReader = new PeopleReader(validator);
			TaskReader taskReader = new TaskReader(validator);	
			ArrayList<Person> people = peopleReader.loadPeople(Common.PEOPLE_FILE);
			ArrayList<Task> tasks = taskReader.loadTasks(Common.TASKS_FILE);
			ArrayList<Task> unchangedTasks = taskReader.loadTasks(Common.TASKS_FILE);
			
			TaskUIWrapper testTaskUI = new TaskUIWrapper(new TardisShellMock(), tasks, people);
			
			//Calls the delete method on the UI
			testTaskUI.delete(index);
			
			//Tests to see that everything that should have been deleted was
			testIfDeleted(unchangedTasks, testTaskUI, index);
			
			//Tests to see that the predecessor/successor relationship is correct
			checkSuccessorPredecessor(unchangedTasks, testTaskUI, index);
			
			//Tests to see that nothing extra was deleted
			//The number of deleted tasks should be equal to the number of the task's subtasks
			//plus the task itself
			int numDeleted = unchangedTasks.size() - testTaskUI.getTasks().size();
			assertTrue(numDeleted == (countSubTasks(unchangedTasks.get(index)) + 1));
		}
		catch (Exception e)
		{
			fail("exception occured");
		}
	}
	
	/**
	 * @Description Tests the delete on a task that has both a super task and a sub task
	 */
	@Test
	public void TestDeleteAndSubAndSuper()
	{
		int index = 2;//the delete index
		
		try
		{
			//setup
			InputValidator validator = new InputValidator();
			PeopleReader peopleReader = new PeopleReader(validator);
			TaskReader taskReader = new TaskReader(validator);	
			ArrayList<Person> people = peopleReader.loadPeople(Common.PEOPLE_FILE);
			ArrayList<Task> tasks = taskReader.loadTasks(Common.TASKS_FILE);
			ArrayList<Task> unchangedTasks = taskReader.loadTasks(Common.TASKS_FILE);
			
			TaskUIWrapper testTaskUI = new TaskUIWrapper(new TardisShellMock(), tasks, people);
			
			//Calls the delete method on the UI
			testTaskUI.delete(index);
			
			//Tests to see that everything that should have been deleted was
			testIfDeleted(unchangedTasks, testTaskUI, index);
			
			//Tests to see that the predecessor/successor relationship is correct
			checkSuccessorPredecessor(unchangedTasks, testTaskUI, index);
			
			//Tests to see that nothing extra was deleted
			//The number of deleted tasks should be equal to the number of the task's subtasks
			//plus the task itself
			int numDeleted = unchangedTasks.size() - testTaskUI.getTasks().size();
			assertTrue(numDeleted == (countSubTasks(unchangedTasks.get(index)) + 1));
		}
		catch (Exception e)
		{
			fail("exception occured");
		}
	}
	
	/**
	 * @Description Tests the delete on a task that has a super task, but has no subtasks
	 */
	@Test
	public void TestDeleteAndSuper()
	{
		int index = 3;//the delete index
		
		try
		{
			//setup
			InputValidator validator = new InputValidator();
			PeopleReader peopleReader = new PeopleReader(validator);
			TaskReader taskReader = new TaskReader(validator);	
			ArrayList<Person> people = peopleReader.loadPeople(Common.PEOPLE_FILE);
			ArrayList<Task> tasks = taskReader.loadTasks(Common.TASKS_FILE);
			ArrayList<Task> unchangedTasks = taskReader.loadTasks(Common.TASKS_FILE);
			
			TaskUIWrapper testTaskUI = new TaskUIWrapper(new TardisShellMock(), tasks, people);
			
			//Calls the delete method on the UI
			testTaskUI.delete(index);
			
			//Tests to see that everything that should have been deleted was
			testIfDeleted(unchangedTasks, testTaskUI, index);
			
			//Tests to see that the predecessor/successor relationship is correct
			checkSuccessorPredecessor(unchangedTasks, testTaskUI, index);
			
			//Tests to see that nothing extra was deleted
			//The number of deleted tasks should be equal to the number of the task's subtasks
			//plus the task itself
			int numDeleted = unchangedTasks.size() - testTaskUI.getTasks().size();
			assertTrue(numDeleted == (countSubTasks(unchangedTasks.get(index)) + 1));
		}
		catch (Exception e)
		{
			fail("exception occured");
		}
	}
	
	/**
	 * @Description Tests the save function
	 */
	@Test
	public void TestSave()
	{
		try
		{
			InputValidator validator = new InputValidator();
			TaskReader taskReader = new TaskReader(validator);
			ArrayList<Task> tasks = taskReader.loadTasks(Common.TASKS_FILE);
			
			testSave(tasks);
		}
		catch (Exception e)
		{
			fail("exception occured");
		}
	}
	
	//Tests if an element has been deleted from an arrayList
	private void testIfDeleted(ArrayList<Task> taskList, TaskUIWrapper testTaskUI, int index)
	{
		Task temp = taskList.get(index);
		
		int indexToTest = 0;
		
		try
		{
			//The testTask list should not contain the task that we deleted
			for (Task tempTask : testTaskUI.getTasks())
			{
				assertFalse(tempTask.getTaskId() == temp.getTaskId());
			}
			
			//For all of the subtasks of the deleted task
			for(Task subTask : temp.getSubtasks())
			{
				//Determine the subtask's position in the original array
				for (int i = 0; i != taskList.size(); ++i)
				{
					if (subTask.getTaskId() == taskList.get(i).getTaskId())
					{
						indexToTest = i;
						break;
					}
				}
				
				//Test to see that the subtask was also deleted
				testIfDeleted(taskList, testTaskUI, indexToTest);
			}
		}
		catch(Exception e)
		{
			fail("exception occured");
		}
	}
	
	//Tests to see that the predecessor/successor relationship is correctly updated during a delete
	private void checkSuccessorPredecessor(ArrayList<Task> unchangedTasks, TaskUIWrapper testTaskUI, int index)
	{
		//Test to see that the original successor no longer depends on it
		if (unchangedTasks.get(index).getSuccessor() != null)
		{
			//For all of the tasks that still exist
			for (Task tempTask : testTaskUI.getTasks())
			{
				//If we found the original successor
				if (tempTask.getTaskId() == unchangedTasks.get(index).getSuccessor().getTaskId())
				{
					//For all of its predecessors
					for (Task tempPredecessor : tempTask.getPredecessors())
					{
						//The id of the predecessor should not be the same as the id of the task that we
						//deleted
						assertFalse(tempPredecessor.getTaskId() == unchangedTasks.get(index).getTaskId());
					}
				}
			}
		}
		
		//Test to see that the original predecessor no longer considers it to be a successor
		//For all the predecessors of the original task
		for (Task predecessor : unchangedTasks.get(index).getPredecessors())
		{
			//The predecessor is found in the updated task list
			for (Task tempTask : testTaskUI.getTasks())
			{
				//If we found the original predecessor
				if (tempTask.getTaskId() == predecessor.getTaskId())
				{
					//The id of the successor should be null since its successor task was deleted
					assertNull(tempTask.getSuccessor());
				}
			}
		}
	}
	
	//Tests the contents of a task list loaded from a save file compared to the original task list
	private void testSave(ArrayList<Task> oldTaskList)
	{
		try
		{	
			//Create a new task list
			ArrayList<Task> newTaskList = new ArrayList<Task>();
			
			//Populate it with the objects of the old task list
			for(int i=0; i<oldTaskList.size(); i++)
			{
				newTaskList.add(oldTaskList.get(i));
			}
			
			//Create a new task and add it to the new list
			Task newTask = new Task();
			newTask.setTitle("New task for save testing");
			newTask.setShortDescription("New task for save testing");
			newTask.setDuration(10);
			newTask.setDeliverable("New task deliverable");
			newTask.setBeginDate(new Date());
			newTask.setDueDate(new Date());
			newTask.setPersonId(4);
			newTaskList.add(newTask);

			//Save the new list
			TaskWriter writer = new TaskWriter();
			writer.writeTasks(Common.SAVE_FILE, newTaskList);//save
			
			//Read in the saved list
			InputValidator validator = new InputValidator();
			TaskReader taskReader = new TaskReader(validator);
			ArrayList<Task> loadTasks = taskReader.loadTasks(Common.SAVE_FILE);//load
			
			//Compare that the task list that was read in is the same as the one that was saved
			for (int i = 0; i != loadTasks.size(); ++i)
			{
				assertEquals(loadTasks.get(i).getTaskId(), newTaskList.get(i).getTaskId());
				assertEquals(loadTasks.get(i).getTitle(), newTaskList.get(i).getTitle());
				assertEquals(loadTasks.get(i).getShortDescription(), newTaskList.get(i).getShortDescription());
				assertEquals(loadTasks.get(i).getDuration(), newTaskList.get(i).getDuration());
				assertEquals(loadTasks.get(i).getDeliverable(), newTaskList.get(i).getDeliverable());
				assertEquals(loadTasks.get(i).getBeginDate().toString(), newTaskList.get(i).getBeginDate().toString());
				assertEquals(loadTasks.get(i).getDueDate().toString(), newTaskList.get(i).getDueDate().toString());
				assertEquals(loadTasks.get(i).getPersonId(), newTaskList.get(i).getPersonId());
				
				if (loadTasks.get(i).getSuperTask() != null)
				{
					assertEquals(loadTasks.get(i).getSuperTask().getTaskId(), newTaskList.get(i).getSuperTask().getTaskId());
				}
				else
				{
					assertNull(newTaskList.get(i).getSuperTask());
				}
				
				assertEquals(loadTasks.get(i).getCompletionPercentage(), newTaskList.get(i).getCompletionPercentage());
				
				if (loadTasks.get(i).getSuccessor() != null)
				{
					assertEquals(loadTasks.get(i).getSuccessor().getTaskId(), newTaskList.get(i).getSuccessor().getTaskId());
				}
				else
				{
					assertNull(newTaskList.get(i).getSuccessor());
				}
			}
		}
		catch (Exception e)
		{
			fail("exception occured");
		}
	}
	
	//Recursive method for determining the number of subTasks, sub-subtasks, etc. of a task
	private int countSubTasks(Task task)
	{
		int numberOfChildren = 0;
		
		for (Task subTask : task.getSubtasks())
		{
			numberOfChildren += countSubTasks(subTask);
		}
		
		numberOfChildren += task.getSubtasks().size();
		
		return numberOfChildren;
	}
}

