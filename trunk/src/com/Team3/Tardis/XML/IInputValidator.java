package com.Team3.Tardis.XML;

import org.w3c.dom.Node;

public interface IInputValidator {

	String validatePerson(Node personNode);

	String validateTasks(Node taskNode);
}
