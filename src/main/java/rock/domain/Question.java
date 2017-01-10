package rock.domain;

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
	
	@Column(nullable=false)
	private String contents;

	@OneToMany(mappedBy="question", orphanRemoval=true)
	private List<Answer> ans;
	
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
	
	
	@Override
	public String toString() {
		return "Qna [id=" + id + ", writer=" + writer + ", title=" + title + ", contents=" + contents + "]";
	}

	public void dbSetting(User writer) {
		this.writer = writer;
		
	}
	public boolean userMatching(User user){
		return this.writer.userMatching(user);
	}
}
