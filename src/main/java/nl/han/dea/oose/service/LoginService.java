package nl.han.dea.oose.service;

import nl.han.dea.oose.persistence.data.ILoginDAO;
import nl.han.dea.oose.domain.User;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/checkuser")
public class LoginService {

    @Inject
    ILoginDAO loginDAO;

    @POST
    @Path("/login")
    @Consumes("application/json")
    @Produces("application/json")
    public User login(User user){
        return loginDAO.checkUser(user.getUsername(), user.getPassword());
    }
}
