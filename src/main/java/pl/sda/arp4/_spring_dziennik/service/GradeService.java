package pl.sda.arp4._spring_dziennik.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.sda.arp4._spring_dziennik.repository.GradeRepository;
import pl.sda.arp4._spring_dziennik.repository.StudentRepository;

@Slf4j
@Service
@RequiredArgsConstructor

public class GradeService {

    private final GradeRepository gradeRepository;
}
