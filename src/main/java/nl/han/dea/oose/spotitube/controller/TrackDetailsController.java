package nl.han.dea.oose.spotitube.controller;

import nl.han.dea.oose.domain.Song;
import nl.han.dea.oose.domain.Track;
import nl.han.dea.oose.domain.Video;
import org.apache.cxf.jaxrs.client.WebClient;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = {"/trackdetails"})
public class TrackDetailsController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if(req.getParameterMap().containsKey("title") && session.getAttribute("USER") != null || req.getParameterMap().containsKey("preformer")){

            WebClient client = WebClient.create("http://localhost:8080/").path("/trackservice/" + req.getParameter("title")).accept("application/json");

            Track track = client.get(Track.class);
            if("Song".equals(track.getType())){
                Song song = client.get(Song.class);
                req.setAttribute("SONG", song);
            }else if("Video".equals(track.getType())){
                Video video = client.get(Video.class);
                req.setAttribute("VIDEO", video);
            }

            req.getRequestDispatcher("/spotitube/view/TrackDetails.jsp").forward(req, resp);
        }else if(session.getAttribute("USER") != null){
            req.getRequestDispatcher("/playlists").forward(req, resp);
        }else{
            req.getRequestDispatcher("/index").forward(req, resp);
        }

    }
}

