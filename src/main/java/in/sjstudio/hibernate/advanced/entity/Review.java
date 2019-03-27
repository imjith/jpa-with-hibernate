package in.sjstudio.hibernate.advanced.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Review {

  @Id
  @GeneratedValue
  private Long id;

  private String description;

  @ManyToOne(fetch = FetchType.EAGER)
  private Course course;

  public Review() {}


  public Review(String description) {
    this.description = description;
  }

  public String getDescription() {
    return description;
  }


  public void setDescription(String description) {
    this.description = description;
  }

  public Course getCourse() {
    return course;
  }


  public void setCourse(Course course) {
    this.course = course;
  }


  public Long getId() {
    return id;
  }

  @Override
  public String toString() {
    return String.format("id:%d, description:%s", id, description);
  }

}
