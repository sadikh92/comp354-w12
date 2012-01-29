/*
 * Created on Jan 29, 2012
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.Team3.Tardis.XML.Helper;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.apache.commons.jxpath.JXPathContext;
import org.apache.xpath.XPathAPI;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import com.Team3.Tardis.logger.Logger;


/**
 * @author team3
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class XPathHelper 
{
	public static final String className = XPathHelper.class.getName();
	
	public static Document getDocument(Object xmlFile)
	{
		Logger.log("getDocument - START");

		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder;
		Document doc = null;
		try {
			docBuilder = docBuilderFactory.newDocumentBuilder();
			
			if(xmlFile instanceof String)			
				doc = docBuilder.parse(new File(xmlFile.toString()));// Parse the XML file and build the Document object in RAM
			else if(xmlFile instanceof InputStream)
				doc = docBuilder.parse((InputStream)xmlFile);// Parse the XML file and build the Document object in RAM
				
		} catch (ParserConfigurationException e) {
			Logger.log("ParserConfigurationException: " + e.getMessage());
		} catch (SAXParseException err) {
			Logger.log("SAXParseException" + ", line " + err.getLineNumber() + ", uri " + err.getSystemId() + "\n" + "   " + err.getMessage());	  		
		}catch (SAXException e) {
			Logger.log("SAXException: " + e.getMessage());
		} catch (IOException e) {
			Logger.log("IOException: " + e.getMessage());
		}

		Logger.log("getDocument - END");
		return doc;
  	}
	
	public static JXPathContext getDocumentContext(Object xmlFile)
	{
		Logger.log("getDocumentContext - START");
		
		Document doc = getDocument(xmlFile);
		JXPathContext ctx = JXPathContext.newContext(doc);
		
		Logger.log("getDocumentContext - END");
		return ctx;
  	}
  	
	public static NodeList getNodeList(Document doc, String query)
	{
		Logger.log("getNodeList - START");
		
		NodeList nl = null;
		try {
			nl = XPathAPI.selectNodeList(doc, query);
		} catch (TransformerException e) {
			Logger.log("TransformerException: " + e.getMessage());
		}

		Logger.log("getNodeList - END");
		return nl;
	}
}
