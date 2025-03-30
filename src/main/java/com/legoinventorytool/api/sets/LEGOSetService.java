package com.legoinventorytool.api.sets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class LEGOSetService {

    private final LEGOSetRepository legoSetRepository;

    @Autowired
    public LEGOSetService(LEGOSetRepository legoSetRepository) {
        this.legoSetRepository = legoSetRepository;
    }

    public List<LEGOSet> getSets() {
        return legoSetRepository.findAll();
    }

    public void addNewSet(@RequestParam LEGOSet legoSet) {
        Optional<LEGOSet> setByUpc = legoSetRepository.findById(legoSet.getUpc());
//        if (setByUpc.isPresent()) {
//
//        }
        // TODO add verification of LEGO set creation
        legoSetRepository.save(legoSet);
    }

    public void deleteSet(Long upc) {
        if (legoSetRepository.existsByUpc(upc)) {
            legoSetRepository.deleteByUpc(upc);
        } else  {
            throw new RuntimeException("No LEGO set in inventory with upc " + upc);
        }
    }
}
