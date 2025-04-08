package com.legoinventorytool.api.sets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/")
public class LEGOSetController {

    private final LEGOSetService setService;

    @Autowired
    public LEGOSetController(LEGOSetService setService) {
        this.setService = setService;
    }

    @PostMapping("/set")
    public ResponseEntity<?> addNewSet(@RequestBody LEGOSet newSet) {
        try {
            String response = setService.addNewSet(newSet);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/sets")
    public List<LEGOSetDTO> getAllSets() {
        return setService.getSets();
    }

    @GetMapping("/set")
    public List<LEGOSetDTO> getSetsByUpc(@RequestParam long upc) {
        return setService.getSetsByUpc(upc);
    }

    @DeleteMapping("/set")
    public ResponseEntity<?> deleteSet(@RequestParam(value = "upc") long upc) {
        try {
            setService.deleteSet(upc);
            return ResponseEntity.ok("Set deleted");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
