package ua.nure.kn.morozova.usermanagement;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class User implements Serializable {

	private static final long serialVersionUID = -7789603340108400175L;
	private Long id;
	private String firstName;
	private String lastName;
	private Date dateOfBirth;

	public User() {
	}

	public User(Long id, String firstName, String lastName, Date dateOfBirth) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;

	}

	public User(String firstname, String lastname, Date date) {
		this.firstName = firstname;
		this.lastName = lastname;
		this.dateOfBirth = date;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getFullName() {
		return (new StringBuilder(getLastName()).append(", ").append(getFirstName()).toString());
	}

	public int getAge() {
		Calendar currentCalendar = Calendar.getInstance();
		Calendar birthdayCalendar = Calendar.getInstance();
		birthdayCalendar.setTime(getDateOfBirth());
		int age = currentCalendar.get(Calendar.YEAR) - birthdayCalendar.get(Calendar.YEAR);

		if (currentCalendar.get(Calendar.DAY_OF_YEAR) < birthdayCalendar.get(Calendar.DAY_OF_YEAR) && age > 0) {
			age--;
		}

		return age;
	}

	@Override
	public int hashCode() {
		  if (this.getId() == null) {
			  return 0;
		  }
		  return this.getId().hashCode();
	}

	@Override
	public boolean equals(Object obj) {
	    if (obj == null) {
	    	return false;
	    }
	    if (this == obj) {
	    	return true;
	    }
	    if (this.getId() == null && ((User) obj).getId() == null) {
	    	return true;
	    }
	    return this.getId().equals(((User) obj).getId());
	}

}
