package pl.sda.arp4._spring_dziennik.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sda.arp4._spring_dziennik.model.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
}
