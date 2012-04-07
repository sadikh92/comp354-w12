package com.Team3.Tardis.Models.XML;

import java.util.ArrayList;

import com.Team3.Tardis.Models.Task;
import com.Team3.Tardis.Models.IO.FileHelper;
import com.Team3.Tardis.Util.Logger;
/**
 * @author Jaffari Rahmatullah,David Campbell,Babacar Ndiaye
 * @Description write tasks to a file.
 * @Last modified 4/5/12 20:34
 */
public class TaskWriter implements ITasksWriter {

	static final String XML_START = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>";
	
	public boolean writeTasks(String path, ArrayList<Task> tasks) throws Exception {

		Logger.log(TaskWriter.class.getName(), "writeTasks() - START ");
		
		String content = serialize(tasks);
		boolean valid = FileHelper.write(path, content);
		

		Logger.log(TaskWriter.class.getName(), "writeTasks() - END ");
		return valid;
	}
	
	private String serialize(ArrayList<Task> tasks) {

		Logger.log(TaskWriter.class.getName(), "serialize() - START ");

		StringBuffer sb = new StringBuffer();
		
		sb.append(XML_START);
		sb.append("\n<tasks>");
		
		for(Task task : tasks) {
			
			sb.append("\n\t<task>");
			sb.append("\n\t\t<id>"+task.getTaskId()+"</id>");
			sb.append("\n\t\t<title>"+task.getTitle()+"</title>");
			sb.append("\n\t\t<description>"+task.getShortDescription()+"</description>");
			sb.append("\n\t\t<duration>"+task.getDuration()+"</duration>");
			sb.append("\n\t\t<deliverable>"+task.getDeliverable()+"</deliverable>");
			sb.append("\n\t\t<beginDate>"+task.getBeginDate()+"</beginDate>");
			sb.append("\n\t\t<dueDate>"+task.getDueDate()+"</dueDate>");
			sb.append("\n\t\t<personId>"+task.getPersonId()+"</personId>");
			
			
			//If the task has a super task, the super task's ID is printed
			sb.append("\n\t\t<superTaskId>"+((task.getSuperTask() != null) ? task.getSuperTask().getTaskId() : "" )+
					  "</superTaskId>");
			//completion
			sb.append("\n\t\t<completionPercentage>"+task.getCompletionPercentage()+"</completionPercentage>");
			//If the task has a successor , the successor ID is printed
			sb.append("\n\t\t<successorId>"+((task.getSuccessor() != null) ? task.getSuccessor().getTaskId() : "" )+
					  "</successorId>");
				
			sb.append("\n\t</task>");
		}
		
		sb.append("\n</tasks>");

		Logger.log(TaskWriter.class.getName(), "serialize() - END ");
		return sb.toString();
	}
}
