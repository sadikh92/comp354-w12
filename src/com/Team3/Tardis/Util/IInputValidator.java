package com.Team3.Tardis.Util;

import org.apache.commons.jxpath.JXPathContext;

public interface IInputValidator {

	String validatePerson(JXPathContext personNode);

	String validateTask(JXPathContext taskNode);
}
