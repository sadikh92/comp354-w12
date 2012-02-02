package com.Team3.Tardis.XML;

import org.apache.commons.jxpath.JXPathContext;
import org.w3c.dom.Node;

public interface IInputValidator {

	String validatePerson(JXPathContext personNode);

	String validateTask(Node taskNode);
}
