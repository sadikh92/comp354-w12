package com.Team3.Tardis.Models.XML;

import java.util.ArrayList;

import com.Team3.Tardis.Models.Person;
import com.Team3.Tardis.Models.Task;
import com.Team3.Tardis.Models.IO.FileHelper;
import com.Team3.Tardis.Util.Logger;

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
		

		StringBuffer sb = new StringBuffer();
		
		sb.append(XML_START);
		sb.append("\n<tasks>");
		
		for(Task task : tasks) {
			
			sb.append("\n\t<task>");
			sb.append("\n\t<id>0</id>");
			sb.append("\n\t\t<title>"+task.getTaskId()+"</title>");
			sb.append("\n\t\t<description>"+task.getShortDescription()+"</description>");
			sb.append("\n\t\t<duration>"+task.getDuration()+"</duration>");
			sb.append("\n\t\t<deliverable>"+task.getDeliverable()+"</deliverable>");
			sb.append("\n\t\t<dueDate>"+task.getDueDate()+"</dueDate>");
			sb.append("\n\t\t<personId>"+task.getPersonId()+"</personId>");
			
			/* TODO - subtask handling should be done here for increment 3
			if(task.getSubtasks() != null && task.getSubtasks().size() > 0) {

				sb.append("\n\t\t<subtasks>");
					<subtask>
						<id>0</id>
						<title>My first sub-task</title>
						<description>This is my first sub-task...</description>
						<duration>1</duration>
						<deliverable>First sub-task.doc</deliverable>
						<dueDate>01/23/2012</dueDate>
					</subtask>
				</subtasks>
				sb.append("\n\t\t</subtasks>");
			}
			else
				sb.append("\n\t\t<subtasks/>");
			*/
				
			sb.append("\n\t</task>");
		}
		
		sb.append("\n</tasks>");
		
		return sb.toString();
	}
}
