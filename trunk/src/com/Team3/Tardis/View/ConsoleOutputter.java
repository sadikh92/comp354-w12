package com.Team3.Tardis.View;

import java.util.ArrayList;

import com.Team3.Tardis.Models.Person;
import com.Team3.Tardis.Models.Task;

public class ConsoleOutputter implements IOutputter {

	@Override
	public String outputResults(ArrayList<Task> tasks, ArrayList<Person> people) {

		StringBuilder output = new StringBuilder();
		for (Person person : people) {
		
			output.append(String.format("Task(s) for person: ID, %d %s %s\n", person.getPersonId(),
					person.getFirstName(), person.getLastName()));
			
			//TODO:
			output.append(String.format("    Task 1\n"));
			output.append(String.format("    Task 2\n"));
			//we need to get the tasks for the person's ID and display them one by one
			//output.append(String.format("    Task 1: %s\n", task name);
		}
		return output.toString();
	}
}
