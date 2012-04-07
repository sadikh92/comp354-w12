package com.Team3.Tardis.Views.Tests;

import static org.junit.Assert.*;
import java.util.ArrayList;
import javax.swing.tree.DefaultMutableTreeNode;
import java.util.Enumeration;
import org.junit.Test;
import com.Team3.Tardis.Models.Task;
import com.Team3.Tardis.Models.XML.TaskReader;
import com.Team3.Tardis.Util.InputValidator;

/**
 * @author David Campbell
 * @Description Tests the Tree UI
 * @Last modified 7/4/12 12:14
 */
public class TreeViewTests {

	@Test
	/**
	 * @Description Test to make sure that an empty tree is correctly drawn 1
	 */
	public void testEmptyTree1() {
		try {
			//sets up the needed classes
			InputValidator validator = new InputValidator();
			TaskReader taskReader = new TaskReader(validator);
			ArrayList<Task> tasks = taskReader.loadTasks(Common.EMPTY_TASK_FILE1);
			
			TreeViewWrapper treeTest = new TreeViewWrapper(new TardisShellMock(), tasks);
			
			testTheTree(treeTest, tasks);

		} catch (Exception ex) {
			fail("exception occured");
		}
	}
	
	@Test
	/**
	 * @Description Test to make sure that an empty tree is correctly drawn 2
	 */
	public void testEmptyTree2() {
		try {
			//sets up the needed classes
			InputValidator validator = new InputValidator();
			TaskReader taskReader = new TaskReader(validator);
			ArrayList<Task> tasks = taskReader.loadTasks(Common.EMPTY_TASK_FILE2);
			
			TreeViewWrapper treeTest = new TreeViewWrapper(new TardisShellMock(), tasks);
			
			testTheTree(treeTest, tasks);

		} catch (Exception ex) {
			fail("exception occured");
		}
	}
	
	@Test
	/**
	 * @Description Test to make sure that a tree with no subtasks is correctly drawn
	 */
	public void testNoSubtasksTree() {
		try {
			//sets up the needed classes
			InputValidator validator = new InputValidator();
			TaskReader taskReader = new TaskReader(validator);
			ArrayList<Task> tasks = taskReader.loadTasks(Common.NO_SUBTASKS_FILE);
			
			TreeViewWrapper treeTest = new TreeViewWrapper(new TardisShellMock(), tasks);
			
			testTheTree(treeTest, tasks);

		} catch (Exception ex) {
			fail("exception occured");
		}
	}
	
	@Test
	/**
	 * @Description Test to make sure that a tree with at least one subtask is correctly drawn
	 */
	public void testSubtasksTree() {
		try {
			//sets up the needed classes
			InputValidator validator = new InputValidator();
			TaskReader taskReader = new TaskReader(validator);
			ArrayList<Task> tasks = taskReader.loadTasks(Common.TASKS_FILE);
			
			TreeViewWrapper treeTest = new TreeViewWrapper(new TardisShellMock(), tasks);
			
			testTheTree(treeTest, tasks);

		} catch (Exception ex) {
			fail("exception occured");
		}
	}
	
	//Tests to make sure that the tree matches the data
	private void testTheTree(TreeViewWrapper treeTest, ArrayList<Task> tasks)
	{
		DefaultMutableTreeNode root = treeTest.getRoot();
		
		//The enumeration of the tree nodes is created
		//Since the tree always has a Project root, we must start at the second element
		Enumeration e = root.breadthFirstEnumeration();
		e.nextElement();
		
		//Used to store the current node
		DefaultMutableTreeNode testNode;
		
		//For every task in the arrayList
		for (int i = 0; i != tasks.size(); ++i)
		{
			//As long as there are more tasks, the enumeration should have more elements
			assertTrue(e.hasMoreElements());
			
			//The node is set to the next node
			testNode = (DefaultMutableTreeNode)e.nextElement();
				
			//If the task has no subtasks, it should be a leaf in the tree
			if (tasks.get(i).getSubtasks().size() == 0)
			{
				assertTrue(testNode.isLeaf());
			}
			//If the task has subtasks, it should be a node in the tree
			else
			{
				assertFalse(testNode.isLeaf());
			}
				
			//In any case, the tree node and the task should have the same level
			assertEquals(testNode.getLevel() - 1, tasks.get(i).getLevel());
		}
	}
}