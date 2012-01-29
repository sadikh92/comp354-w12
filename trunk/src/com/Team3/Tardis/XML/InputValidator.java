package com.Team3.Tardis.XML;

import java.util.regex.Pattern;

import org.w3c.dom.Node;

public class InputValidator implements IInputValidator {

	private static final String TEXT_FIELD = "^(\\S)(.){1,75}(\\S)$";
	private static final String NON_NEGATIVE_INTEGER_FIELD = "(\\d){1,9}";
	private static final String INTEGER_FIELD = "(-)?"
			+ NON_NEGATIVE_INTEGER_FIELD;
	private static final String PHONENUMBER_FIELD = "^\\(?([2-9][0-8][0-9])\\)?[-. ]?([2-9][0-9]{2})[-. ]?([0-9]{4})$";
	private static final String POSTAL_CODE_FIELD = "^([a-z]\\d){3}$/i";

	@Override
	public String validatePerson(Node personNode) {
		
		StringBuilder errorMessage = new StringBuilder();
		
		Node n = personNode.getChildNodes().item(1);
	
		if (n == null || n.getFirstChild() == null)
			errorMessage.append("Missing person ID node\n");
		else
		{
			if(!Pattern.matches(INTEGER_FIELD, n.getFirstChild().getNodeValue()));
				errorMessage.append("Invalid person ID\n");
		}
		
		n = personNode.getChildNodes().item(2);
		if (n == null || n.getFirstChild() == null)
			errorMessage.append("Missing first name node\n");
		else
		{
			if(!Pattern.matches(TEXT_FIELD, n.getFirstChild().getNodeValue()));
				errorMessage.append("Invalid first name\n");
		}
		
		n = personNode.getChildNodes().item(3);
		if (n == null || n.getFirstChild() == null)
			errorMessage.append("Missing last name node\n");
		else
		{
			if(!Pattern.matches(TEXT_FIELD, n.getFirstChild().getNodeValue()));
				errorMessage.append("Invalid last name\n");
		}
		
		n = personNode.getChildNodes().item(3);
		String value = n.getFirstChild().getNodeValue();
		if (n != null && n.getFirstChild() != null && value != "" && !Pattern.matches(PHONENUMBER_FIELD, value))
			errorMessage.append("Invalid phone number\n");

		n = personNode.getChildNodes().item(4);
		value = n.getFirstChild().getNodeValue();
		if (n != null && n.getFirstChild() != null && value != "" && !Pattern.matches(TEXT_FIELD, value))
			errorMessage.append("Invalid address\n");
		
		n = personNode.getChildNodes().item(5);
		value = n.getFirstChild().getNodeValue();
		if (n != null && n.getFirstChild() != null && value != "" && !Pattern.matches(TEXT_FIELD, value))
			errorMessage.append("Invalid city\n");
		
		n = personNode.getChildNodes().item(6);
		value = n.getFirstChild().getNodeValue();
		if (n != null && n.getFirstChild() != null && value != "" && !Pattern.matches(POSTAL_CODE_FIELD, value))
			errorMessage.append("Invalid postal code\n");
		
		n = personNode.getChildNodes().item(7);
		value = n.getFirstChild().getNodeValue();
		if (n != null && n.getFirstChild() != null && value != "" && !Pattern.matches(TEXT_FIELD, value))
			errorMessage.append("Invalid province\n");
		
		n = personNode.getChildNodes().item(8);
		value = n.getFirstChild().getNodeValue();
		if (n != null && n.getFirstChild() != null && value != "" && !Pattern.matches(TEXT_FIELD, value))
			errorMessage.append("Invalid country\n");
		
		return errorMessage.toString();
	}

	@Override
	public String validateTask(Node taskNode) {
		// TODO Auto-generated method stub
		return null;
	}

}
