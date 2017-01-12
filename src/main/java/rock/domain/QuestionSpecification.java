package rock.domain;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;


public class QuestionSpecification {

	
	public static Specification<Question> questionEq(final String isDelete){
		return new Specification<Question>(){
			public Predicate toPredicate(Root<Question> root, CriteriaQuery<?> query, CriteriaBuilder cb){
				return cb.equal(root.get("isDelete"), isDelete);
			}
		};
	}
}
