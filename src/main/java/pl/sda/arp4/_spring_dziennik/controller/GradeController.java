package pl.sda.arp4._spring_dziennik.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.sda.arp4._spring_dziennik.service.GradeService;
import pl.sda.arp4._spring_dziennik.service.StudentService;


@Slf4j
@RequestMapping("/api/grade")
@RestController
@RequiredArgsConstructor

public class GradeController {

    private final GradeService gradeService;
}
