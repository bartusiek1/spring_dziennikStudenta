package pl.sda.arp4._spring_dziennik.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import pl.sda.arp4._spring_dziennik.model.Grade;
import pl.sda.arp4._spring_dziennik.model.Student;
import pl.sda.arp4._spring_dziennik.model.dto.AddGradeRequest;
import pl.sda.arp4._spring_dziennik.model.dto.GradeDTO;
import pl.sda.arp4._spring_dziennik.model.dto.SubjectDTO;
import pl.sda.arp4._spring_dziennik.service.GradeService;
import pl.sda.arp4._spring_dziennik.service.StudentService;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@RequestMapping("/api/grade")
@RestController
@RequiredArgsConstructor

public class GradeController {

    private final GradeService gradeService;


    @PostMapping("/add")
    public void addGrade(@RequestParam Long studentId, Long subjectId, @RequestBody AddGradeRequest request) {
        log.info("Wywołano metodę addGrade");
        gradeService.addGrade(studentId, subjectId, request);
    }

    @GetMapping("/list")
    public List<Grade> allGradesList(@RequestParam Long studentId) {
        log.info("Wywołano metodę gradeList");
        return gradeService.allGradesList(studentId);
    }
    @GetMapping("/subject/list")

    public List<Grade> subjectGradesList(@RequestParam Long studentId, Long subjectId) {
        log.info("Wywołano metodę gradeList");
        return gradeService.subjectGradesList(studentId,subjectId);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable(name = "id") Long studentId, Long subjectId, Long gradeId) {
        log.info("Wywołano metodę: delete " + gradeId);
        gradeService.deleteGrade(gradeId, studentId, subjectId);
    }

    @PatchMapping("/update/{id}")
    public void update(@PathVariable(name = "id") Long studentId, Long subjectId, Long gradeId, @RequestBody Grade grade) {
        log.info("Wywołano metodę: update -> " + grade);
        gradeService.update(studentId, subjectId, gradeId, grade);
    }

}
