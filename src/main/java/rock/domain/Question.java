package rock.domain;


import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.ForeignKey;

@Entity
public class Question {

	@Id
	@GeneratedValue
	private long id;
	
	@ManyToOne
	@JoinColumn(foreignKey=@ForeignKey( name = "fk_question_to_writer"))
	private User writer;
	
	@Column(nullable=false)
	private String title;
	
	@Column(nullable=true)
	private String isDelete;
	
	@ManyToOne
	@JoinColumn(foreignKey=@ForeignKey( name = "fk_question_to_deletePersion"))
	private User deletePerson;
	
	@Column(nullable=true)
	private Date deleteTime;
	
	@Column(nullable=false)
	private String contents;

	@OneToMany(mappedBy="question")
	private List<Answer> ans;
	
	
	public Question(){
		
	}
	
	

	public Question(long id, User writer, String title, String isDelete, User deletePerson, Date deleteTime,
			String contents, List<Answer> ans) {
		super();
		this.id = id;
		this.writer = writer;
		this.title = title;
		this.isDelete = isDelete;
		this.deletePerson = deletePerson;
		this.deleteTime = deleteTime;
		this.contents = contents;
		this.ans = ans;
	}



	public void setId(long id) {
		this.id = id;
	}

	public void setWriter(User writer) {
		this.writer = writer;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}
	
	public void setAns(List<Answer> ans) {
		this.ans = ans;
	}
	public List<Answer> getAns() {
		return ans;
	}
	
	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}


	public void setDeletePerson(User deletePerson) {
		this.deletePerson = deletePerson;
	}

	

	@Override
	public String toString() {
		return "Question [id=" + id + ", writer=" + writer + ", title=" + title + ", isDelete=" + isDelete
				+ ", deletePerson=" + deletePerson + ", deleteTime=" + deleteTime + ", contents=" + contents + ", ans="
				+ ans + "]";
	}


	public void dbSetting(User writer) {
		this.isDelete="0";
		this.writer = writer;
		
	}
	public boolean userMatching(User user){
		return this.writer.userMatching(user) && ansswerChecking(user);
	}

	public int getAnswerSize() {
		return this.ans.size();
	}

	public boolean ansswerChecking(User user) {
		boolean result = false;
		if(ans == null){
			return !result;
		}
		for (Answer answer : ans) {
			result = answer.userMatching(user);
		}
		return result;
	}


	public Question delete(User user, Date date) {
		this.isDelete = "1";
		this.deletePerson = user;
		this.deleteTime = date;
		for(Answer answer : ans){
			answer.delete(user, date);
		}
		return this;
	}

}
