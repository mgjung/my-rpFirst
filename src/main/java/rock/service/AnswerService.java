package rock.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rock.domain.Answer;
import rock.domain.AnswerRepository;

@Service
public class AnswerService {

	
	@Autowired
	AnswerRepository answerRepository;

	public void save(Answer ans) {
		answerRepository.save(ans);
		
	}

	public Answer findOne(Long id) {
		
		return answerRepository.findOne(id);
	}

	public void delete(Long id) {
		answerRepository.delete(id);
		
	}
	
	
}
