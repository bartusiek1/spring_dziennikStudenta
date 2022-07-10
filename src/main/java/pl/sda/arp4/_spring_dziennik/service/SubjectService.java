package pl.sda.arp4._spring_dziennik.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.sda.arp4._spring_dziennik.exception.StudentHasGradesException;
import pl.sda.arp4._spring_dziennik.exception.SubjectHasGradesException;
import pl.sda.arp4._spring_dziennik.model.Grade;
import pl.sda.arp4._spring_dziennik.model.Student;
import pl.sda.arp4._spring_dziennik.model.Subject;
import pl.sda.arp4._spring_dziennik.model.dto.AddSubjectRequest;
import pl.sda.arp4._spring_dziennik.model.dto.StudentDTO;
import pl.sda.arp4._spring_dziennik.model.dto.SubjectDTO;
import pl.sda.arp4._spring_dziennik.repository.StudentRepository;
import pl.sda.arp4._spring_dziennik.repository.SubjectRepository;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor

public class SubjectService {

    private final SubjectRepository subjectRepository;

    public void create(AddSubjectRequest request) {
        Subject subject = mapAddSubjectRequestToSubject(request);

        List<Subject> allsubjects = subjectRepository.findAll();
        String nazwaNowegoPrzedmiotuBezSpacji = subject.getSubjectName().replaceAll(" ","");
        for(Subject przedmiot : allsubjects) {
            String nazwaPrzedmiotuBezSpacji = przedmiot.getSubjectName().replaceAll(" ", "");

            if (nazwaPrzedmiotuBezSpacji.equalsIgnoreCase(nazwaNowegoPrzedmiotuBezSpacji)) {
                return;
            }
        }
        subjectRepository.save(subject);
    }

    private Subject mapAddSubjectRequestToSubject(AddSubjectRequest request) {
        return new Subject(
                request.getNameOfSubject());
    }

    public List<SubjectDTO> findAll() {
        List<Subject> subjectList = subjectRepository.findAll();

        List<SubjectDTO> subjects = new ArrayList<>();
        for (Subject subject : subjectList) {
            subjects.add(subject.mapToSubjectDTO());
        }
        return subjects;
    }

    public void deleteSubject(Long subjectId) {
        Optional<Subject> optionalSubject = subjectRepository.findById(subjectId);
        if (optionalSubject.isPresent()) {
            Subject subject = optionalSubject.get();

            if (!maOceny(subject)) {
                subjectRepository.deleteById(subjectId);
                return;
            }
            throw new SubjectHasGradesException("Przedmiot posiada przypisane oceny, id: " + subjectId);
        }
        throw new EntityNotFoundException("Nie znaleziono przedmiotu o id: " + subjectId);
    }

    private boolean maOceny(Subject subject) {
        for (Grade grade : subject.getGrades()) {
            if(subject.getGrades() != null) {
                return true;
            }
        }
        return false;
    }
}
