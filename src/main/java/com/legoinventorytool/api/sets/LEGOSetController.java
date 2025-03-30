package com.legoinventorytool.api.sets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/set")
public class LEGOSetController {

    private final LEGOSetService setService;

    @Autowired
    public LEGOSetController(LEGOSetService setService) {
        this.setService = setService;
    }

    @PostMapping
    public void addNewSet(@RequestBody LEGOSet newSet) {
        setService.addNewSet(newSet);
    }

    @GetMapping
    public List<LEGOSet> getAllSets() {
        return setService.getSets();
    }

    @DeleteMapping(path = "{upc}")
    public void deleteSet(@PathVariable("upc") Long upc) {
        setService.deleteSet(upc);
    }
}
