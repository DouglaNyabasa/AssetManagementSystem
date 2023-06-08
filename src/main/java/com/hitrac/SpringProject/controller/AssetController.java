package com.hitrac.SpringProject.controller;


import com.hitrac.SpringProject.dto.AssetRequestDTO;
import com.hitrac.SpringProject.dto.Response;
import com.hitrac.SpringProject.model.Asset;
import com.hitrac.SpringProject.service.AssetService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AssetController {

    private final AssetService assetService;

    public AssetController(AssetService assetService) {
        super();
        this.assetService = assetService;
    }
    @GetMapping("/allAssets")
    public ResponseEntity getAllAssets( ){
         return assetService.allAssets();

    }
    @GetMapping("/allBranches")
    public ResponseEntity<Response> getAllBranches(){
        return  assetService.allBranches();
    }

    @GetMapping("/getAssetByName")
    public ResponseEntity getAssetByName(String name){
        return assetService.getAssetByName(name);
    }
    @PutMapping("/update/{id}")
        public ResponseEntity<Response> updateAsset(@PathVariable Long id,AssetRequestDTO assetRequestDTO)
    {
            return  assetService.updateAsset(id,assetRequestDTO);
        }
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Response> deleteAsset(@PathVariable Long id){
        return  assetService.deleteAsset(id);
    }

    @GetMapping("/getAsset/{id}")
    public ResponseEntity<Response> getAsset(@PathVariable Long id){
        return  assetService.getAsset(id);
    }


    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable (value = "pageNo")int pageNo, Model model){
        int pageSize = 5;

        Page<Asset> page = assetService.findPaginated(pageNo,pageSize);
        List<Asset> assetList = page.getContent();
        model.addAttribute("currentPage",pageNo);
        model.addAttribute("totalPages",page.getTotalPages());
        model.addAttribute("totalItems",page.getTotalElements());
        model.addAttribute("listAssets",assetList);
        return "index";
    }

}
