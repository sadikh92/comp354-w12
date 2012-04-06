package com.Team3.Tardis.Models.XML;

import java.util.ArrayList;

import com.Team3.Tardis.Models.Task;
/**
 * @author Eric Regnier,Jaffari Rahmatullah
 * @Description Interface for the Task Writer
 * @Last modified 3/11/12 12:01
 */

public interface ITasksWriter {

	 boolean writeTasks(String path, ArrayList<Task> tasks) throws Exception;

}
