package com.Team3.Tardis.View;
import java.util.ArrayList;

import com.Team3.Tardis.Models.*;

public interface IOutputter {
	
	String outputResults(ArrayList<Task> tasks, ArrayList<Person> people);
	
}
