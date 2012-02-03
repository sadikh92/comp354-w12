package com.Team3.Tardis.XML;

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
import com.Team3.Tardis.XML.Helper.XPathHelper;
import com.Team3.Tardis.logger.Logger;

public class TaskReader implements ITaskReader{

	private IInputValidator inputValidator;

	public TaskReader(IInputValidator inputValidator) {
		this.inputValidator = inputValidator;
	}
	
	@Override
	public ArrayList<Task> loadTasks(String path) throws Exception {

		Logger.log(TaskReader.class.getName(), "loadTask() - START ");
		Logger.log(TaskReader.class.getName(), "docPath = " + path);
		ArrayList<Task> tasks = new ArrayList<Task>();
		InputStream is =  null;
		
		try {
			is = new FileInputStream(path);
			Logger.log(PeopleReader.class.getName(), "is = " + is);

			JXPathContext ctx = XPathHelper.getDocumentContext(is);

			if (ctx != null) {

				Iterator<Pointer> tasksIt = ctx
						.iteratePointers("/tasks/task");

				while (tasksIt.hasNext()) {

					Pointer taskPtr = tasksIt.next();					
					Task task = loadTask(ctx
							.getRelativeContext(taskPtr));
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

	private Task loadTask(JXPathContext taskCtx) throws Exception {
		
		Logger.log(PeopleReader.class.getName(), "loadPerson() - START ");
		
		String errorMessage = inputValidator.validateTask(taskCtx);
		
		if (errorMessage.equals("")) {
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
			
			// subtask to be read here in the next increment - where it's needed 
			
			Logger.log(PeopleReader.class.getName(), "loadPerson() - END ");
			return task;
		} else {
			
			Logger.log(PeopleReader.class.getName(), "loadPerson() - Error = " + errorMessage);
			throw new Exception(errorMessage);
		}
	}
}
