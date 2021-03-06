package rock.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;

import rock.domain.Question;
import rock.domain.QuestionRepository;
//import rock.domain.QuestionSpecification;
import rock.domain.User;

@Service
public class QuestionService {

	@Autowired
	QuestionRepository questionReppository;
	
	public void save(Question qna, User writer) {
		qna.dbSetting(writer);
		questionReppository.save(qna);
		
	}

	public List<Question> findAll() {
		List<Question> questions = questionReppository.findByIsDelete("0");
		for(Question question : questions){
			question.setAnswerSize();
		}
		return  questions;
	}

	public Question findOne(Long id) {
		//Specifications<Question> specifications = Specifications.where(QuestionSpecification.questionEq(id));
		Question question = questionReppository.findByIdAndAnsIsDeleteIsNullOrAnsIsDeleteNot(id, "1");
		question.setAnswerSize();
		return question;
	}

	public void delete(User user, Long id) {
		Question question = findOne(id);
		
		if(question.userMatching(user)){
			Date date = new Date();
			questionReppository.save(question.delete(user, date));
			
		}
		
	}
	
	
	

}
