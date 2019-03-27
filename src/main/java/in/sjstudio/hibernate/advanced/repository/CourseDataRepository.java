package in.sjstudio.hibernate.advanced.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import in.sjstudio.hibernate.advanced.entity.Course;

@RepositoryRestResource(path="courses")
public interface CourseDataRepository extends JpaRepository<Course, Long>{
  
}
