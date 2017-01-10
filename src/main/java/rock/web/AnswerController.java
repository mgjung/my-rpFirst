package rock.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import rock.domain.Answer;
import rock.domain.AnswerRepository;
import rock.domain.Question;
import rock.domain.QuestionRepository;
import rock.domain.User;

@Controller
@RequestMapping("/answer")
public class AnswerController {

	@Autowired
	private AnswerRepository ansRepository;
	
	@Autowired
	private QuestionRepository qnaRepository;
	
	@PostMapping("")
	public String create(HttpSession session, Long id, Answer ans){
		Question qus = qnaRepository.findOne(id);
		User user = getSessionUser(session);
		ans.settingDBData(qus, user);
		
		ansRepository.save(ans);
		return "redirect:/qna/"+id.toString();	
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
