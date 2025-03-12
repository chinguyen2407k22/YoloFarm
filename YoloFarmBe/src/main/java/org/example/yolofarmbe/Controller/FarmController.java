package org.example.yolofarmbe.Controller;

import org.example.yolofarmbe.Entity.Farm;
import org.example.yolofarmbe.Exception.ResourceNotFoundException;
import org.example.yolofarmbe.Response.FarmResponse;
import org.example.yolofarmbe.Service.FarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/farms")
public class FarmController {

    @Autowired
    FarmService farmService;

    @GetMapping
    public List<Farm> getAllFarm(){
        return farmService.getAllFarm();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FarmResponse> getFarmById(@PathVariable int id){
        try {
            FarmResponse farmResponse = farmService.getFarmById(id);
            return ResponseEntity.ok(farmResponse);

        }catch (ResourceNotFoundException e){
            return ResponseEntity.badRequest().body(FarmResponse.builder()
                    .message(e.getMessage())
                    .farm(null)
                    .build());
        }
    }

    @PostMapping
    public ResponseEntity<FarmResponse> addNewFarm(@RequestBody Farm farm){
        FarmResponse farmResponse = farmService.addNewFarm(farm);
        return ResponseEntity.ok(farmResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FarmResponse> updateAFarm(@RequestBody Farm farm, @PathVariable int id){
        try {
            FarmResponse farmResponse = farmService.updateAFarm(id, farm);
            return ResponseEntity.ok(farmResponse);

        }catch (ResourceNotFoundException e){
            return ResponseEntity.badRequest().body(FarmResponse.builder()
                    .message(e.getMessage())
                    .farm(null)
                    .build());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<FarmResponse> deleteAFarm(@PathVariable int id){
        try {
            FarmResponse farmResponse = farmService.deleteAFarm(id);
            return ResponseEntity.ok(farmResponse);

        }catch (ResourceNotFoundException e){
            return ResponseEntity.badRequest().body(FarmResponse.builder()
                    .message(e.getMessage())
                    .farm(null)
                    .build());
        }
    }
}
