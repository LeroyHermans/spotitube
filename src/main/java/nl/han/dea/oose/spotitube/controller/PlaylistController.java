package nl.han.dea.oose.spotitube.controller;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import nl.han.dea.oose.domain.Playlist;
import nl.han.dea.oose.domain.Track;
import nl.han.dea.oose.domain.User;
import org.apache.cxf.jaxrs.client.WebClient;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = {"/playlists"})
public class PlaylistController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        WebClient client = WebClient.create("http://localhost:8080/").path("/playlistservice/playlistbyowner/" + session.getAttribute("USER")).accept("application/json");
        List<Playlist> playlists = (ArrayList<Playlist>)client.getCollection(Playlist.class);

        req.setAttribute("PLAYLISTS", playlists);

        req.getRequestDispatcher("/spotitube/view/PlaylistOverview.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if("delete".equals(req.getParameter("delete"))){
            try{
                HttpSession session = req.getSession();
                Client client = Client.create();
                WebResource webResource = client.resource("http://localhost:8080/playlistservice/deleteplaylist");
                String input = "{\"owner\":\"" + session.getAttribute("USER") + "\",\"name\":\"" + req.getParameter("playlistname") + "\"}";
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
