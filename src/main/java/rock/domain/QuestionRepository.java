package rock.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface QuestionRepository extends JpaRepository<Question,Long>, JpaSpecificationExecutor<Question> {
	List<Question> findByIsDelete(String isDelete);
}
