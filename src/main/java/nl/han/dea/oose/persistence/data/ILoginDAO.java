package nl.han.dea.oose.persistence.data;

import nl.han.dea.oose.domain.User;

public interface ILoginDAO {
    User checkUser(String username, String password);
}
