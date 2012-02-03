package com.Team3.Tardis.Models;

import java.util.ArrayList;

public class Person {

	private int personId;
	private String firstName;
	private String lastName;
	private String address;
	private String phoneNumber;
	private String city;
	private String province;
	private String postalCode;
	private String country;
	
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getPersonId() {
		return personId;
	}

	public void setPersonId(int personId) {
		this.personId = personId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	/*
	 * @param tasks The global list of tasks.
	 * @description A list containing the person's tasks will be returned.
	 */
	public ArrayList<Task> getTasks(ArrayList<Task> tasks)
	{
		ArrayList<Task> myTasks = new ArrayList<Task>();
		
		for (Task task : tasks)
		{
			// The task belongs to the current person.
			if (task.getPersonId() == personId)
				myTasks.add(task);
		}
		return myTasks;
	}

}
