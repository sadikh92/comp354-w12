package com.Team3.Tardis.Models.XML;

import java.util.ArrayList;

import com.Team3.Tardis.Models.Task;

public interface ITasksWriter {

	 boolean writeTasks(String path, ArrayList<Task> tasks) throws Exception;

}
