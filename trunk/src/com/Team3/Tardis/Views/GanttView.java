package com.Team3.Tardis.Views;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.IntervalCategoryDataset;
import org.jfree.data.gantt.TaskSeries;
import org.jfree.data.gantt.TaskSeriesCollection;
import org.jfree.data.time.SimpleTimePeriod;

import com.Team3.Tardis.Models.Task;

//import org.eclipse
/**
 * @author Rahmat Jaffari
 * Parts of GanttView from PeopleUI class.
 * @description Displays the tasks and subtasks in a Gantt chart.
 * @Last modified 5/4/12 8:55
 */
public class GanttView extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8025858802929065772L;
	private TardisShell shell;
	private ArrayList<Task> tasks;
	
	public GanttView(TardisShell shell, ArrayList<Task> tasks)
	{
		this.shell = shell;
		this.tasks = tasks;
		
		//Creates the gannt chart for the tasks and subtasks in the system
		createGanttPanel();
	}
	
	/**
	 * @description Creates the gannt chart for the tasks and subtasks in the system.
	 */
	private void createGanttPanel()
	{
		setLayout(new BorderLayout());
		
        IntervalCategoryDataset tasksDataset = createTasksDataset();
        JFreeChart ganttChart = createGanttChart(tasksDataset);

        // add the chart to a panel...
        ChartPanel chartPanel = new ChartPanel(ganttChart);
		
		JScrollPane scrollPane = new JScrollPane(chartPanel);
		add(scrollPane);
	}
	
	/**
	 * @description Update the tree.
	 */
	public void update()
	{
		// update gantt chart here
		this.removeAll(); 
		createGanttPanel();
	}	
	
	
	/**
     * Creates the tasks dataset for a Gantt chart.
     *
     * @return The dataset.
     */
    private IntervalCategoryDataset createTasksDataset() {

        TaskSeriesCollection collection = new TaskSeriesCollection();
        HashMap<String, TaskSeries> taskSeriesMap = new HashMap<String, TaskSeries>();
        
    	for (Task task : this.tasks)
    	{
			if (task.getSuperTask() == null) // The current task is not a subtask.
			{
				TaskSeries series;
				String seriesLabel = "0 - Tasks";
				
				if(taskSeriesMap.containsKey(seriesLabel))
					series = (TaskSeries)taskSeriesMap.get(seriesLabel);
				else 
					series = new TaskSeries(seriesLabel);
				
				series.add(new org.jfree.data.gantt.Task(task.getTitle(), new SimpleTimePeriod(task.getBeginDate(), task.getDueDate())));
				
		        taskSeriesMap.put(seriesLabel, series);
			}
			else
			{
				TaskSeries series;
				String seriesLabel = task.getLevel()+getOrdinalSuffix(task.getLevel()) + " Level Sub-task";
				Task tempTask = task.getSuperTask();
				
				while(tempTask.getSuperTask() != null)
					tempTask = tempTask.getSuperTask();
			
				if(taskSeriesMap.containsKey(seriesLabel))
					series = (TaskSeries)taskSeriesMap.get(seriesLabel);
				else 
					series = new TaskSeries(seriesLabel);
				
				series.add(new org.jfree.data.gantt.Task(tempTask.getTitle(), new SimpleTimePeriod(task.getBeginDate(), task.getDueDate())));
				
		        taskSeriesMap.put(seriesLabel, series);
			}
		}
    	
    	List<String> sortedKeys = new ArrayList<String>(taskSeriesMap.keySet());
    	Collections.sort(sortedKeys);
    	
    	for (Iterator<String> iterator = sortedKeys.iterator(); iterator.hasNext();)
    	{
	        collection.add(taskSeriesMap.get(iterator.next()));	
		}

        return collection;
    }

	
    /**
     * Creates a gantt chart out of the tasks dataset.
     * 
     * @param dataset the tasks dataset.
     * 
     * @return The chart.
     */
    private JFreeChart createGanttChart(IntervalCategoryDataset dataset) {
    	
        JFreeChart chart = ChartFactory.createGanttChart(
            "Tasks Gantt Chart", // chart title
            "Task",              // domain axis label
            "Time Period",       // range axis label
            dataset,             // data
            true,                // include legend
            true,                // tooltips
            false                // urls
        );
        
        return chart;    
    }

	
    /**
     * Gets the ordinal suffix of a number.
     * 
     * @param number the number to find its ordinal suffix.
     * 
     * @return The ordinal suffix text.
     */
    private String getOrdinalSuffix(int number) {
    	
        if(number == 0)
        	return "";
        else if(number == 1)
        	return "st";
        else if(number == 2)
        	return "nd";
        else if(number == 3)
        	return "rd";
        else
        	return "th";
    }
}