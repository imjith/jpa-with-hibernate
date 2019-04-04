package in.sjstudio.hibernate.advanced.repository;

import javax.transaction.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

  @Test
  public void test_pagination() {
    PageRequest pageRequest = PageRequest.of(0, 2);
    Page<Course> firstPage = repository.findAll(pageRequest);
    logger.info("first page -> {}", firstPage.getContent());

    Pageable secondPageable = firstPage.nextPageable();
    logger.info("second page -> {}", repository.findAll(secondPageable).getContent());
  }

  @Test
  @Transactional
  public void testFindById_firstLevelCache() {
    Course course1 = repository.findById(10001L).get();
    logger.info("Course with 10001L ->{}", course1);
    Course course2 = repository.findById(10001L).get();
    logger.info("Course with id 10001L loaded again -> {}", course2);
  }

}
