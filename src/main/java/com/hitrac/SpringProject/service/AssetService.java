package com.hitrac.SpringProject.service;


import com.hitrac.SpringProject.dto.AssetRequestDTO;
import com.hitrac.SpringProject.dto.Response;
import com.hitrac.SpringProject.model.Asset;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface AssetService {
    ResponseEntity allAssets();
    ResponseEntity addAsset(AssetRequestDTO assetRequestDTO);
    ResponseEntity<Response> getAsset(Long id);
    ResponseEntity<Response> updateAsset(Long id, AssetRequestDTO assetRequestDTO);
    ResponseEntity<Response> deleteAsset(Long id);

    ResponseEntity getAssetByName(String name);

    ResponseEntity<Response> allBranches();

    Page<Asset> findPaginated(int pageNo, int pageSize);
}
