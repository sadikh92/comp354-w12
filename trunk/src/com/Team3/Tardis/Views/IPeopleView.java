/**
 * 
 */
package com.Team3.Tardis.Views;

import java.util.ArrayList;

import com.Team3.Tardis.Models.Person;
import com.Team3.Tardis.Models.Task;
/**
 * @author Alex Landovskis,David Campbell
 * @description interface for people view
 * @Last modified 3/4/12 14:13
 */

public interface IPeopleView {
	public void view(String path, ArrayList<Person> people, ArrayList<Task> tasks);
}
