package in.sjstudio.hibernate.advanced.repository;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import in.sjstudio.hibernate.advanced.entity.Student;

@Repository
@Transactional
public class StudentRepository {

  @Autowired
  private EntityManager em;

  public Student findById(Long id) {
    return em.find(Student.class, id);
  }

  public List<Student> findAll() {
    TypedQuery<Student> query = em.createQuery("select c from Student c", Student.class);
    return query.getResultList();
  }


  public void save(Student student) {
    if (null == student.getId()) {
      em.persist(student);
    } else {
      em.merge(student);
    }
  }

  public void deleteById(Long id) {
    em.remove(findById(id));
  }

}
