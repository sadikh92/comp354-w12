package com.Team3.Tardis.Views;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.plaf.TreeUI;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import com.Team3.Tardis.Models.Person;
import com.Team3.Tardis.Models.Task;

//import org.eclipse
/*
 * @author Alex Landovskis
 * Parts of TreeView from PeopleUI class.
 * @description Displays the tasks and subtasks as a tree.
 */
public class TreeView extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8025858802929065772L;
	private TardisShell shell;
	private ArrayList<Task> tasks;
	
	private JPanel treePanel;
	private DefaultTreeModel treeModel;
	private TreeUI treeUI;
	private JTree tree;
	
	public TreeView(TardisShell shell, ArrayList<Task> tasks)
	{
		this.shell = shell;
		this.tasks = tasks;
		
		setLayout(new BorderLayout());
		
		//Creates the table part
		treePanel();

		//Adds the two to the existing panel
		add(treePanel, BorderLayout.NORTH);
	}
	/*
	 * @description Setup the tree view.
	 */
	private void treePanel()
	{
		// Source http://docs.oracle.com/javase/tutorial/uiswing/components/tree.html
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root");
		
		root = setTaskInfo(root);
		treeModel = new DefaultTreeModel(root);
		
		tree = new JTree(treeModel);
		treePanel = new JPanel();
		treePanel.setLayout(new GridLayout(1, 0));
		
		JScrollPane scrollPane = new JScrollPane(tree);
		add(scrollPane);
	}	
	
	/*
	 * @description Load the tasks into the table.
	 */
	private DefaultMutableTreeNode setTaskInfo(DefaultMutableTreeNode root)
	{
		Task task;
		Task subTask;
		int i;
		int j;
		int m;
		int n = tasks.size();
		DefaultMutableTreeNode taskNode;
		DefaultMutableTreeNode subTaskNode;
		ArrayList<Task> subTasks;
		
		// Put task title into the tree.
		for (i = 0; i < n; i++)
		{
			task = tasks.get(i);
			
			if (task.getSuperTask() == null) // The current task is not a subtask.
			{
				subTasks = task.getSubtasks();
				m = subTasks.size();
			
				taskNode = new DefaultMutableTreeNode(task.getTitle());
				// Put subtasks in the tree.
				for (j = 0; j < m; j++)
				{
					subTask = subTasks.get(j);
					subTaskNode = new DefaultMutableTreeNode(subTask.getTitle());
					taskNode.add(subTaskNode);
				}
				root.add(taskNode);
			}
		}
		return root;
	}
	
	public void update()
	{
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root");
		setTaskInfo(root);
		
		treeModel = new DefaultTreeModel(root);
		tree.setModel(treeModel);
	}
}