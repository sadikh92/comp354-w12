package com.Team3.Tardis.Models;

import java.util.ArrayList;
import java.util.Date;

public class Task {

	private long taskId;
	private String title;
	private String shortDescription; 
	private int duration;
	private String deliverable;
	private Date dueDate;
	private int personId;
	private ArrayList<Task> subTasks;
	private Task superTask;
	
	//Deafault constructor to get rig of the empty arrayList problem
	public Task()
	{
		subTasks = new ArrayList<Task>();
	}
	
	public long getTaskId() {
		return taskId;
	}

	public void setTaskId(long taskId) {
		this.taskId = taskId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}
	
	public String getDeliverable() {
		return deliverable;
	}

	public void setDeliverable(String deliverable) {
		this.deliverable = deliverable;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	
	public int getPersonId() {
		return personId;
	}

	public void setPersonId(int personId) {
		this.personId = personId;
	}
	
	//Returns a shallow copy so that the changes are actually made to the Task's arrayList
	public ArrayList<Task> getSubtasks() {
		return subTasks;
	}
	
	public void addSubtask(Task task) {
		subTasks.add(task);
	}
	
	public Task getSuperTask(){
		return superTask;
	}
	
	public void setSuperTask(Task superTask){
		this.superTask = superTask;
	}
}