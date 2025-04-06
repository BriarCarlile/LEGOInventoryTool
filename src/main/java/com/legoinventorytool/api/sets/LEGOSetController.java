package com.legoinventorytool.api.sets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

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
    public ResponseEntity<?> addNewSet(@RequestBody LEGOSet newSet) {
        try {
            String response = setService.addNewSet(newSet);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public List<LEGOSetDTO> getAllSets() {
        return setService.getSets();
    }

    @GetMapping("{upc}")
    public List<LEGOSetDTO> getSetsByUpc(@PathVariable long upc) {
        return setService.getSetsByUpc(upc);
    }

    @DeleteMapping(path = "{upc}")
    public ResponseEntity<?> deleteSet(@PathVariable("upc") Long upc) {
        try {
            setService.deleteSet(upc);
            return ResponseEntity.ok("Set deleted");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
