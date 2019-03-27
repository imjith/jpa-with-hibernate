package in.sjstudio.hibernate.advanced.repository;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import in.sjstudio.hibernate.advanced.entity.Passport;

@Repository
@Transactional
public class PassportRepository {

  @Autowired
  private EntityManager em;

  public Passport findById(Long id) {
    return em.find(Passport.class, id);
  }

  public List<Passport> findAll() {
    TypedQuery<Passport> query = em.createQuery("select c from Passport c", Passport.class);
    return query.getResultList();
  }


  public void save(Passport passport) {
    if (null == passport.getId()) {
      em.persist(passport);
    } else {
      em.merge(passport);
    }
  }

  public void deleteById(Long id) {
    em.remove(findById(id));
  }

}
