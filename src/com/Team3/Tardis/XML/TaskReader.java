package com.Team3.Tardis.XML;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.Team3.Tardis.Models.Task;
import com.Team3.Tardis.XML.Helper.XPathHelper;
import com.Team3.Tardis.logger.Logger;

public class TaskReader implements ITaskReader{

	@Override
	public ArrayList<Task> loadTasks(String path) {

		Logger.log("loadTask() - START ");
		Logger.log("docPath = " + path);
		ArrayList<Task> tasksList = new ArrayList<Task>();
		InputStream is =  null;
		
		try {
			is = new FileInputStream(path);

			Logger.log("is = " + is);			
			org.w3c.dom.Document doc = XPathHelper.getDocument(is);
			
			String query = "//people/person";
			Logger.log("query = " + query);
			NodeList nl = XPathHelper.getNodeList(doc, query);
			Logger.log("nl.getLength() = " + nl.getLength());
			for(int i=0; i< nl.getLength(); i++)
			{
				Task task = new Task();
				
				Node n = nl.item(i).getChildNodes().item(1);
				if(n != null && n.getFirstChild() != null)
					task.setTaskId(Integer.parseInt(n.getFirstChild().getNodeValue()));
					
				n = nl.item(i).getChildNodes().item(2);
				if(n != null && n.getFirstChild() != null)
					task.setTitle(n.getFirstChild().getNodeValue());
					
				n = nl.item(i).getChildNodes().item(3);
				if(n != null && n.getFirstChild() != null)
					task.setShortDescription(n.getFirstChild().getNodeValue());
				
				n = nl.item(i).getChildNodes().item(4);
				if(n != null && n.getFirstChild() != null)
					task.setDuration(Integer.parseInt(n.getFirstChild().getNodeValue()));
				
				n = nl.item(i).getChildNodes().item(6);
				if(n != null && n.getFirstChild() != null){	
					try {
						DateFormat formatter = new SimpleDateFormat("MM/dd/yy");
						task.setDueDate((Date)formatter.parse((n.getFirstChild().getNodeValue())));
					} catch (ParseException e) {
					}
				}
				
				tasksList.add(task);
			}
			
			is.close();		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			Logger.log("FILENOTFOUND EXCEPTION " + e.getMessage());
		} catch (MalformedURLException e) {
			e.printStackTrace();
			Logger.log(" MalformedURLException " + e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
			Logger.log("IOException " + e.getMessage());
		}

		Logger.log("loadTask() - END ");
		return tasksList;		
	}
}
