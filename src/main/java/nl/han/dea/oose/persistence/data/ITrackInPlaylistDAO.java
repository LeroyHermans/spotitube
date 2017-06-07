package nl.han.dea.oose.persistence.data;

import nl.han.dea.oose.domain.Track;

import java.util.List;

/**
 * Created by root on 27-3-17.
 */
public interface ITrackInPlaylistDAO {

    List<Track> getTracksInPlaylist(String owner, String name);
    void saveTracksToPlaylist(String owner, String name, List<Track> tracks);
    void deleteTracksFromPlaylist(String owner, String name, List<Track> track);
    void deleteAllTracksFromPlaylist(String owner, String name);
}
