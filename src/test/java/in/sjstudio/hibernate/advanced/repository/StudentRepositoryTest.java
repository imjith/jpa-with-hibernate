package in.sjstudio.hibernate.advanced.repository;

import in.sjstudio.hibernate.advanced.entity.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentRepositoryTest {


  @Autowired
  private StudentRepository studentRepo;

  Logger logger = LoggerFactory.getLogger(this.getClass());

  @Test
  @Transactional
  public void testFindById() {
    Student student = studentRepo.findById(20001L);
    logger.info("Student with 20001L ->{}",student);
  }
}
