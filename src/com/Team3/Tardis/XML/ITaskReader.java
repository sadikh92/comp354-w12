package com.Team3.Tardis.XML;

import java.util.ArrayList;

import com.Team3.Tardis.Models.*;

public interface ITaskReader {

	ArrayList<Task> loadTasks(String path) throws Exception;

}
