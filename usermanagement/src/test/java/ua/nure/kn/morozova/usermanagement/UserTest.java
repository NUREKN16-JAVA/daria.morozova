package ua.nure.kn.morozova.usermanagement;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UserTest {

	private User user;
	
	@Before
	public void setUp() throws Exception {
		user = new User(1L, "Daria", "Morozova", new Date(98, 2, 21));
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetFullName() {
		String expected = user.getLastName() + ", " + user.getFirstName();	
		String actual = user.getFullName();
		assertEquals(expected, actual);
		
	}
	
	@Test
	public void testGetAgeWithTwentyYearsOld() {
		assertEquals(20, user.getAge());
	}

	@Test
	public void testGetAgeWithCurrentTime() {
		user.setDateOfBirth(new Date());
		assertEquals(0, user.getAge());

	}

	@Test
	public void testGetAgeNotNullAge() {
		assertNotNull(user.getAge());
	}

	@Test
	public void testGetAgeWithBirthdayTomorrow() {
		int year = user.getDateOfBirth().getYear();
		int month = new Date().getMonth();
		int day = new Date().getDate();
		Date dateTomorrow = new Date(year, month, day + 1);
		user.setDateOfBirth(dateTomorrow);

		assertEquals(19, user.getAge());
	}

	@Test
	public void testGetAgeWithDayBirthdayYesterday() {
		int year = user.getDateOfBirth().getYear();
		int month = new Date().getMonth();
		int day = new Date().getDate();
		Date dateYesterday = new Date(year, month, day - 1);
		user.setDateOfBirth(dateYesterday);

		assertEquals(20, user.getAge());

	}

	@Test
	public void testGetAgeWhenYearsAreDifferent() {
		assertEquals(20, user.getAge());
		user.setDateOfBirth(new Date(97, 2, 21));
		assertEquals(21, user.getAge());
	}

	
}


