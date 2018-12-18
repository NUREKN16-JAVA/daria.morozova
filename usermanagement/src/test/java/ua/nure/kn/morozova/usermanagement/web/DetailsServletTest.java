package ua.nure.kn.morozova.usermanagement.web;


import ua.nure.kn.morozova.usermanagement.User;

public class DetailsServletTest extends MockServletTestCase  {

    @Override
    public void setUp() throws Exception {
        super.setUp();
        createServlet(DetailsServlet.class);
    }

    public void testBack() {
        addRequestParameter("backButton", "BACK");
        User nullUser = (User) getWebMockObjectFactory().getMockSession().getAttribute("user");
        assertNull("User must not exist in session scope", nullUser);
    }
}