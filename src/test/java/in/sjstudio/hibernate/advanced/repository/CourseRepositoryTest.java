package in.sjstudio.hibernate.advanced.repository;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import java.util.List;
import javax.transaction.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import in.sjstudio.hibernate.advanced.entity.Course;
import in.sjstudio.hibernate.advanced.entity.Review;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CourseRepositoryTest {

  @Autowired
  private CourseRepository courseRepo;

  @Test
  public void testFindById() {
    Course course = courseRepo.findById(10001L);
    assertEquals("Spring Beginner to Guru!", course.getName());
  }

  @Test
  public void testFindAll() {
    List<Course> courses = courseRepo.findAll();
    assertEquals(3, courses.size());
    String[] expected = new String[] {"Spring Beginner to Guru!", "Spring Beginner to Expert!",
        "Hibernate in 100 steps!"};
    String[] actuals = courses.stream().map(course -> course.getName()).toArray(String[]::new);
    assertArrayEquals(expected, actuals);
  }

  @Test
  @DirtiesContext
  public void testDeleteById() {
    courseRepo.deleteById(10001L);
    assertNull(courseRepo.findById(10001L));
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
    List<Review> reviews = course.getReviews();

    String[] expected = new String[] {"Very Good!", "Very nice!"};
    String[] actuals =
        reviews.stream().map(review -> review.getDescription()).toArray(String[]::new);
    assertArrayEquals(expected, actuals);
  }
}
