package com.hitrac.SpringProject.serviceImpl;

import com.hitrac.SpringProject.dto.AssetRequestDTO;
import com.hitrac.SpringProject.model.Asset;
import com.hitrac.SpringProject.model.Branch;
import com.hitrac.SpringProject.repsitory.AssetRepository;
import com.hitrac.SpringProject.service.AssetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
@Slf4j
public class AssetServiceImpl implements AssetService {

    private AssetRepository assetRepository;

    public AssetServiceImpl(AssetRepository assetRepository) {
        super();
        this.assetRepository = assetRepository;
    }
    @Override
   public ResponseEntity allAssets(){
        List<Asset> assets = assetRepository.findAll();
        return ResponseEntity.ok().body(assets);
    }

    @Override
    public ResponseEntity addAsset(AssetRequestDTO assetRequestDTO) {
        Asset asset = new Asset();
        asset.setBranch(assetRequestDTO.getBranch());
        asset.setName(assetRequestDTO.getName());
        asset.setDescription(assetRequestDTO.getDescription());
        asset.setTime_In(assetRequestDTO.getTime_In());
        asset.setTime_Out(assetRequestDTO.getTime_Out());
        assetRepository.save(asset);
       return ResponseEntity.ok().body(asset);
    }

    @Override
    public ResponseEntity getAsset(Long id) {
        Optional<Asset> asset = assetRepository.findById(id);
        if (asset.isPresent()){
            return ResponseEntity.ok().body(asset);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("asset not found") ;
    }

    @Override
    public ResponseEntity updateAsset(Long id, AssetRequestDTO assetRequestDTO) {
        Optional<Asset> asset = assetRepository.findById(id);
        if(asset.isPresent()){
            Asset asset1 = asset.get();
            asset1.setName(assetRequestDTO.getName());
            asset1.setBranch(assetRequestDTO.getBranch());
            asset1.setDescription(assetRequestDTO.getDescription());
            asset1.setTime_In(assetRequestDTO.getTime_In());
            asset1.setTime_Out(assetRequestDTO.getTime_Out());

            assetRepository.save(asset1);
            return ResponseEntity.ok().body("asset details updated") ;
        }
       return ResponseEntity.ok().body("asset not found") ;
    }

    @Override
    public ResponseEntity deleteAsset(Long id) {
        Optional<Asset> asset = assetRepository.findById(id);
        if(asset.isPresent()){
            assetRepository.delete(asset.get());
            return ResponseEntity.ok().body("asset deleted");

        }
        return ResponseEntity.ok().body("asset not found") ;
    }

    @Override
    public ResponseEntity getAssetByName(String name) {
        Optional<Asset> asset = assetRepository.findAllByName(name);
        if(asset.isPresent()){
           return ResponseEntity.ok().body(asset) ;
        }
        return ResponseEntity.ok().body("asset not found");
    }

    @Override
    public ResponseEntity allBranches() {
        List<Branch> branches = new ArrayList<>();
        branches.add(Branch.MainBranch);
        branches.add(Branch.MazoweBranch);
        return ResponseEntity.ok().body(branches);
    }

    @Override
    public Page<Asset> findPaginated(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo -1,pageSize );
        return this.assetRepository.findAll(pageable);
    }


}
