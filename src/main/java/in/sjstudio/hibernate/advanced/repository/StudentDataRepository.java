package in.sjstudio.hibernate.advanced.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import in.sjstudio.hibernate.advanced.entity.Student;

public interface StudentDataRepository extends JpaRepository<Student, Long> {

  Student findByNameAndPassport_number(String name, String passport);
  
  int countByName(String name);
  
  @Query("Select s from Student s")
  List<Student> findAllByQuery();
  
  @Query(value="Select * from STUDENT", nativeQuery = true)
  List<Student> findAllByNativeQuery();
  
  @Query(name="Student.findAll")
  List<Student> findAllByNamedQuery();
  
  @Query(name="Student.findById")
  Student findByIdNamedQuery(Long id);
  

}
