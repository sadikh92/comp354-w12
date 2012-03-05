//**************************************************
//THIS FILE IS NO LONGER USED. IT SHOULD BE DELETED.
//**************************************************

package com.Team3.Tardis.Views;

import java.util.ArrayList;

import com.Team3.Tardis.Models.Person;
import com.Team3.Tardis.Models.Task;
import com.Team3.Tardis.Models.XML.InputValidator;
import com.Team3.Tardis.Models.XML.PeopleReader;
import com.Team3.Tardis.Models.XML.TaskReader;

public class ConsoleApp {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		TaskReader tr = new TaskReader(new InputValidator());
		PeopleReader pr = new PeopleReader(new InputValidator());
		
		try {
			ArrayList<Task> tasks = tr.loadTasks("xml/tasks.xml");
			ArrayList<Person> people = pr.loadPeople("xml/people.xml");
			
			TardisShell.createAndShowGUI(tasks, people);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
