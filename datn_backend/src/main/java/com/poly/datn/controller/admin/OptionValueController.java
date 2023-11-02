package com.poly.datn.controller.admin;


import com.poly.datn.dto.OptionValueDTO;
import com.poly.datn.dto.ResponseObject;
import com.poly.datn.model.TOptionValue;
import com.poly.datn.service.impl.OptionValueServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/optionvalue")
public class OptionValueController {

    @Autowired
    private OptionValueServicesImpl optionValueServices;

    @GetMapping("")
    private ResponseEntity<?> pageFindAll(@RequestParam(name = "page",defaultValue = "0") int pageNumber){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok","Thành Công",optionValueServices.findAll(pageNumber))
        );
    }

    @PostMapping("/add")
    private ResponseEntity<?> pageSave(@RequestBody OptionValueDTO optionValue){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok",optionValueServices.save(optionValue),optionValue)
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

    @GetMapping("/findbyname/{name}")
    private ResponseEntity<?> pageFindByName(@PathVariable(name = "name") String optionValueName){

        TOptionValue optionValue = optionValueServices.findByName(optionValueName);
        if(optionValue == null){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("fail","OptionValue Không Tồn Tại","")
            );
        }
        else {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok","Thành Công",optionValueServices.findByName(optionValueName))
            );
        }

    }

    @GetMapping("/active/{id}")
    private ResponseEntity<?> pageActive(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok",optionValueServices.active(id),"")
        );
    }
}
