package com.Team3.Tardis.Models.XML;

import org.apache.commons.jxpath.JXPathContext;

public interface IInputValidator {

	String validatePerson(JXPathContext personNode);

	String validateTask(JXPathContext personNode);
}
