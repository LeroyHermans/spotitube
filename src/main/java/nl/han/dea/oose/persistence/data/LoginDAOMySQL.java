package nl.han.dea.oose.persistence.data;

import nl.han.dea.oose.domain.User;
import nl.han.dea.oose.persistence.connection.IConnection;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Default
public class LoginDAOMySQL implements ILoginDAO {

    @Inject
    private IConnection connectionFactory;

    @Override
    public User checkUser(String username, String password) {
        String query = "SELECT * FROM Users WHERE username=? AND password=?";
        try(
                Connection con = connectionFactory.getConnection();
                PreparedStatement preparedStatement = con.prepareStatement(query)
                ){
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()){
                return new User(rs.getString("username"), rs.getString("password"));
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
