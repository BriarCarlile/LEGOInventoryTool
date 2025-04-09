package com.legoinventorytool.api.sets;

import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class LEGOSetDTOMapper implements Function<LEGOSet, LEGOSetDTO> {
    @Override
    public LEGOSetDTO apply(LEGOSet set) {
        return new LEGOSetDTO(
                set.getId(),
                set.getUpc(),
                set.getName()
        );
    }
}
