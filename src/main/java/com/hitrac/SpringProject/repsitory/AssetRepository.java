package com.hitrac.SpringProject.repsitory;


import com.hitrac.SpringProject.model.Asset;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AssetRepository extends JpaRepository<Asset,Long> {
    Optional<Asset> findAllByName(String name);
}
