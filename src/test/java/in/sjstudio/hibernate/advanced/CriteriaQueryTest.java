package in.sjstudio.hibernate.advanced;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import in.sjstudio.hibernate.advanced.entity.Course;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CriteriaQueryTest {

  @Autowired
  private EntityManager em;

  private Logger logger = LoggerFactory.getLogger(this.getClass());

  @Test
  public void test_basics() {

    CriteriaBuilder cb = em.getCriteriaBuilder();
    CriteriaQuery<Course> cq = cb.createQuery(Course.class);

    Root<Course> rootQuery = cq.from(Course.class);

    TypedQuery<Course> query = em.createQuery(cq.select(rootQuery));

    List<Course> courses = query.getResultList();


    logger.info("The courses -> {}", courses);
  }

  @Test
  public void all_courses_having_100_steps() {
    CriteriaBuilder cb = em.getCriteriaBuilder();
    CriteriaQuery<Course> cq = cb.createQuery(Course.class);

    Root<Course> rootQuery = cq.from(Course.class);

    // Predicate like = cb.like(rootQuery.get("name"), "%100 steps%");
    // cq.where(like);

    cq.where(cb.like(rootQuery.get("name"), "%100 steps%"));
    TypedQuery<Course> typedQuery = em.createQuery(cq.select(rootQuery));
    logger.info("Courses like 100 steps -> {}", typedQuery.getResultList());
  }

  @Test
  public void all_courses_without_students() {
    // select c from Course c where c.students is empty
    CriteriaBuilder cb = em.getCriteriaBuilder();
    CriteriaQuery<Course> cq = cb.createQuery(Course.class);

    Root<Course> rootQuery = cq.from(Course.class);
    cq.where(cb.isEmpty(rootQuery.get("students")));

    TypedQuery<Course> typedQuery = em.createQuery(cq.select(rootQuery));

    logger.info("Courses without students -> {}", typedQuery.getResultList());

  }

  @Test
  public void all_courses_with_minimum_2_courses() {
    // select c from Course c where size(c.students)>=2

    CriteriaBuilder cb = em.getCriteriaBuilder();
    CriteriaQuery<Course> cq = cb.createQuery(Course.class);

    Root<Course> rootQuery = cq.from(Course.class);

    cq.where(cb.greaterThanOrEqualTo(cb.size(rootQuery.get("students")), 2));
    TypedQuery<Course> typedQuery = em.createQuery(cq.select(rootQuery));

    logger.info("Courses with minimum 2 students -> {}", typedQuery.getResultList());
  }

  @Test
  public void courses_ordered_by_num_of_students() {

    CriteriaBuilder cb = em.getCriteriaBuilder();
    CriteriaQuery<Course> cq = cb.createQuery(Course.class);

    Root<Course> rootQuery = cq.from(Course.class);

    //cq.orderBy(cb.asc(cb.size(rootQuery.get("students"))));
    
    Expression<Integer> sizeOfStudents = cb.size(rootQuery.get("students"));
    Order descOrder = cb.desc(sizeOfStudents);
    cq.orderBy(descOrder);

    TypedQuery<Course> typedQuery = em.createQuery(cq.select(rootQuery));
    logger.info("Course ordered by the number of students -> {}", typedQuery.getResultList());
  }
  
  @Test
  public void course_join() {
    CriteriaBuilder cb = em.getCriteriaBuilder();
    CriteriaQuery<Course> cq = cb.createQuery(Course.class);
    
    Root<Course> from = cq.from(Course.class);
    from.join("students");
    
    TypedQuery<Course> query = em.createQuery(cq.select(from));
    
    logger.info("Courses after join -> {}", query.getResultList());
  }
}
