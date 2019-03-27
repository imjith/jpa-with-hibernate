package in.sjstudio.hibernate.advanced.inheritance.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

//@Entity
// Default is single_table @Inheritance(strategy=InheritanceType.SINGLE_TABLE)
// @DiscriminatorColumn(name = "EmployeeType")
// @Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
//@Inheritance(strategy = InheritanceType.JOINED)
@MappedSuperclass
public abstract class Employee {

  @Id
  @GeneratedValue
  protected Long id;

  protected String name;

  public Employee() {
    super();
  }

  public Employee(String name) {
    super();
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Long getId() {
    return id;
  }

}
