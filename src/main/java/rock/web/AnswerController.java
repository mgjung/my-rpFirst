package rock.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import rock.domain.Answer;
import rock.domain.Question;
import rock.domain.User;
import rock.service.AnswerService;
import rock.service.QuestionService;

@Controller
@RequestMapping("/question/{questionid}/answer")
public class AnswerController {

	@Autowired
	private AnswerService answerService;
	
	@Autowired
	private QuestionService questionService;
	
	@PostMapping("")
	public String create(HttpSession session, @PathVariable Long questionid, Answer ans){
		Question qus = questionService.findOne(questionid);
		String url = "redirect:/users/login";
		User user = getSessionUser(session);
		if (user != null)  {
			ans.settingDBData(qus, user);
			answerService.save(ans);
			url = "redirect:/qna/"+questionid.toString();;
		} 
		
		return url;	
	}
	
	@DeleteMapping("{id}")
	public String delete(HttpSession session, @PathVariable Long questionid, @PathVariable Long id){
		User user = getSessionUser(session);
		Answer ans = answerService.findOne(id);
		
		if(ans.userMatching(user)){
			answerService.delete(id);
		}
		
		return "redirect:/qna/"+questionid.toString();
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
