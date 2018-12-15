package ua.nure.kn.morozova.usermanagement.web;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ua.nure.kn.morozova.usermanagement.User;

public class BrowseServletTest extends MockServletTestCase {

	@Before
	protected void setUp() throws Exception {
		super.setUp();
        createServlet(BrowseServlet.class);
	}

	
    @Test
    public void testBrowse() {
        User user = new User(new Long(1000),"Dasha", "Morozova", new Date());
        List<User> list = Collections.singletonList(user);
        getMockUserDao().expectAndReturn("findAll", user);
        doGet();
        Collection<User> collection = (Collection<User>) getWebMockObjectFactory().getMockSession().getAttribute("users");
        assertNotNull("Could not find list of users in session", collection);
        assertSame(list, collection);
       
    }
    

}
