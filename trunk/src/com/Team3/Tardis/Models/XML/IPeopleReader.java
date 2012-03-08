package com.Team3.Tardis.Models.XML;

import java.util.ArrayList;

import com.Team3.Tardis.Models.Person;

/**
 * @author Eric Regnier
 * @description Interface for the People Reader
 *
 */
public interface IPeopleReader {

	ArrayList<Person> loadPeople(String path) throws Exception;

}
