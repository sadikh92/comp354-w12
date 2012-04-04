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

				Iterator<Pointer> tasksIt = ctx.iteratePointers("/tasks/task");

				while (tasksIt.hasNext()) {

					Pointer taskPtr = tasksIt.next();	
					
					//Retrieves the task information and adds the new task to the arrayList of tasks
					Task task = loadTask(ctx.getRelativeContext(taskPtr), tasks);
					tasks.add(task);
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
	private Task loadTask(JXPathContext taskCtx, ArrayList<Task> tasks) throws Exception {
		
		//Stores the ID of the super task
		long idLong;
		//Stores the ID of the successor
		long pidLong;
		
		Logger.log(PeopleReader.class.getName(), "loadPerson() - START ");
		
		String errorMessage = inputValidator.validateTask(taskCtx);
		
		if (errorMessage.equals("")) {
			
			Task task = new Task();
			
			// required fields
			task.setTaskId(Long.parseLong(taskCtx.getValue("id").toString()));
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
			
			
			//Setting the super task/sub task relationship
			
			//If the task has a super task
			if(taskCtx.getValue("superTaskId") != null && !"".equals(taskCtx.getValue("superTaskId")))
			{
				//Read in the id of the super task
				idLong = Long.parseLong(taskCtx.getValue("superTaskId").toString());
				
				//Search the arrayList of tasks to find the super task
				for (Task tempTask : tasks)
				{
					if (tempTask.getTaskId() == idLong)
					{
						//Set the super task of the new task to the task found
						task.setSuperTask(tempTask);
						
						//Add the new task to the super task's sub task arrayList
						task.getSuperTask().addSubtask(task);
						
						break;
					}
				}
			}
			//completion
			task.setCompletionPercentage(taskCtx.getValue("completionPercentage") == null ? 0
					: Integer.parseInt(taskCtx.getValue("completionPercentage").toString()));
			//Setting the successor relationship
			
			//If the task has a successor
			if(taskCtx.getValue("successorId") != null && !"".equals(taskCtx.getValue("successorId")))
			{
				//Read in the id of the successor task
				pidLong = Long.parseLong(taskCtx.getValue("successorId").toString());
				
				//Search the arrayList of tasks to find the successor
				for (Task tempTask1 : tasks)
				{
					if (tempTask1.getTaskId() == pidLong)
					{
						//Set the successor of the new task to the task found
						task.setSuccessor(tempTask1);
						
						//this task is added to the predecessor list of its successor
						task.getSuccessor().addPredecessor(task);
						break;
					}
				}
			}
			
			
			Logger.log(PeopleReader.class.getName(), "loadPerson() - END ");
			return task;
		} else {
			
			Logger.log(PeopleReader.class.getName(), "loadPerson() - Error = " + errorMessage);
			throw new Exception(errorMessage);
		}
		//here
		
	}
}