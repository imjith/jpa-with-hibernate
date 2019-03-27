package in.sjstudio.hibernate.advanced.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

@Entity
@NamedQueries(value = {@NamedQuery(name = "Student.findAll", query = "Select s from Student s"),
    @NamedQuery(name = "Student.findById", query = "Select s from Student s where s.id=:id")})
public class Student {

  @Id
  @GeneratedValue
  private Long id;

  @Column(name = "name", nullable = false)
  private String name;

  @OneToOne(fetch = FetchType.LAZY)
  private Passport passport;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "student_course", joinColumns = @JoinColumn(name = "student_id"),
      inverseJoinColumns = @JoinColumn(name = "course_id"))
  private List<Course> courses = new ArrayList<>();

  public Student() {}

  public Student(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Passport getPassport() {
    return passport;
  }

  public void setPassport(Passport passport) {
    this.passport = passport;
  }

  public List<Course> getCourses() {
    return courses;
  }

  public void addCourse(Course course) {
    this.courses.add(course);
  }

  public Long getId() {
    return id;
  }

  @Override
  public String toString() {
    return String.format("id:%d, name:%s", id, name);
  }

}
