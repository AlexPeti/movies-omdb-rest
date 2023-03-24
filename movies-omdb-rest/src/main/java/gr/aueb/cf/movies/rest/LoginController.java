package gr.aueb.cf.movies.rest;

import gr.aueb.cf.movies.dao.IUserDAO;
import gr.aueb.cf.movies.dao.UserDAOImpl;
import gr.aueb.cf.movies.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/login")
public class LoginController {
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response authenticateUser(User user, @Context HttpServletRequest request) {
        try {
            IUserDAO userDAO = new UserDAOImpl();
            User authenticatedUser = userDAO.authenticate(user.getUsername(), user.getPassword());

            if (authenticatedUser != null) {
                // Set the session attributes
                HttpSession session = request.getSession(true);
                session.setAttribute("user", authenticatedUser);

                // Redirect to the index page
                String url = request.getContextPath() + "/index.html";
                String message = "Welcome back, " + authenticatedUser.getUsername();
                return Response.ok().entity("<script>window.location.replace('" + url + "');alert('" + message + "');</script>").build();
            } else {
                return Response.status(Response.Status.UNAUTHORIZED).build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
