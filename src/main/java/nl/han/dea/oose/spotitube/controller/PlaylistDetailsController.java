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

@WebServlet(urlPatterns = {"/playlistdetails"})
public class PlaylistDetailsController extends HttpServlet {

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if(req.getParameterMap().containsKey("playlistname") && session.getAttribute("USER") != null){

            WebClient client = WebClient.create("http://localhost:8080/").path("/playlistservice/playlistbyownerandname/" + session.getAttribute("USER").toString() +"/" +
            req.getParameter("playlistname")).accept("application/json");
            Playlist playlist = client.get(Playlist.class);

            req.setAttribute("PLAYLIST", playlist);

            req.getRequestDispatcher("/spotitube/view/PlaylistDetails.jsp").forward(req, resp);
        }else if(session.getAttribute("USER") != null){
            req.getRequestDispatcher("/playlists").forward(req, resp);
        }else{
            req.getRequestDispatcher("/index").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       if("delete".equals(req.getParameter("action"))){
           try{
               HttpSession session = req.getSession();
               Client client = Client.create();
               WebResource webResource = client.resource("http://localhost:8080/playlistservice/deletetrack");

               List<Track> tracks = new ArrayList<>();
               tracks.add(new Track(req.getParameter("preformer"), req.getParameter("title")));
               Playlist playlist = new Playlist(session.getAttribute("USER").toString(), req.getParameter("playlistname"), tracks);

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
