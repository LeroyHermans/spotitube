package nl.han.dea.oose.persistence.data;

import nl.han.dea.oose.domain.Track;
import nl.han.dea.oose.persistence.connection.IConnection;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Default
public class TrackInPlaylistDAOMySQL implements ITrackInPlaylistDAO{

    @Inject
    private IConnection connectionFactory;

    @Inject
    private ITrackDAO trackDAO;

    public List<Track> getTracksInPlaylist(String owner, String name){
        List<Track> resultList = new ArrayList<>();
        String sqlGetTracksFromPlaylist = "SELECT t.*\n" +
                "FROM Track t\n" +
                "INNER JOIN TrackInPlaylist tip on\n" +
                "    tip.Title = t.Title AND tip.Preformer = t.Preformer\n" +
                "WHERE tip.Name=? AND tip.Owner=?";
        try(
                Connection con = connectionFactory.getConnection();
                PreparedStatement preparedStatement = con.prepareStatement(sqlGetTracksFromPlaylist)
        ){
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, owner);
            ResultSet rs = preparedStatement.executeQuery();
                while (rs.next()){
                    resultList.add(trackDAO.getTrack(rs));
                }
            }
        catch (SQLException e){
            e.printStackTrace();
        }
        return resultList;
    }

    public void saveTracksToPlaylist(String owner, String name, List<Track> tracks) {
        String sqlAddTracksToPlaylist = "INSERT INTO TrackInPlaylist(Owner, Name, Title, Preformer) VALUES (?,?,?,?)";
        try(
                Connection con = connectionFactory.getConnection();
                PreparedStatement preparedStatement = con.prepareStatement(sqlAddTracksToPlaylist)
                ){
            for(Track track : tracks){
                addTrack(preparedStatement, owner, name, track);
                preparedStatement.execute();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void deleteTracksFromPlaylist(String owner, String name, List<Track> tracks) {
        String sqlRemoveTrackFromPlaylist = "DELETE FROM TrackInPlaylist WHERE Name=? AND Owner=? AND Title=? AND Preformer=?";
        try(
                Connection con = connectionFactory.getConnection();
                PreparedStatement preparedStatement = con.prepareStatement(sqlRemoveTrackFromPlaylist)
        ){
            for(Track track : tracks){
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, owner);
                preparedStatement.setString(3, track.getTitle());
                preparedStatement.setString(4, track.getPreformer());
                preparedStatement.execute();
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void deleteAllTracksFromPlaylist(String owner, String name) {
        String sqlRemoveTracksFromPlaylist = "DELETE FROM TrackInPlaylist WHERE Name=? AND Owner=?";
        try(
                Connection con = connectionFactory.getConnection();
                PreparedStatement preparedStatement = con.prepareStatement(sqlRemoveTracksFromPlaylist)
        ){
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, owner);
            preparedStatement.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    private void addTrack(PreparedStatement ps,String owner, String name, Track track){
        try {
            ps.setString(1, owner);
            ps.setString(2, name);
            ps.setString(3, track.getTitle());
            ps.setString(4, track.getPreformer());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
