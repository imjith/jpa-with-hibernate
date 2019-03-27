package in.sjstudio.hibernate.advanced;

import java.math.BigDecimal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import in.sjstudio.hibernate.advanced.entity.Course;
import in.sjstudio.hibernate.advanced.inheritance.entity.FullTimeEmployee;
import in.sjstudio.hibernate.advanced.inheritance.entity.PartTimeEmployee;
import in.sjstudio.hibernate.advanced.inheritance.repository.EmployeeRepository;
import in.sjstudio.hibernate.advanced.repository.CourseRepository;

@SpringBootApplication
public class JpaWithHibernateAdvancedApplication implements CommandLineRunner {

  @Autowired
  private CourseRepository courseRepo;

  @Autowired
  private EmployeeRepository employeeRepo;



  Logger logger = LoggerFactory.getLogger(this.getClass());

  public static void main(String[] args) {
    SpringApplication.run(JpaWithHibernateAdvancedApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    // testHibernateSequenceBehaviour();
    // testInheritance();
  }

  private void testHibernateSequenceBehaviour() {
    new Thread(() -> {
      try {
        courseRepo.save(new Course("course1"), false);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }).start();
    new Thread(() -> {
      try {
        courseRepo.save(new Course("course2"), true);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }).start();
    new Thread(() -> {
      try {
        courseRepo.save(new Course("course3"), true);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }).start();

    new Thread(() -> {
      try {
        courseRepo.save(new Course("course3"), false);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }).start();
  }

  private void testInheritance() {
    employeeRepo.save(new FullTimeEmployee("Sreejith Sreekantan", BigDecimal.valueOf(7500)));
    employeeRepo.save(new PartTimeEmployee("Sathya", BigDecimal.valueOf(100)));

    // List<Employee> employees = employeeRepo.findAll();
    // logger.info("All employees -> {}", employees);

    logger.info("All the FullTimeEmployees -> {}", employeeRepo.findAllFullTimeEmployee());
    logger.info("All the PartTimeEmployees -> {}", employeeRepo.findAllPartTimeEmployee());

  }

}
