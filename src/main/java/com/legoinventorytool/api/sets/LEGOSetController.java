package com.legoinventorytool.api.sets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/set")
public class LEGOSetController {

    private final LEGOSetService setService;

    @Autowired
    public LEGOSetController(LEGOSetService setService) {
        this.setService = setService;
    }

    @GetMapping
    public List<LEGOSet> getSets() {
        return setService.getSets();
    }
}
