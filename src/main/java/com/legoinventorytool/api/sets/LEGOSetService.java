package com.legoinventorytool.api.sets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class LEGOSetService {

    private final LEGOSetRepository legoSetRepository;
    private final LEGOSetDTOMapper legoSetDTOMapper;

    @Autowired
    public LEGOSetService(LEGOSetRepository legoSetRepository, LEGOSetDTOMapper legoSetDTOMapper) {
        this.legoSetRepository = legoSetRepository;
        this.legoSetDTOMapper = legoSetDTOMapper;
    }

    public List<LEGOSetDTO> getSets() {
        return legoSetRepository.findAll()
                .stream()
                .map(legoSetDTOMapper).collect(Collectors.toList());
    }

    @Transactional
    public String addNewSet(LEGOSet legoSet) {
        if (legoSetRepository.existsLEGOSetsByUpc(legoSet.getUpc())) {
            legoSetRepository.save(legoSet);
            log.info("{}: [{}]Already exists in inventory, successfully added again " +
                    "to LEGO inventory successfully", legoSet.getUpc(), legoSet.getId());
            return MessageFormat.format("{0}: Already exists, added again successfully", legoSet.getUpc());
        }
        // TODO add verification of LEGO set creation.
        legoSetRepository.save(legoSet);
        log.info("{}: [{}]Successfully added to LEGO inventory successfully", legoSet.getUpc(), legoSet.getId());
        return MessageFormat.format("{0}: Successfully added", legoSet.getUpc());
    }

    public List<LEGOSetDTO> getSetsByUpc(long upc) {
        try {
            //TODO Fix {"timestamp":"2025-04-08T03:06:33.993+00:00","status":500,"error":"Internal Server Error","message":"Query did not return a unique result: 2 results were returned","path":"/api/v1/set"}
            log.info("{}: [{}] returning from inventory successfully", upc, legoSetRepository.findLegoSetByUpc(upc).get().getId());
            return legoSetRepository.findAllByUpc(upc)
                    .stream()
                    .map(legoSetDTOMapper).collect(Collectors.toList());
        } catch (Exception e) {
            log.error("{}: Error returning from inventory", upc, e);
            throw e;
            //TODO clean up exception logic to include the handling potential errors separately.
        }
    }

    @Transactional
    public void deleteSet(Long upc) {
        if (legoSetRepository.existsLEGOSetsByUpc(upc)) {
            //long  id = legoSetRepository.findById(upc).get().getId();
            legoSetRepository.deleteByUpc(upc);
            //log.info("{} [{}]: Successfully deleted from LEGO inventory successfully", upc, id);
        } else  {
            log.error("{}: Set not found via UPC", upc);
            throw new IllegalArgumentException(MessageFormat.format("{0}: Set not found via UPC", upc));
        }
    }
}
