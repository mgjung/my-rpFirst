package rock.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import rock.domain.Qna;
import rock.domain.QnaRepository;
import rock.domain.User;

@Controller
@RequestMapping("/qna")
public class QnaController {

	@Autowired
	private QnaRepository qnaRepository;
	
	@PostMapping("")
	public String create(Qna qna){
		System.out.println("QnA : "+qna);
		
		qnaRepository.save(qna);
		return "redirect:/qna";	
	}
	
	@GetMapping("")
	public String list(Model model){
		
		model.addAttribute("qnas", qnaRepository.findAll());
		
		return "redirect:/"; 
	}
	
}
