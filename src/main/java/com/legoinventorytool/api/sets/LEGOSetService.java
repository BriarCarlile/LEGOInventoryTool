package com.legoinventorytool.api.sets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

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

    public String addNewSet(LEGOSet legoSet) {
        if (legoSetRepository.existsByUpc(legoSet.getUpc())) {
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
            log.info("{}: [{}] returning from inventory successfully", upc, legoSetRepository.findById(upc).get().getId());
            return legoSetRepository.findAllByUpc(upc)
                    .stream()
                    .map(legoSetDTOMapper).collect(Collectors.toList());
        } catch (Exception e) {
            log.error("{}: Error returning from inventory successfully", upc, e);
            throw e;
            //TODO clean up exception logic to include the handling potential errors separately.
        }
    }

    public void deleteSet(Long upc) {
        if (legoSetRepository.existsByUpc(upc)) {
            long  id = legoSetRepository.findById(upc).get().getId();
            legoSetRepository.deleteByUpc(upc);
            log.info("{} [{}]: Successfully deleted from LEGO inventory successfully", upc, id);
        } else  {
            log.error("{}: Set not found via UPC", upc);
            throw new IllegalArgumentException(MessageFormat.format("{0}: Set not found via UPC", upc));
        }
    }
}
