package nl.han.dea.oose.spotitube.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import nl.han.dea.oose.domain.Playlist;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = {"/addplaylist"})
public class AddPlaylistsController extends HttpServlet {

    ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/spotitube/view/AddPlaylist.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if("add".equals(req.getParameter("addplaylist"))){
            try{
                HttpSession session = req.getSession();
                Client client = Client.create();
                WebResource webResource = client.resource("http://localhost:8080/playlistservice/saveplaylist");

                Playlist playlist = new Playlist(session.getAttribute("USER").toString(), req.getParameter("playlistname"));
                String input = mapper.writeValueAsString(playlist);
                ClientResponse response = webResource.type("application/json").post(ClientResponse.class, input);

                if(response != null){
                    resp.sendRedirect("/playlists");
                }

            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
