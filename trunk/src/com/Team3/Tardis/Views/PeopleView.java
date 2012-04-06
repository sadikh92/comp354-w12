package com.Team3.Tardis.Views;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import com.Team3.Tardis.Models.Person;
import com.Team3.Tardis.Models.Task;
/**
 * @author David Campbell,Alex Landovskis
 * @description Prepare and output table 2
 * @Last modified 3/4/12 14:13
 */

public class PeopleView implements IPeopleView {
	
	/*
	 * @description Output the people view.
	 * @param path The path to the output file.
	 * @param people The people to output.
	 * @param tasks The tasks to output.
	 * 
	 */
	public void view(String path, ArrayList<Person> people, ArrayList<Task> tasks)
	{
		// Open the file.
		try {
			FileWriter fileWriter = new FileWriter(path);
			PrintWriter writer = new PrintWriter(fileWriter);
			
			// Output people.
			ArrayList<Task> personTasks = null;
			for (Person person : people)
			{
				String name = person.getFirstName() + " " + person.getLastName();
				writer.println(name);
				writer.println("-id: " + person.getPersonId());
				writer.println("-phone: " + person.getPhoneNumber());
				writer.println("-address: " + person.getAddress());
				
				writer.println("-city: " + person.getCity());
				writer.println("-postalCode: " + person.getPostalCode());
				writer.println("-province: " + person.getProvince());
				writer.println("-country: " + person.getCountry());
				
				// Output tasks assigned to the current person.
				personTasks = person.getTasks(tasks);
				
				if (personTasks.size() <= 0) // No tasks assigned to the current person.
					writer.println("-tasks: None");
				else
					writer.println("-tasks: ");
				for (Task task : personTasks)
				{
					writer.println("    -" + task.getTitle());
				}
				writer.println();
			}
			writer.close();
			fileWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
