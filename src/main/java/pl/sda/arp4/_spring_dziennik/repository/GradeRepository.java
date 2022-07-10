package pl.sda.arp4._spring_dziennik.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.arp4._spring_dziennik.model.Grade;
import pl.sda.arp4._spring_dziennik.model.Student;

public interface GradeRepository extends JpaRepository<Grade, Long> {
}
