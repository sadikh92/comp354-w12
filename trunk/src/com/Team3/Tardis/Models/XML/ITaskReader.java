package com.Team3.Tardis.Models.XML;

import java.util.ArrayList;

import com.Team3.Tardis.Models.*;

/**
 * @author Eric Regnier
 * @description Interface for the Task Reader
 *
 */
public interface ITaskReader {

	ArrayList<Task> loadTasks(String path) throws Exception;

}
