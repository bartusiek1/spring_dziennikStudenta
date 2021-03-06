package pl.sda.arp4._spring_dziennik.model.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GradeDTO {

    private Double value;
    private LocalDateTime dateAdded;

}
