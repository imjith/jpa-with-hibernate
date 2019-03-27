package in.sjstudio.hibernate.advanced.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;
import in.sjstudio.hibernate.advanced.entity.Student;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentDataRepositoryTest {

  @Autowired
  private StudentDataRepository repository;

  Logger logger = LoggerFactory.getLogger(this.getClass());

  @Test
  public void test_find_by_id() {
    logger.info("Student with id 20001 -> {}", repository.findById(20001L).get());
  }

  @Test
  public void test_find_all() {
    logger.info("All Students -> {}", repository.findAll());
  }

  @Test
  public void test_insert() {
    Student student = repository.save(new Student("new student"));
    logger.info("New student added with id -> {}", student.getId());
  }

  @Test
  public void test_update() {
    Student student = repository.findById(20001L).get();
    student.setName("Updated student name!");
    repository.save(student);
    logger.info("Student 20001 after updation -> {}", repository.findById(20001L).get());
  }

  @Test
  public void find_by_name_and_passport() {
    logger.info("Student with name:Sreejith Sreekantan and passport: L8434899 -> {}",
        repository.findByNameAndPassport_number("Sreejith Sreekantan", "L8434899"));
  }
  
  @Test
  public void find_all_by_query() {
    logger.info("All students by query -> {}",repository.findAllByQuery());
  }
  
  @Test
  public void find_all_by_native_query() {
    logger.info("All students by native query -> {}",repository.findAllByNativeQuery());
  }
  
  @Test
  public void find_all_by_named_query() {
    logger.info("All students by named query -> {}",repository.findAllByNamedQuery());
  }
  
  @Test
  public void find_by_id_named_query() {
    logger.info("Student with id 20001 named query -> {}",repository.findByIdNamedQuery(20001L));
  }
  
  @Test
  public void count_by_name() {
    logger.info("students with name Sreejith Sreekantan -> {}", repository.countByName("Sreejith Sreekantan"));
  }
  
  @Test
  public void test_sort() {
    Sort sort = new Sort(Sort.Direction.ASC, "name");
    logger.info("Students in order of name -> {}", repository.findAll(sort));
  }
}
