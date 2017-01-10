package rock.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import rock.domain.User;
import rock.domain.UserRepository;

@Controller
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	
	@PostMapping("")
	public String create(User user){
		System.out.println("User : "+user);
		userRepository.save(user);
		
		return "redirect:/users";	
	}
	
	@GetMapping("")
	public String list(Model model){
		
		model.addAttribute("users", userRepository.findAll());
		return "user/list";
		
	}
	@PostMapping("/login")
	public  String login(String  userId, String password, HttpSession session) {
		
		String url ="user/login_failed";
		System.out.println("Here /users/login ~~~");
		User user = userRepository.findByUserId(userId);
		
		if(user != null && user.passMatching(password) ){
			url="redirect:/";
			session.setAttribute("sessionedUser",  user);
		}
		
		return url;
	}
	
	@GetMapping("/login")
	public String login(HttpSession session){
		
		return "user/login"; 
	}
	
	@GetMapping("/form")
	public String form(){
		
		return "user/form"; 
	}
	
	@GetMapping("{id}/updateForm")
	public String updateForm(@PathVariable Long id, Model model){
		User user = userRepository.findOne(id);
		model.addAttribute("user", user);
		return "user/updateForm"; 
	}
	
	@PutMapping("{id}/update")
	public String update(@PathVariable Long id, User user){
		User dbUser = userRepository.findOne(id);
		
		dbUser.update(user);
		userRepository.save(user);
		
		
		return "redirect:/users";	
	}
	
	
	
	
	
}
