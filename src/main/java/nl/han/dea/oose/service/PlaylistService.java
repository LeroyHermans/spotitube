package nl.han.dea.oose.service;

import nl.han.dea.oose.persistence.data.IPlaylistDAO;
import nl.han.dea.oose.domain.Playlist;

import javax.inject.Inject;
import javax.ws.rs.*;
import java.util.List;

@Path("/playlistservice")
public class PlaylistService {

    @Inject
    private IPlaylistDAO playlistDAO;

    @GET
    @Produces("application/json")
    public List<Playlist> getAllPlaylists(){
        return playlistDAO.findAllPlaylists();
    }

    @GET
    @Path("/playlistbyowner/{name}")
    @Produces("application/json")
    public List<Playlist> findPlayistByOwner(@PathParam("name") String owner) {
        return playlistDAO.findPlayistByOwner(owner);
    }

    @GET
    @Path("/playlistbyownerandname/{owner}/{name}")
    @Produces("application/json")
    public Playlist findplaylistByownerAndName(@PathParam("owner") String owner, @PathParam("name") String name){
        return playlistDAO.getPlaylistByNameAndOwner(name, owner);
    }

    @POST
    @Path("/saveplaylist")
    @Consumes("application/json")
    public void savePlaylist(Playlist playlist) {
        playlistDAO.savePlaylist(playlist);
    }

    @POST
    @Path("/addtrack")
    @Consumes("application/json")
    public void addTrack(Playlist playlist) {
        playlistDAO.addTrack(playlist);
    }

    @POST
    @Path("/deletetrack")
    @Consumes("application/json")
    public void deleteTrack(Playlist playlist) {
        playlistDAO.deleteTrack(playlist.getOwner(), playlist.getName(), playlist.getTracks());
    }

    @POST
    @Path("/deleteplaylist")
    @Consumes("application/json")
    public void deletePlaylist(Playlist playlist) {
        playlistDAO.deletePlaylist(playlist.getName(), playlist.getOwner());
    }
}
