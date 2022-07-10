package pl.sda.arp4._spring_dziennik.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.sda.arp4._spring_dziennik.exception.StudentHasGradesException;
import pl.sda.arp4._spring_dziennik.model.Grade;
import pl.sda.arp4._spring_dziennik.model.Student;
import pl.sda.arp4._spring_dziennik.model.dto.AddStudentRequest;
import pl.sda.arp4._spring_dziennik.model.dto.StudentDTO;
import pl.sda.arp4._spring_dziennik.repository.StudentRepository;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor

public class StudentService {

    private final StudentRepository studentRepository;


    public List<StudentDTO> findAll() {
        List<Student> studentList = studentRepository.findAll();

        List<StudentDTO> studenci = new ArrayList<>();
        for (Student student : studentList) {
            studenci.add(student.mapToStudentDTO());
        }

        return studenci;
    }

    public void addStudent(AddStudentRequest request) {
        Student student = mapAddStudentRequestToStudent(request);
        studentRepository.save(student);
        return;
    }

    private Student mapAddStudentRequestToStudent(AddStudentRequest request) {
        return new Student(
                request.getStudentName(),
                request.getStudentSurname(),
                request.getBirthDate(),
                request.getStudentIndexNumber());
    }

    public void deleteStudent(Long studentId) {
        Optional<Student> optionalStudent = studentRepository.findById(studentId);
        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();

            if (!maOceny(student)) {
                studentRepository.deleteById(studentId);
                return;
            }
            throw new StudentHasGradesException("Student posiada oceny, id: " + studentId);
        }
        throw new EntityNotFoundException("Nie znaleziono studenta o id: " + studentId);
    }

    private boolean maOceny(Student student) {
    for (Grade grade : student.getGrades()) {
        if(grade.getValue() != null) {
            return true;
        }
    }
        return false;
    }

    public void update(Long studentId, Student edytowanyStudent) {

        Optional<Student> studentOptional = studentRepository.findById(studentId);
        if (studentOptional.isPresent()) {
            Student student = studentOptional.get();
            if(edytowanyStudent.getName()!=null){
                student.setName(edytowanyStudent.getName());
            }
            if(edytowanyStudent.getSurname()!=null){
                student.setSurname(edytowanyStudent.getSurname());
            }
            if(edytowanyStudent.getBirthDate()!=null){
                student.setBirthDate(edytowanyStudent.getBirthDate());
            }
            studentRepository.save(student);
            return;
        }
        throw new EntityNotFoundException("Nie odnaleziono studenta o id: " + studentId);
    }
}