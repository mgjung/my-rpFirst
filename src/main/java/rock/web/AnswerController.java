package rock.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import rock.domain.Answer;
import rock.domain.AnswerRepository;
import rock.domain.Question;
import rock.domain.QuestionRepository;
import rock.domain.User;

@Controller
@RequestMapping("/question/{questionid}/answer")
public class AnswerController {

	@Autowired
	private AnswerRepository ansRepository;
	
	@Autowired
	private QuestionRepository qnaRepository;
	
	@PostMapping("")
	public String create(HttpSession session, @PathVariable Long questionid, Answer ans){
		Question qus = qnaRepository.findOne(questionid);
		User user = getSessionUser(session);
		ans.settingDBData(qus, user);
		ansRepository.save(ans);
		return "redirect:/qna/"+questionid.toString();	
	}
	
	@DeleteMapping("{id}")
	public String delete(HttpSession session, @PathVariable Long questionid, @PathVariable Long id){
		User user = getSessionUser(session);
		Answer ans = ansRepository.findOne(id);
		
		if(ans.userMatching(user)){
			ansRepository.delete(id);
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
