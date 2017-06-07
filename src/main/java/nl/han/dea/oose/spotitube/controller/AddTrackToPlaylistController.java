package nl.han.dea.oose.spotitube.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import nl.han.dea.oose.domain.Playlist;
import nl.han.dea.oose.domain.Track;
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

@WebServlet(urlPatterns = {"/addtracktoplaylist"})
public class AddTrackToPlaylistController extends HttpServlet {

    ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebClient client = WebClient.create("http://localhost:8080/").path("/trackservice").accept("application/json");
        List<Track> trackList = (ArrayList<Track>)client.getCollection(Track.class);

        req.setAttribute("TRACK_LIST", trackList);

        req.getRequestDispatcher("/spotitube/view/AddTrackToPlaylist.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if("search".equals(req.getParameter("searchtrack"))){
            WebClient client = WebClient.create("http://localhost:8080/").path("/trackservice/" + req.getParameter("trackname")).accept("application/json");
            Track track = client.get(Track.class);

            req.setAttribute("TRACK", track);

            req.getRequestDispatcher("/spotitube/view/AddTrackToPlaylist.jsp").forward(req, resp);
        }else if("add".equals(req.getParameter("addtrack"))){
            try{
                HttpSession session = req.getSession();
                Client client = Client.create();
                WebResource webResource = client.resource("http://localhost:8080/playlistservice/addtrack");

                List<Track> track = new ArrayList<>();
                track.add(new Track(req.getParameter("preformer"), req.getParameter("title")));
                Playlist playlist = new Playlist(session.getAttribute("USER").toString(), req.getParameter("playlistname"), track);
                String input = mapper.writeValueAsString(playlist);
                ClientResponse response = webResource.type("application/json").post(ClientResponse.class, input);

                if(response != null){
                    resp.sendRedirect("/playlistdetails?playlistname=" + req.getParameter("playlistname"));
                }

            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
