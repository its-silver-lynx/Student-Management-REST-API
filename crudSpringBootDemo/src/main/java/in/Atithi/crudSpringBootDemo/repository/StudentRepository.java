package in.Atithi.crudSpringBootDemo.repository;

import in.Atithi.crudSpringBootDemo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    public Optional<Student>  findByIdAndDeletedIsFalse(Long id);
    public List<Student> findByDeletedIsFalse();
}
