package com.Team3.Tardis.Views;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import com.Team3.Tardis.Models.Person;
import com.Team3.Tardis.Models.Task;

/*
 * @author David Campbell,Babacar Ndiaye
 * @description Prepare and output table 1
 */
public class TaskView implements ITaskView {

	/*
	 * @description Output the table view of tasks
	 * 
	 * @param path The path to the output file.
	 * 
	 * @param people The people to output.
	 * 
	 * @param tasks The tasks to output.
	 */
	public void view(String path, ArrayList<Person> people, ArrayList<Task> tasks) {
		// Open the file.
		try {
			FileWriter fileWriter = new FileWriter(path);
			PrintWriter writer = new PrintWriter(fileWriter);

			int personID = 0;

			// Output people.
			for (int i = 0; i != tasks.size(); ++i) {
				String title = tasks.get(i).getTitle();
				writer.println(title);
				writer.println("-id: " + tasks.get(i).getTaskId());
				writer.println("-Short Description: " + tasks.get(i).getShortDescription());
				writer.println("-Duration: " + tasks.get(i).getDuration());

				writer.println("-Deliverable: " + tasks.get(i).getDeliverable());
				writer.println("-Due Date: " + tasks.get(i).getDueDate());
				writer.println("-completionPercentage : " + tasks.get(i).getCompletionPercentage());

				personID = tasks.get(i).getPersonId();

				// Looks for the name of the person with the ID read
				for (Person tempPerson : people) {
					if (tempPerson.getPersonId() == personID) {
						String name = tempPerson.getFirstName() + " " + tempPerson.getLastName();
						writer.println("-Person: " + name);
						break;
					}
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