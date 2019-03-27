package in.sjstudio.hibernate.advanced;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import java.util.List;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import in.sjstudio.hibernate.advanced.entity.Course;
import in.sjstudio.hibernate.advanced.entity.Student;
import in.sjstudio.hibernate.advanced.repository.CourseRepository;
import in.sjstudio.hibernate.advanced.repository.StudentRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ManyToManyTest {

  @Autowired
  private StudentRepository studentRepo;

  @Autowired
  private CourseRepository courseRepo;

  @Autowired
  private EntityManager em;

  @Test
  @Transactional
  public void testStudent() {
    Student student = studentRepo.findById(20001L);
    List<Course> courses = student.getCourses();
    assertEquals(1, courses.size());
    assertEquals("Spring Beginner to Guru!", courses.get(0).getName());
  }

  @Test
  @Transactional
  public void testCourses() {
    Course course = courseRepo.findById(10001L);
    List<Student> students = course.getStudents();
    String[] expected = new String[] {"Sreejith Sreekantan", "Anuraj Radhakrishnan"};
    String[] actuals = students.stream().map(student -> student.getName()).toArray(String[]::new);
    assertArrayEquals(expected, actuals);
  }

  @Test
  @DirtiesContext
  @Transactional
  public void testInsert() {
    Student student = new Student("First Last");
    Course course = new Course("Demo course");
    em.persist(student);
    System.out.println(student.getId());
    em.persist(course);

    student.addCourse(course);
    em.persist(student);

    assertEquals(course.getName(),
        studentRepo.findById(student.getId()).getCourses().get(0).getName());
  }

}
