package rock.domain;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Answer {

	@Id
	@GeneratedValue
	private long id;
	
	@ManyToOne
	@JoinColumn(foreignKey=@ForeignKey(name="fk_answer_to_question"))
	private Question question;
	
	@ManyToOne
	@JoinColumn(foreignKey=@ForeignKey( name = "fk_answer_to_writer"))
	private User writer;
	
	@Column(nullable=false)
	private String contents;
	
	@Column(nullable=true)
	private String isDelete;
	
	@ManyToOne
	@JoinColumn(foreignKey=@ForeignKey( name = "fk_answer_to_deletePersion"))
	private User deletePerson;
	
	@Column(nullable=true)
	private Date deleteTime;
	
	public Answer(){
		
	}
	
	public Answer(long id, Question question, User writer, String contents, String isDelete, User deletePerson,
			Date deleteTime) {
		super();
		this.id = id;
		this.question = question;
		this.writer = writer;
		this.contents = contents;
		this.isDelete = isDelete;
		this.deletePerson = deletePerson;
		this.deleteTime = deleteTime;
	}


	public void setId(long id) {
		this.id = id;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public void setWriter(User writer) {
		this.writer = writer;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	
	@Override
	public String toString() {
		return "Answer [id=" + id + ", question=" + question + ", writer=" + writer + ", contents=" + contents
				+ ", isDelete=" + isDelete + ", deletePerson=" + deletePerson + ", deleteTime=" + deleteTime + "]";
	}


	public void settingDBData(Question question, User writer){
		this.question = question;
		this.writer = writer;
		
	}
	public boolean userMatching(User user){
		return this.writer.userMatching(user);
	}
	
	public void delete(User user, Date date){
		this.isDelete = "1";
		this.deletePerson = user;
		this.deleteTime = date;
	}
	
}
