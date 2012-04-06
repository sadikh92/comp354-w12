package com.Team3.Tardis.Util;

import org.apache.commons.jxpath.JXPathContext;
/**
 * @author Eric Regnier,Alex Landovskis,Jaffari Rahmatullah
 * @description interface for input validator
 * @Last modified 3/11/12 16:28
 */
public interface IInputValidator {

	String validatePerson(JXPathContext personNode);

	String validateTask(JXPathContext taskNode);
}
