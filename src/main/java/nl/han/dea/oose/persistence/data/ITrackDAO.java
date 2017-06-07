package nl.han.dea.oose.persistence.data;

import nl.han.dea.oose.domain.Track;

import java.sql.ResultSet;
import java.util.List;

/**
 * Created by root on 21-3-17.
 */
public interface ITrackDAO {

    List<Track> findAllTracks();
    Track findByTitle(String title);
    Track getTrack(ResultSet rs);
}
