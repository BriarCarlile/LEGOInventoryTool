package com.legoinventorytool.api.sets;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class LEGOSetService {

    public List<LEGOSet> getSets() {
        return List.of(
                new LEGOSet(
                        673419389457L,
                        LocalDate.now()
                )
        );
    }
}
