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
		int i;
		int n = tasks.size();
		DefaultMutableTreeNode taskNode;
		
		// Put task title into the tree.
		for (i = 0; i < n; i++)
		{
			task = tasks.get(i);
			taskNode = new DefaultMutableTreeNode(task.getTitle());
			
			if (task.getSuperTask() == null) // The current task is not a subtask.
			{
				taskNode = traverse(task);
				root.add(taskNode);
			}
		}
		return root;
	}
	
	/*
	 * @description Update the tree.
	 */
	public void update()
	{
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root");
		setTaskInfo(root);
		
		treeModel = new DefaultTreeModel(root);
		tree.setModel(treeModel);
	}
	
	/*
	 * @description Traverse the tasks and subtasks to build a tree.
	 */
	public DefaultMutableTreeNode traverse(Task task)
	{
		int i;
		int n;
		Task subTask;
		ArrayList<Task> subTasks = task.getSubtasks();
		DefaultMutableTreeNode treeNode = new DefaultMutableTreeNode(task.getTitle());
		DefaultMutableTreeNode subNode;
		n = subTasks.size();

		// Keep traversing subtasks until the leaf task is found.
		for (i = 0; i < n; i++)
		{
			subTask = subTasks.get(i);
			subNode = traverse(subTask);
			treeNode.add(subNode);
		}
		return treeNode;
	}
}