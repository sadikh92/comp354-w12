/**
 * 
 */
package com.Team3.Tardis.Views;

import java.util.ArrayList;

import com.Team3.Tardis.Models.Person;
import com.Team3.Tardis.Models.Task;
/**
 * @author David Campbell
 * @description Interface for Tardis Shell.
 * @Last modified 3/4/12 14:39
 */

public interface ITaskView {
	public void view(String path, ArrayList<Person> people, ArrayList<Task> tasks);
}