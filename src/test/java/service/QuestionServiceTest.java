package service;



import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import rock.domain.Answer;
import rock.domain.Question;

import rock.domain.User;



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
				new Answer(1L, qus, user,"test","0"),
				new Answer(2L, qus, user,"test","0"));
		ans2 = Arrays.asList (
				new Answer(3L, qus2, user,"test","0"),
				new Answer(4L, qus2, user2,"test","0"));
		
		
		qus = new Question(1L, user, "test","0",null,"test", ans1);
		qus2 = new Question(2L, user, "test","0",null,"test", ans2);
		
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
		Question comQus = new Question(1L, user, "test","1",user,"test", ans1);
		qus.delete(user);
		assertThat(qus.toString(), is(comQus.toString()));
	}
	
	@Test
	public void answer_delete(){
		Answer answer = new Answer(1L, qus, user,"test","0");
		Answer comAns = new Answer(1L, qus, user,"test","1");
		answer.delete();
		assertThat(answer.toString(), is(comAns.toString()));
	}
	
	
	
	
}
