package com.Team3.Tardis.XML;

import java.util.regex.Pattern;
import org.apache.commons.jxpath.JXPathContext;
import org.apache.commons.jxpath.JXPathException;
import org.w3c.dom.Node;

public class InputValidator implements IInputValidator {

	private static final String TEXT_FIELD = "^(\\S)(.){1,75}(\\S)$";
	private static final String NON_NEGATIVE_INTEGER_FIELD = "(\\d){1,9}";
	private static final String INTEGER_FIELD = "(-)?" + NON_NEGATIVE_INTEGER_FIELD;
	private static final String PHONENUMBER_FIELD = "^\\(?([2-9][0-8][0-9])\\)?[-. ]?([2-9][0-9]{2})[-. ]?([0-9]{4})$";
	private static final String POSTAL_CODE_FIELD = "^([A-Z]\\d[A-Z]\\s\\d[A-Z]\\d)$";

	private static final String DATE_FIELD = "^[0-1][0-9]/[0-3][0-9]/\\d{2}$";

	@Override
	public String validatePerson(JXPathContext personCtx) {

		StringBuilder errorMessage = new StringBuilder();

		try {
			if (personCtx.getValue("id") == null || !Pattern.matches(NON_NEGATIVE_INTEGER_FIELD, personCtx.getValue("id").toString()))
				errorMessage.append("Invalid person ID\n");

		} catch (JXPathException e) {
			errorMessage.append("Missing person ID node\n");
		}

		try {
			if (personCtx.getValue("firstName") == null || !Pattern.matches(TEXT_FIELD, personCtx.getValue("firstName").toString()))
				errorMessage.append("Invalid first name\n");
		} catch (JXPathException e) {
			errorMessage.append("Missing first name node\n");
		}

		try {
			if (personCtx.getValue("lastName") == null || !Pattern.matches(TEXT_FIELD, personCtx.getValue("lastName").toString()))
				errorMessage.append("Invalid last name\n");
		} catch (JXPathException e) {
			errorMessage.append("Missing last name node\n");
		}

		try {
			Object value = personCtx.getValue("phoneNumber");
			if (value != null && value.toString() != "" && !Pattern.matches(PHONENUMBER_FIELD, value.toString()))
				errorMessage.append("Invalid phone number\n");
		} catch (JXPathException e) {
		}

		try {
			Object value = personCtx.getValue("address");
			if (value != null && value.toString() != "" && !Pattern.matches(TEXT_FIELD, value.toString()))
				errorMessage.append("Invalid address\n");
		} catch (JXPathException e) {
		}

		try {
			Object value = personCtx.getValue("city");
			if (value != null && value.toString() != "" && !Pattern.matches(TEXT_FIELD, value.toString()))
				errorMessage.append("Invalid city\n");
		} catch (JXPathException e) {
		}

		try {
			Object value = personCtx.getValue("postalCode");
			if (value != null && value.toString() != "" && !Pattern.matches(POSTAL_CODE_FIELD, value.toString()))
				errorMessage.append("Invalid postal code\n");
		} catch (JXPathException e) {
		}

		try {
			Object value = personCtx.getValue("province");
			if (value != null && value.toString() != "" && !Pattern.matches(TEXT_FIELD, value.toString()))
				errorMessage.append("Invalid province\n");
		} catch (JXPathException e) {
		}

		try {
			Object value = personCtx.getValue("country");
			if (value != null && value.toString() != "" && !Pattern.matches(TEXT_FIELD, value.toString()))
				errorMessage.append("Invalid country\n");
		} catch (JXPathException e) {
		}
		
		return errorMessage.toString();
	}

	@Override
	public String validateTask(Node taskNode) {

		StringBuilder errorMessage = new StringBuilder();

		Node n = taskNode.getChildNodes().item(1);

		if (n == null || n.getFirstChild() == null)
			errorMessage.append("Missing task ID node\n");
		else {
			if (!Pattern.matches(INTEGER_FIELD, n.getFirstChild().getNodeValue()))
				;
			errorMessage.append("Invalid task ID\n");
		}

		n = taskNode.getChildNodes().item(2);
		if (n == null || n.getFirstChild() == null || n.getFirstChild().getNodeValue().trim().isEmpty())
			errorMessage.append("Missing title node\n");

		n = taskNode.getChildNodes().item(3);
		if (n == null || n.getFirstChild() == null || n.getFirstChild().getNodeValue().trim().isEmpty())
			errorMessage.append("Missing description node\n");

		n = taskNode.getChildNodes().item(4);
		String duration = n.getFirstChild().getNodeValue().trim();
		if (n == null || n.getFirstChild() != null || duration.isEmpty()) {
			errorMessage.append("Missing duration\n");
		} else if (!Pattern.matches(INTEGER_FIELD, duration)) {
			errorMessage.append("Invalid duration\n");
		}

		n = taskNode.getChildNodes().item(5);
		String date = n.getFirstChild().getNodeValue().trim();
		if (n == null || n.getFirstChild() != null || date.isEmpty()) {
			errorMessage.append("Missing date\n");
		} else if (!Pattern.matches(DATE_FIELD, date)) {
			errorMessage.append("Invalid date\n");
		}

		return errorMessage.toString();
	}
}
