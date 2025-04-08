package com.legoinventorytool.api.sets;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface LEGOSetRepository extends JpaRepository<LEGOSet, Long>{


    Optional<LEGOSet> findLegoSetByUpc(Long upc);


    //@Query("SELECT s FROM LEGOSet s WHERE s.upc = ?1")
    boolean existsLEGOSetsByUpc(Long upc);

    void deleteByUpc(Long upc);

    List<LEGOSet> findAllByUpc(Long upc);
}
