package nl.han.dea.oose.service;

import nl.han.dea.oose.domain.User;
import nl.han.dea.oose.persistence.data.ILoginDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class LoginServiceTest {

    @Mock
    ILoginDAO loginDAO;

    @InjectMocks
    private LoginService sut;

    @Test
    public void checkUserTest(){
        User user = new User("Leroy", "Leroy");
        sut.login(user);
        verify(loginDAO).checkUser(user.getUsername(), user.getPassword());
    }
}