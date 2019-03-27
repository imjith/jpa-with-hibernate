package in.sjstudio.hibernate.advanced.inheritance.repository;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import in.sjstudio.hibernate.advanced.inheritance.entity.Employee;
import in.sjstudio.hibernate.advanced.inheritance.entity.FullTimeEmployee;
import in.sjstudio.hibernate.advanced.inheritance.entity.PartTimeEmployee;

@Repository
@Transactional
public class EmployeeRepository {

  @Autowired
  private EntityManager em;

  public Employee findById(Long id) {
    return em.find(Employee.class, id);
  }

  public List<Employee> findAll() {
    TypedQuery<Employee> query = em.createQuery("select e from Employee e", Employee.class);
    return query.getResultList();
  }

  public List<FullTimeEmployee> findAllFullTimeEmployee() {
    return em.createQuery("select e from FullTimeEmployee e", FullTimeEmployee.class)
        .getResultList();
  }

  public List<PartTimeEmployee> findAllPartTimeEmployee() {
    return em.createQuery("select e from PartTimeEmployee e", PartTimeEmployee.class)
        .getResultList();
  }

  public void save(Employee employee) {
    if (null == employee.getId()) {
      em.persist(employee);
    } else {
      em.merge(employee);
    }
  }

  public void deleteById(Long id) {
    em.remove(findById(id));
  }

}
