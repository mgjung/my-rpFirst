package rock.domain;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

//public interface UserRepository extends CrudRepository<User, Long> {
public  interface UserRepository extends JpaRepository<User,Long>{
	User findByUserId(String userId);
}

