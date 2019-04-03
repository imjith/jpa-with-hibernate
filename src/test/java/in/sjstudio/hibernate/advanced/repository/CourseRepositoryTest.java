package in.sjstudio.hibernate.advanced.repository;

import in.sjstudio.hibernate.advanced.entity.Course;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CourseRepositoryTest {

  @Autowired
  private CourseRepository courseRepo;

  Logger logger = LoggerFactory.getLogger(this.getClass());

  @Test
  public void testFindById() {
    Course course = courseRepo.findById(10001L);
    logger.info("Course with 10001L ->{}",course);
  }

  @Test
  public void testFindAll() {
    logger.info("All courses -> {}", courseRepo.findAll());
  }

  @Test
  @DirtiesContext
  public void testDeleteById() {

    //courseRepo.deleteById(10001L);
    //assertNull(courseRepo.findById(10001L));
  }

  @Test
  @DirtiesContext
  public void testInsert() {
    Course course1 = new Course("New course");
    courseRepo.save(course1);
    assertEquals("New course", courseRepo.findById(course1.getId()).getName());
  }

  @Test
  @DirtiesContext
  public void testUpdate() {
    Course course = courseRepo.findById(10001L);
    course.setName("course updated");
    courseRepo.save(course);
    assertEquals("course updated", courseRepo.findById(10001L).getName());
  }

  @Test
  @Transactional
  public void testCourseReviews() {
    Course course = courseRepo.findById(10001L);
    logger.info("reviews of course [{}] -> {}", course.getName(), course.getReviews());
  }
}
