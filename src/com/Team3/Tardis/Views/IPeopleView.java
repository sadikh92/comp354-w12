/**
 * 
 */
package com.Team3.Tardis.Views;

import java.util.ArrayList;

import com.Team3.Tardis.Models.Person;
import com.Team3.Tardis.Models.Task;

/**
 * @author Alex Landovskis
 *
 */
public interface IPeopleView {
	public void view(String path, ArrayList<Person> people, ArrayList<Task> tasks);
}
