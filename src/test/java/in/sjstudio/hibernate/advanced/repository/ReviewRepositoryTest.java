package in.sjstudio.hibernate.advanced.repository;

import static org.junit.Assert.assertEquals;
import javax.persistence.EntityManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import in.sjstudio.hibernate.advanced.entity.Course;
import in.sjstudio.hibernate.advanced.entity.Review;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReviewRepositoryTest {

  @Autowired
  private ReviewRepository reviewRepo;

  @Autowired
  private EntityManager em;

  @Test
  public void testFindById() {
    Review review = reviewRepo.findById(40001L);
    assertEquals("Very Good!", review.getDescription());
  }

  @Test
  public void testCourse() {
    Review review = reviewRepo.findById(40001L);
    assertEquals(10001L, review.getCourse().getId().longValue());
  }

  @Test
  public void testInsert() {
    Course course = em.find(Course.class, 10001L);
    Review review = new Review("Good tutorial");
    review.setCourse(course);
    reviewRepo.save(review);

    Review rv = reviewRepo.findById(review.getId());
    assertEquals(course.getId(), rv.getCourse().getId());

  }

}
