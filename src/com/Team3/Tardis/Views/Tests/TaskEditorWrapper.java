package com.Team3.Tardis.Views.Tests;

import java.awt.Component;
import java.awt.TextField;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JComboBox;

import com.Team3.Tardis.Models.Person;
import com.Team3.Tardis.Models.Task;
import com.Team3.Tardis.Views.ITardisShell;
import com.Team3.Tardis.Views.TaskEditor;

public class TaskEditorWrapper extends TaskEditor {

	private HashMap<String, Component> componentMap;

	private void createComponentMap() {
		componentMap = new HashMap<String, Component>();
		Component[] components = getContentPane().getComponents();
		for (int i = 0; i < components.length; i++) {
			componentMap.put(components[i].getName(), components[i]);
		}
	}

	public TextField getTextFieldByName(String name) {
		if (componentMap.containsKey(name)) {
			return (TextField) componentMap.get(name);
		} else
			return null;
	}
	
	public JComboBox getComboBoxByName(String name) {
		if (componentMap.containsKey(name)) {
			return (JComboBox) componentMap.get(name);
		} else
			return null;
	}
	
	public TaskEditorWrapper(ITardisShell shell, ArrayList<Task> tasks, ArrayList<Person> people) {
		super(shell, tasks, people);
		// TODO Auto-generated constructor stub
		

		createComponentMap();
	}
	
	public TaskEditorWrapper(ITardisShell shell, ArrayList<Task> tasks, ArrayList<Person> people, int index) {
		super(shell, tasks, people, index);
		// TODO Auto-generated constructor stub
		

		createComponentMap();
	}

	public void setTitle(String title)
	{
		getTextFieldByName("title").setText(title);		
	}

	public void setId(int id)
	{
		getTextFieldByName("id").setText(id + "");		
	}
	
	public void setDescription(String desc)
	{
		getTextFieldByName("desc").setText(desc);		
	}
	
	public void setDuration(int duration)
	{
		getTextFieldByName("duration").setText(duration + "");		
	}
	
	public void setDuration(String deliverable)
	{
		getTextFieldByName("deliverable").setText(deliverable);		
	}
	
	public void setDueDate(Date dueDate)
	{
		getTextFieldByName("month").setText(dueDate.getMonth() + "");	
		getTextFieldByName("year").setText(dueDate.getYear() + "");
		getTextFieldByName("day").setText(dueDate.getDay() + "");
	}
	
	public void setPerson(int personId)
	{
		getComboBoxByName("people").setSelectedItem(personId + "");		
	}

	public void setParentTask(int taskId)
	{
		getTextFieldByName("superL").setText(taskId + "");
		//getComboBoxByName("superL").setSelectedItem(taskId + "");		
	}

}
