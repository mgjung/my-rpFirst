package rock.web;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import rock.domain.AnswerRepository;
import rock.domain.Question;
import rock.domain.QuestionRepository;
import rock.domain.User;
import rock.domain.Answer;

@Controller
@RequestMapping("/qna")
public class QnaController {

	@Autowired
	private QuestionRepository qnaRepository;
	
	@Autowired
	private AnswerRepository ansRepository;
	
	@PostMapping("")
	public String create(HttpSession session, Question qna){
		System.out.println("QnA : "+qna);
		User writer = (User)session.getAttribute("sessionedUser"); 
		qna.dbSetting(writer);
		qnaRepository.save(qna);
		
		
		return "redirect:/qna";	
	}
	
	@GetMapping("")
	public String list(Model model){
		
		model.addAttribute("qnas", qnaRepository.findAll());
		
		return "index"; 
	}
	@GetMapping("/form")
	public String form(HttpSession session,Model model){
		System.out.println("Here /qna/form ~~~");
		String url = "redirect:/users/login";;
		User user = getSessionUser(session);
		if (user != null)  {
			model.addAttribute("user",user);
			url = "qna/form";
		} 
		return url;
		
	}
	@GetMapping("{id}")
	public String show(@PathVariable Long id,Model model){
		Question qna = qnaRepository.findOne(id);
		List<Answer> answers = ansRepository.findByQuestion(qna);
		
		model.addAttribute("qna", qna);
		model.addAttribute("answers", answers);
		
		return "qna/show"; 
	}
	
	@DeleteMapping("{id}")
	public String delete(HttpSession session, @PathVariable Long id){
		User user = getSessionUser(session);
		Question qna = qnaRepository.findOne(id);
		
		if(qna.userMatching(user)){
			
			qnaRepository.delete(id);
		}
		
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
