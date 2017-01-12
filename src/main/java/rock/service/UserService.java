package rock.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rock.domain.User;
import rock.domain.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public void save(User user) {
		
		userRepository.save(user);
		
	}

	public List<User> findAll() {
		
		return userRepository.findAll();
	}

	public User findByUserId(String userId) {
		
		return userRepository.findByUserId(userId);
	}

	public User findOne(Long id) {
		
		return userRepository.findOne(id);
	}

	public void update(User user, Long id) {
		save(findOne(id).update(user));
	}
	
	public User login(String userId, String password){
		User user = findByUserId(userId);
		if(user != null && user.passMatching(password)){
			return user;
		}
		return null;
	}
	
	
}
