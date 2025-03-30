package com.legoinventorytool.api.sets;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
class LEGOSet {
    @Id
    @SequenceGenerator(
            name = "legoSet_sequence",
            sequenceName = "legoSet_sequence",
            allocationSize = 1
    )
    @GeneratedValue (
            strategy = GenerationType.SEQUENCE,
            generator = "legoSet_sequence"
    )
    private Long id;
    private Long upc;
    private String name;
    private Long modelNumber;
    private String details;
    private LocalDate initDate;
    private LocalDate updateDate;
    private boolean built;

    public LEGOSet(Long upc) {
        this.upc = upc;
    }

    public LEGOSet(Long upc, LocalDate initDate)  {
        this.upc = upc;
        this.initDate = initDate;
    }

    public LEGOSet() {

    }
}
