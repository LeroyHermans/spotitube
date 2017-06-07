package nl.han.dea.oose.persistence.data;

import nl.han.dea.oose.domain.Song;
import nl.han.dea.oose.domain.Track;
import nl.han.dea.oose.domain.Video;
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
public class TrackDAOMySQL implements ITrackDAO {

    @Inject
    private IConnection connectionFactory;

    @Override
    public List<Track> findAllTracks() {
        List<Track> resultList = new ArrayList<>();
        String sqlQuery = "SELECT * FROM Track";
        try(
                Connection con = connectionFactory.getConnection();
                PreparedStatement preparedStatement = con.prepareStatement(sqlQuery);
                ResultSet resultSet = preparedStatement.executeQuery();
                ){
            while(resultSet.next()){
                resultList.add(getTrack(resultSet));
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return resultList;
    }

    @Override
    public Track findByTitle(String title) {
        String sqlQuery = "SELECT * FROM Track where Title=?";
        try(
                Connection con = connectionFactory.getConnection();
                PreparedStatement preparedStatement = con.prepareStatement(sqlQuery)
        ){
            preparedStatement.setString(1, title);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
               return getTrack(resultSet);
            }
            resultSet.close();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return null;

    }

    public Track getTrack(ResultSet rs){
        Track result = null;

        try {
            if("Song".equals(rs.getString("Type"))){
                result = new Song(rs.getString("Preformer"), rs.getString("Title"),
                        rs.getString("url"), rs.getFloat("Duration"),rs.getString("Type") , rs.getString("Album"));
            }else if("Video".equals(rs.getString("Type"))){
                result = new Video(rs.getString("Preformer"), rs.getString("Title"),
                        rs.getString("url"), rs.getFloat("Duration"),rs.getString("Type"), rs.getInt("PlayCount"),
                        rs.getString("PublicationDate").toString(), rs.getString("Description"));
        }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
