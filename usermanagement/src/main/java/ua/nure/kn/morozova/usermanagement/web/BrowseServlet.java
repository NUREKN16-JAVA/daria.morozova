package ua.nure.kn.morozova.usermanagement.web;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.kn.morozova.usermanagement.User;
import ua.nure.kn.morozova.usermanagement.db.DaoFactory;
import ua.nure.kn.morozova.usermanagement.db.DatabaseException;

public class BrowseServlet extends HttpServlet {
	
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        if (req.getParameter("add") != null) {
//            add(req, resp);
//        } else if (req.getParameter("edit") != null) {
//            edit(req, resp);
//        } else if (req.getParameter("delete") != null) {
//            delete(req, resp);
//        } else if (req.getParameter("details") != null) {
//            details(req, resp);
//        } else {
            browse(req, resp);
        
    }

    private void browse(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Collection<User> users;
        try {
            users = DaoFactory.getInstance().getUserDao().findAll();
            req.getSession().setAttribute("users", users);
            req.getRequestDispatcher("/browse.jsp").forward(req, resp);
        } catch (DatabaseException e) {
            throw new ServletException(e);
        }

    }

}
