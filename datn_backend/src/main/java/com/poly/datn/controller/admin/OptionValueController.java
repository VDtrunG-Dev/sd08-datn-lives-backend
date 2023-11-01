package com.poly.datn.controller.admin;


import com.poly.datn.dto.ResponseObject;
import com.poly.datn.model.TOptionValue;
import com.poly.datn.service.impl.OptionValueServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/optionvalue")
public class OptionValueController {

    @Autowired
    private OptionValueServices optionValueServices;

    @GetMapping("")
    private ResponseEntity<?> pageFindAll(@RequestParam(name = "page",defaultValue = "0") int pageNumber){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok","Thành Công",optionValueServices.findAll(pageNumber))
        );
    }

    @PostMapping("/add")
    private ResponseEntity<?> pageSave(@RequestBody TOptionValue optionValue,@RequestBody Long optionId){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok",optionValueServices.save(optionValue,optionId),optionValue)
        );
    }

    @PutMapping("/update/{id}")
    private ResponseEntity<?> pageUpdate(@RequestBody TOptionValue optionValue,@PathVariable Long id,@RequestBody String optionId){
        optionValue.setId(id);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok",optionValueServices.update(optionValue),optionValue)
        );
    }

    @DeleteMapping("/delete/{id}")
    private ResponseEntity<?> pageDelete(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok",optionValueServices.deleteById(id),"")
        );
    }

    @GetMapping("/active/{id}")
    private ResponseEntity<?> pageActive(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok",optionValueServices.active(id),"")
        );
    }
}
