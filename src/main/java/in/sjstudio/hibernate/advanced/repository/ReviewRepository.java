package in.sjstudio.hibernate.advanced.repository;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import in.sjstudio.hibernate.advanced.entity.Review;

@Repository
@Transactional
public class ReviewRepository {

  @Autowired
  private EntityManager em;

  public Review findById(Long id) {
    return em.find(Review.class, id);
  }

  public List<Review> findAll() {
    TypedQuery<Review> query = em.createQuery("select r from Review r", Review.class);
    return query.getResultList();
  }


  public void save(Review review) {
    if (null == review.getId()) {
      em.persist(review);
    } else {
      em.merge(review);
    }
  }

  public void deleteById(Long id) {
    em.remove(findById(id));
  }

}
