package ua.nure.kn.morozova.usermanagement.web;

import ua.nure.kn.morozova.usermanagement.User;

import java.text.DateFormat;
import java.util.Date;

public class AddServletTest extends MockServletTestCase {
    @Override
    public void setUp() throws Exception {
        super.setUp();
        createServlet(AddServlet.class);
    }
        public void testAdd() {
            Date date = new Date();
            User newUser = new User("Dasha", "Morozova", date);
            User user = new User(new Long(1000), "Dasha", "Morozova", date);
            getMockUserDao().expectAndReturn("create", newUser, user);

            addRequestParameter("firstName", "Dasha");
            addRequestParameter("lastName", "Morozova");
            addRequestParameter("date", DateFormat.getDateInstance().format(date));
            addRequestParameter("okButton", "Ok");
            doPost();
        }

    public void testAddEmptyFirstName() {
        Date date = new Date();
        addRequestParameter("lastName", "Morozova");
        addRequestParameter("date", DateFormat.getDateInstance().format(date));
        addRequestParameter("okButton", "Ok");
        doPost();
        String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
        assertNotNull("Could not find error message in session scope", errorMessage);
    }

    public void testAddEmptyLastName() {
        Date date = new Date();
        addRequestParameter("firstName", "Dasha");
        addRequestParameter("date", DateFormat.getDateInstance().format(date));
        addRequestParameter("okButton", "Ok");
        doPost();
        String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
        assertNotNull("Could not find error message in session scope", errorMessage);
    }

    public void testAddEmptyDate() {
        Date date = new Date();
        addRequestParameter("firstName", "Dasha");
        addRequestParameter("lastName", "Morozova");
        addRequestParameter("okButton", "Ok");
        doPost();
        String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
        assertNotNull("Could not find error message in session scope", errorMessage);
    }

        public void testAddEmptyDateIncorrect() {
            Date date = new Date();
            addRequestParameter("firstName", "Dasha");
            addRequestParameter("lastName", "Morozova");
            addRequestParameter("date", "parampampam");
            addRequestParameter("okButton", "Ok");
            doPost();
            String errorMessage = (String) getWebMockObjectFactory().getMockRequest().getAttribute("error");
            assertNotNull("Could not find error message in session scope", errorMessage);
        }

    }



