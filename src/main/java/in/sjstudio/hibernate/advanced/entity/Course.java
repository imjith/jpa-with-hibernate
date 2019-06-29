package in.sjstudio.hibernate.advanced.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
@Cacheable
public class Course {

  @Id
  @GeneratedValue
  private Long id;

  @Column(name = "name", nullable = false)
  private String name;

  @OneToMany(mappedBy = "course")
  private List<Review> reviews = new ArrayList<>();

  @ManyToMany(mappedBy = "courses", fetch = FetchType.EAGER)
  private List<Student> students = new ArrayList<>();

  public Course() {}

  public Course(String name) {
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


  public List<Review> getReviews() {
    return reviews;
  }

  public void addReview(Review review) {
    this.reviews.add(review);
  }

  public void removeReview(Review review) {
    this.reviews.remove(review);
  }

  public List<Student> getStudents() {
    return students;
  }

  public void addStudent(Student student) {
    this.students.add(student);
  }

  @Override
  public String toString() {
    return String.format("id:%d, name:%s", id, name);
  }

}
