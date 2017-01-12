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
import rock.service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	
	@PostMapping("")
	public String create(User user){
		userService.save(user);
		return "redirect:/users";	
	}
	
	@GetMapping("")
	public String list(Model model){
		model.addAttribute("users", userService.findAll());
		return "user/list";
	}
	
	@PostMapping("/login")
	public  String login(String  userId, String password, HttpSession session) {
		User user = userService.login(userId, password);
		session.setAttribute("sessionedUser",  user);
		return userIdentify(user);
	}

	private String userIdentify(User user) {
		String url ="user/login_failed";
		if(user != null){
			url="redirect:/";
		}
		return url;
	}
	
	@GetMapping("/login")
	public String login(){
		
		return "user/login"; 
	}
	
	@GetMapping("/form")
	public String form(){
		
		return "user/form"; 
	}
	
	@GetMapping("{id}/updateForm")
	public String updateForm(@PathVariable Long id, Model model){
		User user = userService.findOne(id);
		model.addAttribute("user", user);
		return "user/updateForm"; 
	}
	
	@PutMapping("{id}/update")
	public String update(@PathVariable Long id, User user){
		userService.update(user, id);
		return "redirect:/users";	
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session){
		session.setAttribute("sessionedUser", null);
		return "redirect:/";
	}
}
