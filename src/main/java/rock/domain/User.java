package rock.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {
	@Id
	@GeneratedValue
	private long id;
	
	@Column(length = 20, nullable=false, unique=true)
	private String userId;
	
	@Column(nullable=false)
	private String password;
	
	@Column(nullable=false)
	private String name;
	
	private String email;
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", name=" + name + ", email=" + email + "]";
	}
	
	public void update(User updateUser){
		if(!passMatching(updateUser)){
			throw new IllegalStateException("실패");
		}
	    this.name = updateUser.name;
	    this.email = updateUser.email;
	}
	
	public boolean passMatching(User updateUser){
		return this.password.equals(updateUser.password);
	}
	public boolean passMatching(String password){
		return this.password.equals(password);
	}
	public boolean userMatching(User user){
		return this.userId == user.userId;
	}
	
}
