package nl.han.dea.oose.persistence.data;

import nl.han.dea.oose.domain.Playlist;
import nl.han.dea.oose.domain.Track;

import java.util.List;

/**
 * Created by root on 22-3-17.
 */
public interface IPlaylistDAO {
    List<Playlist> findAllPlaylists();
    List<Playlist> findPlayistByOwner(String owner);
    Playlist getPlaylistByNameAndOwner(String name, String owner);
    void savePlaylist(Playlist playlist);
    void deletePlaylist(String name, String owner);
    void addTrack(Playlist playlist);
    void deleteTrack(String owner, String name, List<Track> tracks);
}
