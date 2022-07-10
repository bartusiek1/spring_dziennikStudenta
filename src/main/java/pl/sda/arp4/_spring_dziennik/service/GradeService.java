package pl.sda.arp4._spring_dziennik.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.sda.arp4._spring_dziennik.exception.BrakujacyPrametrException;
import pl.sda.arp4._spring_dziennik.exception.SubjectHasGradesException;
import pl.sda.arp4._spring_dziennik.model.Grade;
import pl.sda.arp4._spring_dziennik.model.Student;
import pl.sda.arp4._spring_dziennik.model.Subject;
import pl.sda.arp4._spring_dziennik.model.dto.AddGradeRequest;
import pl.sda.arp4._spring_dziennik.repository.GradeRepository;
import pl.sda.arp4._spring_dziennik.repository.StudentRepository;
import pl.sda.arp4._spring_dziennik.repository.SubjectRepository;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor

public class GradeService {

    private final GradeRepository gradeRepository;
    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;


    public void addGrade(Long studentId, Long subjectId, AddGradeRequest request) {
        Optional<Student> optionalStudent = studentRepository.findById(studentId);
        Optional<Subject> optionalSubject = subjectRepository.findById(subjectId);

        if (optionalStudent.isPresent() && optionalSubject.isPresent()) {
            Student student = optionalStudent.get();
            Subject subject = optionalSubject.get();

            Grade newGrade = new Grade();
            newGrade.setValue(request.getValue());
            newGrade.setStudent(student);
            newGrade.setSubject(subject);

            gradeRepository.save(newGrade);
            return;
        }
        throw new EntityNotFoundException("Nie znaleziono studenta lub przedmiotu");
    }

    public List<Grade> allGradesList(Long studentId) {
        Optional<Student> studentOptional = studentRepository.findById(studentId);
        if (studentOptional.isPresent()) {
            Student student = studentOptional.get();

            List<Grade> gradesList = new ArrayList<>(student.getGrades());

            return gradesList;
        }
        throw new EntityNotFoundException("Nie znaleziono studenta o id: " + studentId);
    }


    public List<Grade> subjectGradesList(Long studentId, Long subjectId) {
        Optional<Student> studentOptional = studentRepository.findById(studentId);
        Optional<Subject> subjectOptional = subjectRepository.findById(subjectId);

        if (studentOptional.isPresent() && subjectOptional.isPresent()) {
            Student student = studentOptional.get();
            Subject subject = subjectOptional.get();

            List<Grade> ocenyStudenta = new ArrayList<>(student.getGrades());
            List<Grade> ocenyStudentaZprzedmiotu = new ArrayList<>()
            for (Grade grades : ocenyStudenta) {
                if (grades.getSubject().getId() == subjectId) {
                    ocenyStudentaZprzedmiotu.add(grades);
                }
            }
            return ocenyStudentaZprzedmiotu;
        }
        throw new EntityNotFoundException("Nie odnaleziono wskazanego studenta lub przedmiotu");
    }

    public void deleteGrade(Long studentId, Long subjectId, Long gradeId) {
        Optional<Student> optionalStudent = studentRepository.findById(studentId);
        Optional<Subject> optionalSubject = subjectRepository.findById(subjectId);
        Optional<Grade> optionalGrade = gradeRepository.findById(gradeId);
        if (optionalStudent.isPresent() && optionalSubject.isPresent() && optionalGrade.isPresent()) {
            Student student = optionalStudent.get();
            Subject subject = optionalSubject.get();
            Grade grade = optionalGrade.get();

            gradeRepository.deleteById(gradeId);
            return;
        }
        throw new BrakujacyPrametrException("Nie ma oceny spełeniającej podane kryteria: " + studentId + subjectId + gradeId);
    }

    public void update(Long studentId, Long subjectId, Long gradeId, Grade editedGrade) {
        Optional<Student> studentOptional = studentRepository.findById(studentId);
        Optional<Subject> optionalSubject = subjectRepository.findById(subjectId);
        Optional<Grade> optionalGrade = gradeRepository.findById(gradeId);

        if (studentOptional.isPresent() && optionalSubject.isPresent() && optionalGrade.isPresent()) {
            Student student = studentOptional.get();
            Subject subject = optionalSubject.get();
            Grade grade = optionalGrade.get();

            if(editedGrade.getValue()!=null){
                grade.setValue(editedGrade.getValue());
            }
            gradeRepository.save(grade);
            return;
        }
        throw new EntityNotFoundException("Nie odnaleziono oceny spełniającej podane kryteria: " + studentId + subjectId + gradeId);
    }
    }