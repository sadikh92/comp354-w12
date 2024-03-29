package com.Team3.Tardis.Models;

import java.util.ArrayList;
import java.util.Date;
/**
 * @author Eric Regnier,Kam Yip,Alex Landovskis,Adam Anderson,Jaffari Rahmatullah,
 * David Campbell,Babacar Ndiaye
 * @Description Task class
 * @Last modified 7/4/12 7:57
 */
public class Task {

	private long taskId;
	private String title;
	private String shortDescription; 
	private int duration;
	private String deliverable;
	private Date beginDate;
	private Date dueDate;
	private int personId;
	private ArrayList<Task> subTasks;
	private Task superTask;
	private int level;
	private int completionPercentage;
	//The current task must finish before its successor may start.
	//Successors can be super tasks, sibling tasks,
	//or (in the case that the current task has no super task) another task with no super task.
	private Task successor;	
	//The current task can only start once its predecessors have finished.
	//Predecessors can be sub tasks, sibling tasks,
	//or (in the case that the current task has no super task) another task with no super task.
	private ArrayList<Task> predecessors;
	private String status;
	
	//Default constructor to get rid of the empty arrayList problem
	public Task()
	{
		taskId = new Date().getTime();
		predecessors = new ArrayList<Task>();
		subTasks = new ArrayList<Task>();
		superTask = null;
		successor = null;
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
	
	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
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
	
	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	
	public int getCompletionPercentage() {
		return completionPercentage;
	}

	public void setCompletionPercentage(int completionPercentage) {
		this.completionPercentage = completionPercentage;
	}
	
	public Task getSuccessor(){
		return successor;
	}
	
	public void setSuccessor(Task successor){
		this.successor = successor;
	}
	
	public ArrayList<Task> getPredecessors() {
		return predecessors;
	}
	
	public void addPredecessor(Task task) {
		predecessors.add(task);
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	//Set status and percentage 
	public void updateCompletion()
	{
		//if task has no predecessors
		if (this.getPredecessors().isEmpty() && this.getCompletionPercentage() == 0)
		{
			this.setStatus("WaitingToRun");
		}
		else if (this.getPredecessors().isEmpty() && this.getCompletionPercentage() == 100)
		{
			this.setStatus("Complete");
			//other option: maybe delete from list of predecessor of its successor
		}
		else if (this.getPredecessors().isEmpty() && this.getCompletionPercentage() != 0)
		{
			this.setStatus("In progress");
		}
		//if task has predecessors 
		else if (!this.getPredecessors().isEmpty())
		{		
			//compute percentage in predecessor array list
			int temp = 0;
			
			for(int i = 0; i < this.getPredecessors().size() ; i++)
			{
				temp = temp + (this.getPredecessors().get(i).getCompletionPercentage());
			}
			
			int result = temp/(this.getPredecessors().size());
			
			//if all predecessors are completed,task is ready to start
			if (result == 100 && this.getCompletionPercentage() == 0)
			{
				this.setStatus("WaitingToRun");
			}
			//if all predecessors are completed and task ran to completion
			else if (result == 100 && this.getCompletionPercentage() == 100)
			{
				this.setStatus("Complete");
			}
			//if all predecessors are completed and task is running
			else if (result == 100 && this.getCompletionPercentage() != 0)
			{
				this.setStatus("In progress");
			}
			//if predecessors are not completed yet
			else if (result!=100)
			{
				this.setStatus("WaitingForPredecessorToComplete");
				
				//reset to 0 if user tries to set completion percentage for a task
				//before all its predecessors are complete.
				this.setCompletionPercentage(0);
			}
		}			
		else
		{
			this.setStatus("Unknown/Invalid Status");
		}
	}
}