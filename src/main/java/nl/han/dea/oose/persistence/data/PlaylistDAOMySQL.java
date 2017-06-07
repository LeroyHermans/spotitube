package nl.han.dea.oose.persistence.data;

import nl.han.dea.oose.domain.Playlist;
import nl.han.dea.oose.domain.Track;
import nl.han.dea.oose.persistence.connection.IConnection;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class PlaylistDAOMySQL implements IPlaylistDAO {

    @Inject
    private IConnection connectionFactory;

    @Inject
    ITrackInPlaylistDAO trackInPlaylistDAO;

    @Override
    public List<Playlist> findAllPlaylists() {
        List<Playlist> resultList = new ArrayList<>();
        String sqlQuery = "SELECT * FROM Playlist";
        try(
                Connection con = connectionFactory.getConnection();
                PreparedStatement preparedStatement = con.prepareStatement(sqlQuery);
                ResultSet resultSet = preparedStatement.executeQuery()
        ){
            while(resultSet.next()){
                    resultList.add(new Playlist(resultSet.getString("Owner"), resultSet.getString("Name"),
                            trackInPlaylistDAO.getTracksInPlaylist(resultSet.getString("Owner"), resultSet.getString("Name"))));
                }
            }
        catch (SQLException e){
            e.printStackTrace();
        }
        return resultList;
    }

    @Override
    public List<Playlist> findPlayistByOwner(String owner) {
        List<Playlist> resultList = new ArrayList<>();
        String sqlQuery = "SELECT * FROM Playlist where Owner=?";
        try(
                Connection con = connectionFactory.getConnection();
                PreparedStatement preparedStatement = con.prepareStatement(sqlQuery)
        ){
                preparedStatement.setString(1, owner);
                ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                resultList.add(new Playlist(resultSet.getString("Owner"), resultSet.getString("Name"),
                        trackInPlaylistDAO.getTracksInPlaylist(resultSet.getString("Owner"), resultSet.getString("Name"))));
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return resultList;

    }

    @Override
    public Playlist getPlaylistByNameAndOwner(String name, String owner) {
        String sqlQuery = "SELECT * FROM Playlist where Owner=? and Name=?";
        try(
                Connection con = connectionFactory.getConnection();
                PreparedStatement preparedStatement = con.prepareStatement(sqlQuery)
        ){
            preparedStatement.setString(1, owner);
            preparedStatement.setString(2, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return (new Playlist(resultSet.getString("Owner"), resultSet.getString("Name"),
                        trackInPlaylistDAO.getTracksInPlaylist(resultSet.getString("Owner"), resultSet.getString("Name"))));
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void savePlaylist(Playlist playlist) {
        String sqlQueryPlaylist = "INSERT INTO Playlist(Owner, Name) VALUES (?,?)";
        try(
                Connection con = connectionFactory.getConnection();
                PreparedStatement preparedStatementPlaylist = con.prepareStatement(sqlQueryPlaylist)
        ){
            preparedStatementPlaylist.setString(1, playlist.getOwner());
            preparedStatementPlaylist.setString(2, playlist.getName());
            preparedStatementPlaylist.execute();
            trackInPlaylistDAO.saveTracksToPlaylist(playlist.getOwner(), playlist.getName(), playlist.getTracks());
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void deletePlaylist(String name, String owner) {
        String sqlQuery = "DELETE FROM Playlist where Name=? AND Owner=?";
        try(
                Connection con = connectionFactory.getConnection();
                PreparedStatement preparedStatement = con.prepareStatement(sqlQuery)
                ){
            trackInPlaylistDAO.deleteAllTracksFromPlaylist(owner, name);

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, owner);
            preparedStatement.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void addTrack(Playlist playlist) {
        trackInPlaylistDAO.saveTracksToPlaylist(playlist.getOwner(), playlist.getName(), playlist.getTracks());
    }

    @Override
    public void deleteTrack(String owner, String name, List<Track> tracks) {
        trackInPlaylistDAO.deleteTracksFromPlaylist(owner, name, tracks);
    }


}
