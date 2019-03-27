package in.sjstudio.hibernate.advanced.repository;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import in.sjstudio.hibernate.advanced.entity.Course;

@Repository
@Transactional
public class CourseRepository {

  @Autowired
  private EntityManager em;

  public Course findById(Long id) {
    return em.find(Course.class, id);
  }

  public List<Course> findAll() {
    TypedQuery<Course> query = em.createQuery("select c from Course c", Course.class);
    return query.getResultList();
  }


  public void save(Course course) {
    if (null == course.getId()) {
      em.persist(course);
    } else {
      em.merge(course);
    }
  }

  public void deleteById(Long id) {
    em.remove(findById(id));
  }

  public void save(Course course, boolean throwError) {
    em.persist(course);
    if (throwError) {
      em.clear();
      throw new RuntimeException();
    }
  }

}
