package com.Team3.Tardis.Views.Tests;

import java.awt.Component;
import java.awt.TextField;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.swing.JComboBox;

import com.Team3.Tardis.Models.Person;
import com.Team3.Tardis.Models.Task;
import com.Team3.Tardis.Views.ITardisShell;
import com.Team3.Tardis.Views.TaskEditor;

/**
 * @author Eric Regnier
 */
public class TaskEditorWrapper extends TaskEditor {

	public TaskEditorWrapper(ITardisShell shell, ArrayList<Task> tasks, ArrayList<Person> people) {
		super(shell, tasks, people);
	}
	
	public TaskEditorWrapper(ITardisShell shell, ArrayList<Task> tasks, ArrayList<Person> people, int index) {
		super(shell, tasks, people, index);
	}

	public void setTitle(String title)
	{
		tTitle.setText(title);		
	}
	
	public void setDescription(String desc)
	{
		tDesc.setText(desc);		
	}
	
	public void setDuration(int duration)
	{
		tDuration.setText(duration + "");		
	}
	
	public void setDeliverable(String deliverable)
	{
		tDeliverable.setText(deliverable);		
	}
	
	public void setDueDate(Date date)
	{
		tMonth.setText(date.getMonth() + 1 + "");	
		tYear.setText(date.getYear() + 1900 + "");
		tDay.setText(date.getDate() + "");
	}
	
	public void setPerson(int personId)
	{
		cPeople.setSelectedItem(personId + "");		
	}

	public void setParentTaskLabel(int taskId)
	{
		cSuperL.setText(taskId + "");	
	}
	
	public void setParentTask(int taskId)
	{
		cSuper.setSelectedItem(taskId + "");		
	}
}
