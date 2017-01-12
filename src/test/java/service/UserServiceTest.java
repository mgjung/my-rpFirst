package service;


import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import rock.domain.User;
import rock.domain.UserRepository;
import rock.service.UserService;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
	@Mock private UserRepository userRepository;
	
	@InjectMocks private UserService userService;
	
	@Test
	public void findOne() {
		User newUser = new User(1L, "test","passwd","name","test@test");
		when(userRepository.findByUserId("test")).thenReturn(newUser);
		User user = userService.findByUserId("test2");
		assertEquals(newUser, user);
	}
	
	@Test
	public void create() {
		User newUser = new User(1L, "test","passwd","name","test@test");
		userService.save(newUser);
		verify(userRepository).save(newUser);
		
	}

}
