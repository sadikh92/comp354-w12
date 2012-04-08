package com.Team3.Tardis.Views.Tests;

import java.util.ArrayList;
import com.Team3.Tardis.Models.Task;
import com.Team3.Tardis.Models.Person;
import com.Team3.Tardis.Views.ITardisShell;
import com.Team3.Tardis.Views.TaskUI;

/**
 * @author David Campbell, Joe Yip
 * @Description Allows us to run tests on the Task UI
 * @Last modified 7/4/12 1:45
 */
@SuppressWarnings("serial")
public class TaskUIWrapper extends TaskUI
{
	public TaskUIWrapper(ITardisShell shell, ArrayList<Task> tasks, ArrayList<Person> people)
	{
		super(shell, tasks, people);
	}
	
	public ArrayList<Task> getTasks()
	{
		return tasks;
	}
	
	public void delete(int row)
	{
		super.delete(row);
	}
}