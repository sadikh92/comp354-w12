package com.Team3.Tardis.Models.XML;

import java.util.ArrayList;

import com.Team3.Tardis.Models.Person;

public interface IPeopleReader {

	ArrayList<Person> loadPeople(String path) throws Exception;

}
