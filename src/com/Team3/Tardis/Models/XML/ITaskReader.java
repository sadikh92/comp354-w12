package com.Team3.Tardis.Models.XML;

import java.util.ArrayList;

import com.Team3.Tardis.Models.*;
/**
 * @author Eric Regnier,Alex Landovskis,Jaffari Rahmatullah
 * @Description Interface for the Task Reader
 * @Last modified 3/7/12 20:10
 */

public interface ITaskReader {

	ArrayList<Task> loadTasks(String path) throws Exception;

}
