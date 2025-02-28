package org.example.yolofarmbe.Controller;

import org.example.yolofarmbe.Service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.example.yolofarmbe.Entity.Record;

@RestController
@RequestMapping("/api/records")
public class RecordController {
    @Autowired
    private RecordService recordService;

    //recordType can be: amountofwater,moisture,light,humidity,temperature
    @GetMapping("/{recordType}")
    public List<? extends Record> getRecord(@PathVariable String recordType){
        return recordService.fetchRecords(recordType);
    }

    //recordType can be: amountofwater,moisture,light,humidity,temperature
    @GetMapping("/{recordType}/{farm_id}")
    public List<? extends Record> getRecordByFarmId(@PathVariable String recordType, @PathVariable int farm_id){
        return recordService.fetchRecordsByFarmId(recordType, farm_id);
    }

    //recordType can be: amountofwater,moisture,light,humidity,temperature
    @DeleteMapping("/{recordType}/{record_id}")
    public ResponseEntity<String> deleteById(@PathVariable String recordType, @PathVariable int record_id) {
        String res = recordService.deleteRecord(recordType,record_id);
        return ResponseEntity.ok(res);
    }

}
