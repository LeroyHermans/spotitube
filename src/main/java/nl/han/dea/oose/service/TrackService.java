package nl.han.dea.oose.service;

import nl.han.dea.oose.persistence.data.ITrackDAO;
import nl.han.dea.oose.domain.Track;

import javax.inject.Inject;
import javax.ws.rs.*;
import java.util.List;

/**
 * Created by root on 22-3-17.
 */
@Path("/trackservice")
public class TrackService {

    @Inject
    private ITrackDAO trackDAO;

    @GET
    @Produces("application/json")
    public List<Track> getAllTracks(){
        return trackDAO.findAllTracks();
    }

    @GET
    @Path("/{name}")
    @Produces("application/json")
    public Track getTrackByTitle(@PathParam("name") String title){
        return trackDAO.findByTitle(title);
    }

}
