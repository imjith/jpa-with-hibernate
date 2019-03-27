package in.sjstudio.hibernate.advanced;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import in.sjstudio.hibernate.advanced.entity.Course;
import in.sjstudio.hibernate.advanced.entity.Student;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JPQLTest {

  @Autowired
  private EntityManager em;

  Logger logger = LoggerFactory.getLogger(this.getClass());

  @Test
  public void test_course_without_student() {
    TypedQuery<Course> query =
        em.createQuery("select c from Course c where c.students is empty", Course.class);
    List<Course> courses = query.getResultList();

    logger.info("Courses without student -> {} ", courses);
  }

  @Test
  public void test_course_with_atleast_two_students() {
    TypedQuery<Course> query =
        em.createQuery("select c from Course c where size(c.students) >=2", Course.class);
    List<Course> courses = query.getResultList();

    logger.info("Courses with atleast 2 students -> {} ", courses);
  }

  @Test
  public void test_course_order_by_numof_students() {
    TypedQuery<Course> query =
        em.createQuery("select c from Course c order by size(c.students) desc", Course.class);
    List<Course> courses = query.getResultList();

    logger.info("Courses, order by number of students -> {} ", courses);
  }

  @Test
  public void test_student_having_passport_with_certain_pattern() {
    TypedQuery<Student> query = em.createQuery(
        "select s from Student s where s.passport.number like 'L8434%'", Student.class);
    List<Student> students = query.getResultList();
    logger.info("Student having passport number start with L8434 -> {}", students);
  }

  @Test
  public void test_join() {
    Query query = em.createQuery("select c, s from Course c join c.students s");
    List<Object[]> result = query.getResultList();
    logger.info("number of rows : {}", result.size());
    for (Object[] item : result) {
      logger.info("{course -> {}}, {student -> {}}", item[0], item[1]);
    }
  }

  @Test
  public void test_left_join() {
    Query query = em.createQuery("select c, s from Course c left join c.students s");
    List<Object[]> result = query.getResultList();
    logger.info("number of rows : {}", result.size());
    for (Object[] item : result) {
      logger.info("{course -> {}}, {student -> {}}", item[0], item[1]);
    }
  }

  @Test
  public void test_cross_join() {
    Query query = em.createQuery("select c, s from Course c, Student s");
    List<Object[]> result = query.getResultList();
    logger.info("number of rows : {}", result.size());
    for (Object[] item : result) {
      logger.info("{course -> {}}, {student -> {}}", item[0], item[1]);
    }
  }

}
