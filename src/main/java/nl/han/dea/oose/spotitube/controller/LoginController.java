package nl.han.dea.oose.spotitube.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import nl.han.dea.oose.domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = {"/index"})
public class LoginController extends HttpServlet {

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("spotitube/view/Login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            try{
                Client client = Client.create();
                WebResource webResource = client.resource("http://localhost:8080/checkuser/login");
                String input = "{\"username\":\"" + req.getParameter("username") + "\",\"password\":\"" + req.getParameter("password") + "\"}";
                ClientResponse response = webResource.type("application/json").post(ClientResponse.class, input);

                if(response != null){
                    String jsonResp = response.getEntity(String.class);
                    User user = mapper.readValue(jsonResp, User.class);
                    HttpSession session = req.getSession();
                    session.setAttribute("USER", user.getUsername());
                }
            resp.sendRedirect("/playlists");
            }catch (Exception e){
                e.printStackTrace();
            }
    }
}
