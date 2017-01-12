package rock.domain;

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
	
	@Column(nullable=true)
	private String deletePerson;
	
	@Column(nullable=true)
	private String deleteTime;
	
	public Answer(){
		
	}
	
	


	public Answer(long id, Question question, User writer, String contents, String isDelete) {
		super();
		this.id = id;
		this.question = question;
		this.writer = writer;
		this.contents = contents;
		this.isDelete = isDelete;
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
		return "Answer [id=" + id + ", contents=" + contents + ", isDelete=" + isDelete + "]";
	}


	public void settingDBData(Question question, User writer){
		this.question = question;
		this.writer = writer;
		
	}
	public boolean userMatching(User user){
		return this.writer.userMatching(user);
	}
	
	public void delete(){
		this.isDelete = "1";
	}
	
}
