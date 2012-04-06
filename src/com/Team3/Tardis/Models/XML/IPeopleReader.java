package com.Team3.Tardis.Models.XML;

import java.util.ArrayList;

import com.Team3.Tardis.Models.Person;

/**
 * @author Eric Regnier,Alex Landovskis
 * @Description Interface for the people reader
 * @Last modified 3/7/12 20:10
 */
public interface IPeopleReader {

	ArrayList<Person> loadPeople(String path) throws Exception;

}
