package com.Team3.Tardis.Views.Tests;

import java.util.ArrayList;
import javax.swing.tree.DefaultMutableTreeNode;
import com.Team3.Tardis.Models.Task;
import com.Team3.Tardis.Views.ITardisShell;
import com.Team3.Tardis.Views.TreeView;

/**
 * @author David Campbell
 * @Description Allows us to run tests on the tree
 * @Last modified 6/4/12 11:35
 */
@SuppressWarnings("serial")
public class TreeViewWrapper extends TreeView
{
	public TreeViewWrapper(ITardisShell shell, ArrayList<Task> tasks)
	{
		super(shell, tasks);
	}
	
	public DefaultMutableTreeNode getRoot()
	{
		return rootForTest;
	}
}