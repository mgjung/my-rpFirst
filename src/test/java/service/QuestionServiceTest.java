package service;



import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;


import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import rock.domain.Answer;
import rock.domain.Question;

import rock.domain.User;
import rock.service.QuestionService;



public class QuestionServiceTest {
	
	
	
	User user;
	User user2;
	Question qus;
	Question qus2;
	List<Answer> ans1= null;
	List<Answer> ans2 = null;
	@Before
	public void setup(){
		user = new User(1L, "test","passwd","name","test@test");
		user2 = new User(2L, "test2","passwd","name","test@test");
		
		ans1 = Arrays.asList(
				new Answer(1L, qus, user,"test","0",user, null),
				new Answer(2L, qus, user,"test","0",user, null));
		ans2 = Arrays.asList (
				new Answer(3L, qus2, user,"test","0",user, null),
				new Answer(4L, qus2, user2,"test","0",user2, null));
		
		
		
		qus = new Question(1L, user, "test","0",null,null,"test", ans1, 2);
		qus2 = new Question(2L, user, "test","0",null,null,"test", ans2, 2);
		
	}
	
	@Test
	public void user_체크() {
		assertTrue(user.userMatching(user));
	}
	
	@Test
	public void question의_user_확인(){
		assertTrue(qus.userMatching(user));
	}
	
	@Test
	public void answer의_user_체크성공(){
		assertTrue(qus.ansswerChecking(user));
	}
	
	@Test
	public void answer의_user_체크실패(){
		assertFalse(qus2.ansswerChecking(user));
		
	}
	
	@Test
	public void question_delete(){
		
		Date date = new Date();
		Question comQus = new Question(1L, user, "test","1",user,date,"test", ans1, 2);
		qus.delete(user, date);
		assertThat(qus.toString(), is(comQus.toString()));
	}
	
	@Test
	public void question_delete답변이_없을때(){
		
		Date date = new Date();
		Question qus = new Question(1L, user, "test","1",null,null,"test", null, 0);
		Question comQus = new Question(1L, user, "test","1",user,date,"test", null, 0);
		qus.delete(user, date);
		assertThat(qus.toString(), is(comQus.toString()));
	}
	
	@Test
	public void answer_delete(){
		Date date = new Date();
		Answer answer = new Answer(1L, qus, user,"test","0",null, null);
		Answer comAns = new Answer(1L, qus, user,"test","1",user,date);
		
		answer.delete(user, date);
		assertThat(answer.toString(), is(comAns.toString()));
	}
	
	@Test
	public void answer존재여부_확인() throws Exception {
		assertTrue(qus.isAnswer());
	}
	
	@Test
	public void setAnswersCount() throws Exception {
		Question qus = new Question(1L, user, "test","0",null,null,"test", null, 0);
		Question comQus = new Question(1L, user, "test","0",null,null,"test", ans1, 2);
		qus.setAns(ans1);
		qus.setAnswerSize();
		assertThat(qus.toString(), is (comQus.toString()));
	}
	
}
