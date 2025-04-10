package com.legoinventorytool.api.sets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;
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
    public Map<String, Object> addNewSet(LEGOSet legoSet) {
        if (legoSetRepository.existsLEGOSetsByUpc(legoSet.getUpc())) {
            LEGOSet newSet = legoSetRepository.save(legoSet);
            log.info("{}: [{}]Already exists in inventory, successfully added again " +
                    "to LEGO inventory successfully", newSet.getUpc(), newSet.getId());
            return Map.of("id", newSet.getId());
        }
        LEGOSet newSet = legoSetRepository.save(legoSet);
        log.info("{}: [{}]Successfully added to LEGO inventory successfully", newSet.getUpc(), newSet.getId());
        return Map.of("id", newSet.getId());
    }

//    public String updateSet(LEGOSet set) throws NoSuchElementException {
//        if (set.getName() == null || set.getName().isEmpty()) {
//            throw new MustUpdateNameException(MessageFormat.format("{0}: Name is empty or null", set.getUpc()));
//        }
//        List<LEGOSet> matches = legoSetRepository.findByUpcAndNameIsNullOrderByIdDesc(set.getUpc());
//        if (matches.size() == 0) {
//            throw new NoSuchElementException("No lego set with upc " + set.getUpc());
//        }
//        //TODO Update PUT method(s) to account for sets without anything beyond UPC and updating sets with a upcs
//        LEGOSet updatedSet = matches.getFirst();
//        updatedSet.setName(set.getName());
//
//    }

    public List<LEGOSetDTO> getSetsByUpc(long upc) {
        try {
            List<LEGOSetDTO> sets = legoSetRepository.findAllByUpc(upc)
                    .stream()
                    .map(legoSetDTOMapper).collect(Collectors.toList());
            String ids = sets.stream()
                            .map(set -> String.valueOf(set.id()))
                                    .collect(Collectors.joining(", "));
            log.info("{}: [{}] returned from inventory successfully", upc, ids);
            return sets;
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
