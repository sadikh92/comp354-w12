package com.Team3.Tardis.XML;

import java.util.regex.Pattern;

import org.apache.commons.jxpath.JXPathContext;
import org.apache.commons.jxpath.JXPathException;

import com.Team3.Tardis.logger.Logger;

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

		Logger.log("START: id check");
		try {
			if (personCtx.getValue("id") == null || !Pattern.matches(NON_NEGATIVE_INTEGER_FIELD, personCtx.getValue("id").toString()))
				errorMessage.append("Invalid person ID\n");
			Logger.log("END: id check " + errorMessage);

		} catch (JXPathException e) {
			errorMessage.append("Missing person ID node\n");
		}
		
		Logger.log("START: first name check");
		try {
			if (personCtx.getValue("firstName") == null || !Pattern.matches(TEXT_FIELD, personCtx.getValue("firstName").toString()))
				errorMessage.append("Invalid first name\n");
			Logger.log("END: first name check " + errorMessage);
		} catch (JXPathException e) {
			errorMessage.append("Missing first name node\n");
		}

		Logger.log("START: last name check");
		try {
			if (personCtx.getValue("lastName") == null || !Pattern.matches(TEXT_FIELD, personCtx.getValue("lastName").toString()))
				errorMessage.append("Invalid last name\n");
			Logger.log("END: last name check " + errorMessage);
		} catch (JXPathException e) {
			errorMessage.append("Missing last name node\n");
		}

		Logger.log("START: phone number check");
		try {
			Object value = personCtx.getValue("phoneNumber");
			if (value != null && !value.toString().equals("") && !Pattern.matches(PHONENUMBER_FIELD, value.toString()))
				errorMessage.append("Invalid phone number\n");
			Logger.log("END: phone number check " + errorMessage);
		} catch (JXPathException e) {
		}

		Logger.log("START: address check ");
		try {
			Object value = personCtx.getValue("address");
			if (value != null && !value.toString().equals("") && !Pattern.matches(TEXT_FIELD, value.toString()))
				errorMessage.append("Invalid address\n");
			Logger.log("END: address check " + errorMessage);
		} catch (JXPathException e) {
		}

		Logger.log("START: city check " + errorMessage);
		try {
			Object value = personCtx.getValue("city");
			if (value != null && !value.toString().equals("") && !Pattern.matches(TEXT_FIELD, value.toString()))
				errorMessage.append("Invalid city\n");
			Logger.log("END: city check " + errorMessage);
		} catch (JXPathException e) {
		}

		Logger.log("START: postal code check");
		try {
			Object value = personCtx.getValue("postalCode");
			if (value != null && !value.toString().equals("") && !Pattern.matches(POSTAL_CODE_FIELD, value.toString()))
				errorMessage.append("Invalid postal code\n");
			Logger.log("END: postal code check " + errorMessage);
		} catch (JXPathException e) {
		}

		Logger.log("START: province check");
		try {
			Object value = personCtx.getValue("province");
			if (value != null && !value.toString().equals("") && !Pattern.matches(TEXT_FIELD, value.toString()))
				errorMessage.append("Invalid province\n");
			Logger.log("END: province check " + errorMessage);
		} catch (JXPathException e) {
		}

		Logger.log("START: country check " + errorMessage);
		try {
			Object value = personCtx.getValue("country");
			if (value != null && !value.toString().equals("") && !Pattern.matches(TEXT_FIELD, value.toString()))
				errorMessage.append("Invalid country\n");
			Logger.log("END: country check " + errorMessage);
		} catch (JXPathException e) {
		}
		
		return errorMessage.toString();
	}

	@Override
	public String validateTask(JXPathContext taskCtx) {

		StringBuilder errorMessage = new StringBuilder();

		try {
			if (taskCtx.getValue("id") == null || !Pattern.matches(NON_NEGATIVE_INTEGER_FIELD, taskCtx.getValue("id").toString()))
				errorMessage.append("Invalid task ID\n");

		} catch (JXPathException e) {
			errorMessage.append("Missing task ID node\n");
		}

		try {
			if (taskCtx.getValue("title") == null || !Pattern.matches(TEXT_FIELD, taskCtx.getValue("title").toString()))
				errorMessage.append("Invalid title\n");
		} catch (JXPathException e) {
			errorMessage.append("Missing title node\n");
		}

		try {
			if (taskCtx.getValue("description") == null || !Pattern.matches(TEXT_FIELD, taskCtx.getValue("description").toString()))
				errorMessage.append("Invalid description\n");
		} catch (JXPathException e) {
			errorMessage.append("Missing description node\n");
		}

		try {
			Object value = taskCtx.getValue("duration");
			if (value != null && !value.toString().equals("") && !Pattern.matches(TEXT_FIELD, value.toString()))
				errorMessage.append("Invalid duration\n");
		} catch (JXPathException e) {
		}

		try {
			Object value = taskCtx.getValue("deliverable");
			if (value != null && !value.toString().equals("") && !Pattern.matches(TEXT_FIELD, value.toString()))
				errorMessage.append("Invalid deliverable\n");
		} catch (JXPathException e) {
		}

		try {
			Object value = taskCtx.getValue("deliverable");
			if (value != null && !value.toString().equals("") && !Pattern.matches(TEXT_FIELD, value.toString()))
				errorMessage.append("Invalid deliverable\n");
		} catch (JXPathException e) {
		}

		try {
			Object value = taskCtx.getValue("dueDate");
			if (value != null && !value.toString().equals("") && !Pattern.matches(TEXT_FIELD, value.toString()))
				errorMessage.append("Invalid dueDate\n");
		} catch (JXPathException e) {
		}

		return errorMessage.toString();
	}
}
