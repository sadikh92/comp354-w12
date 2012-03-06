package com.Team3.Tardis.Models.XML;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import org.apache.commons.jxpath.JXPathContext;
import org.apache.commons.jxpath.Pointer;

import com.Team3.Tardis.Models.Task;
import com.Team3.Tardis.Util.IInputValidator;
import com.Team3.Tardis.Util.Logger;

/**
 * @description Reads tasks from a file.
 *
 */
public class TaskReader implements ITaskReader{

	private IInputValidator inputValidator;

	public TaskReader(IInputValidator inputValidator) {
		this.inputValidator = inputValidator;
	}
	
	/**
	 * @description Reads a task from the file.
	 * @param path The path to the tasks file.
	 *
	 */
	@Override
	public ArrayList<Task> loadTasks(String path) throws Exception {

		Logger.log(TaskReader.class.getName(), "loadTask() - START ");
		Logger.log(TaskReader.class.getName(), "docPath = " + path);
		ArrayList<Task> tasks = new ArrayList<Task>();
		InputStream is = null;
		File file = null;
		
		try {
			file = new File(path);
			
			// The file does not exist.
			if (!file.exists()) {
				System.out.println(path + " does not exist.");
				return tasks;
			}
			
			is = new FileInputStream(path);
			Logger.log(PeopleReader.class.getName(), "is = " + is);

			// The file exists.
			JXPathContext ctx = XPathHelper.getDocumentContext(is);

			if (ctx != null) {

				Iterator<Pointer> tasksIt = ctx
						.iteratePointers("/tasks/task");

				while (tasksIt.hasNext()) {

					Pointer taskPtr = tasksIt.next();	
					
					//Comment out next two lines to test sub tasks
					Task task = loadTask(ctx.getRelativeContext(taskPtr));
					tasks.add(task);
					
					//ArrayList<Task> tempTaskList = loadTask(ctx.getRelativeContext(taskPtr));
					/*
					//add the task and all its subtasks to the main task list
					for(int i =0; i<tempTaskList.size();i++)
					{
						tasks.add(tempTaskList.get(i));					
					}
					*/
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			Logger.log(TaskReader.class.getName(), "FILENOTFOUND EXCEPTION " + e.getMessage());
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (MalformedURLException e) {
					e.printStackTrace();
					Logger.log(PeopleReader.class.getName(), "MalformedURLException " + e.getMessage());
				} catch (IOException e) {
					e.printStackTrace();
					Logger.log(PeopleReader.class.getName(), "IOException " + e.getMessage());
				}
			}
		}

		Logger.log(TaskReader.class.getName(), "loadTask() - END ");
		return tasks;		
	}

	/**
	 * @description Reads a task from the file.
	 * @param taskCtx The XML context containing one task.
	 *
	 */
	
	//private ArrayList<Task> loadTask(JXPathContext taskCtx) throws Exception {
	private Task loadTask(JXPathContext taskCtx) throws Exception {
		
		Logger.log(PeopleReader.class.getName(), "loadPerson() - START ");
		
		String errorMessage = inputValidator.validateTask(taskCtx);
		
		if (errorMessage.equals("")) {
			
			// Temporary list to store new tasks in to return
			ArrayList<Task> tempList = new ArrayList<Task>();
			
			Task task = new Task();
			
			// required fields
			task.setTaskId(Integer.parseInt(taskCtx.getValue("id").toString()));
			task.setTitle(taskCtx.getValue("title").toString());
			task.setShortDescription(taskCtx.getValue("description").toString());

			// non required fields
			task.setDuration(taskCtx.getValue("duration") == null ? 0
					: Integer.parseInt(taskCtx.getValue("duration").toString()));
			task.setDeliverable(taskCtx.getValue("deliverable") == null ? "" : taskCtx
					.getValue("deliverable").toString());
			task.setDueDate(taskCtx.getValue("dueDate") == null ? null : 
					new Date(taskCtx.getValue("dueDate").toString()));
			task.setPersonId(taskCtx.getValue("personId") == null ? 0
					: Integer.parseInt(taskCtx.getValue("personId").toString()));
			
			/*
			 
			// add parent to list to be returned
			tempList.add(task);
					
			// subtask to be read here in the next increment - where it's needed 
			
			//need to loop through if there are sub tasks and not to if there are none
			
			//START LOOP
			
			// required fields
			Task subTask = new Task();
			subTask.setTaskId(Integer.parseInt(((JXPathContext) ((JXPathContext) taskCtx.getValue("subtasks")).getValue("subtask")).getValue("id").toString()));
			subTask.setTitle(((JXPathContext) ((JXPathContext) taskCtx.getValue("subtasks")).getValue("subtask")).getValue("title").toString());
			subTask.setShortDescription(((JXPathContext) ((JXPathContext) taskCtx.getValue("subtasks")).getValue("subtask")).getValue("description").toString());

			// non required fields
			subTask.setDuration(((JXPathContext) ((JXPathContext) taskCtx.getValue("subtasks")).getValue("subtask")).getValue("duration") == null ? 0
					: Integer.parseInt(((JXPathContext) ((JXPathContext) taskCtx.getValue("subtasks")).getValue("subtask")).getValue("duration").toString()));
			subTask.setDeliverable(((JXPathContext) ((JXPathContext) taskCtx.getValue("subtasks")).getValue("subtask")).getValue("deliverable") == null ? "" : ((JXPathContext) ((JXPathContext) taskCtx
					.getValue("subtasks")).getValue("subtask")).getValue("deliverable").toString());
			subTask.setDueDate(((JXPathContext) ((JXPathContext) taskCtx.getValue("subtasks")).getValue("subtask")).getValue("dueDate") == null ? null : 
					new Date(((JXPathContext) ((JXPathContext) taskCtx.getValue("subtasks")).getValue("subtask")).getValue("dueDate").toString()));
			
			// setting parent
			subTask.setSuperTask(task);
			
			// add sub tasks to the parents sub task list
			task.addSubtask(subTask);
			
			// add the sub task to the list to be returned
			tempList.add(subTask);
			//END LOOP
			 
			*/
			
			Logger.log(PeopleReader.class.getName(), "loadPerson() - END ");
			return task;
			//return tempList;
		} else {
			
			Logger.log(PeopleReader.class.getName(), "loadPerson() - Error = " + errorMessage);
			throw new Exception(errorMessage);
		}
	}
}