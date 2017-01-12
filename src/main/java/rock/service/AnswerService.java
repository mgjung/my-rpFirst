package rock.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rock.domain.Answer;
import rock.domain.AnswerRepository;
import rock.domain.Question;
import rock.domain.User;

@Service
public class AnswerService {

	
	@Autowired
	AnswerRepository answerRepository;

	@Autowired
	private QuestionService questionService;
	
	public void save(Answer ans, Long questionid, User user) {
		Question qus = questionService.findOne(questionid);
		ans.settingDBData(qus, user);
		answerRepository.save(ans);
		
	}

	public Answer findOne(Long id) {
		
		return answerRepository.findOne(id);
	}

	public void delete(Long id, User user) {
		Answer ans = findOne(id);
		if(ans.userMatching(user)){
			answerRepository.delete(id);
		}
		
	}
	
	
}
