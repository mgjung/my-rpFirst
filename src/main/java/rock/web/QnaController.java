package rock.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import rock.domain.Question;
import rock.domain.User;
import rock.service.QuestionService;


@Controller
@RequestMapping("/qna")
public class QnaController {

	@Autowired
	private QuestionService questionService;
	
	@PostMapping("")
	public String create(HttpSession session, Question qna){
		
		User writer = (User)session.getAttribute("sessionedUser"); 
		questionService.save(qna, writer);
		return "redirect:/qna";	
		
	}
	
	@GetMapping("")
	public String list(Model model){
		
		model.addAttribute("qnas", questionService.findAll());
		return "index"; 
	}
	
	@GetMapping("/form")
	public String form(HttpSession session,Model model){
		
		String url = "redirect:/users/login";
		User user = getSessionUser(session);
		if (user != null)  {
			model.addAttribute("user",user);
			url = "qna/form";
		} 
		return url;
		
	}
	
	@GetMapping("{id}")
	public String show(@PathVariable Long id,Model model){
		
		Question qna = questionService.findOne(id);
		model.addAttribute("qna", qna);
		model.addAttribute("count", qna.getAnswerSize());
		
		return "qna/show"; 
	}
	
	@DeleteMapping("{id}")
	public String delete(HttpSession session, @PathVariable Long id){
		User user = getSessionUser(session);
		questionService.delete(user, id);
		return "redirect:/";
	}
	
	public User getSessionUser(HttpSession session){
		
		User user = null;
		Object value = session.getAttribute("sessionedUser"); 
		if(value != null){
			user = (User)value;
		}
		
		return user;
	}
	
}
