package in.sjstudio.hibernate.advanced.repository;

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
public class CourseDataRepositoryTest {

  @Autowired
  private CourseDataRepository repository;

  Logger logger = LoggerFactory.getLogger(this.getClass());

  @Test
  public void test_find_by_id() {
    logger.info("Course with id 10001 -> {}", repository.findById(10001L).get());
  }

  @Test
  public void test_find_all() {
    logger.info("All courses -> {}", repository.findAll());
  }

  @Test
  public void test_insert() {
    Course course = repository.save(new Course("New course!"));
    logger.info("New course added with id -> {}", course.getId());
  }

  @Test
  public void test_update() {
    Course course = repository.findById(10001L).get();
    course.setName("Updated course!");
    repository.save(course);
    logger.info("Course 10001 after updation -> {}", repository.findById(10001L).get());
  }
}
