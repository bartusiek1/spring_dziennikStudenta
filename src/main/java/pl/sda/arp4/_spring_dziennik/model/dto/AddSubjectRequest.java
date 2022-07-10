package pl.sda.arp4._spring_dziennik.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class AddSubjectRequest {
    private String nameOfSubject;
}
