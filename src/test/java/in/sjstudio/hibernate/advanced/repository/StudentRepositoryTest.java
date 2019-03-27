package in.sjstudio.hibernate.advanced.repository;

import static org.junit.Assert.assertEquals;
import javax.transaction.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import in.sjstudio.hibernate.advanced.entity.Student;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentRepositoryTest {


  @Autowired
  private StudentRepository studentRepo;

  @Test
  @Transactional
  public void testFindById() {
    Student student = studentRepo.findById(20001L);
    assertEquals("Sreejith Sreekantan", student.getName());
    assertEquals("L8434899", student.getPassport().getnumber());
  }
}
