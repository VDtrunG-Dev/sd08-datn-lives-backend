package com.poly.datn.controller.admin;

import com.poly.datn.dto.ResponseObject;
import com.poly.datn.model.TOption;
import com.poly.datn.service.IOptionServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/option")
public class OptionController {

    @Autowired
    private IOptionServices optionServices;

    @GetMapping("/findall")
    private ResponseEntity<?> pageOption(@RequestParam(name = "page") int page){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok","Thành Công",optionServices.findAll(page)
        ));
    }

    @GetMapping("/{id}")
    private ResponseEntity<?> pageFindById(@PathVariable Long id){
        TOption option = optionServices.findById(id);
        if (option != null){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok","Thành Công",option)
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("fail","Thất Bại","Không tìm thấy")
        );

    }

    @PostMapping("/add")
    private ResponseEntity<?> pageAdd(@RequestBody TOption option){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("",optionServices.save(option),"")
        );
    }

    @PutMapping("/update/{id}")
    private ResponseEntity<?> pageUpdate(@RequestBody TOption option,@PathVariable Long id){
        option.setId(id);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("Ok",optionServices.update(option),"")
        );
    }

    @PutMapping("/active/{id}")
    private ResponseEntity<?> pageActive(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("Ok",optionServices.active(id),"")
        );
    }

    @DeleteMapping("/delete/{id}")
    private ResponseEntity<?> pageDelete(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("Ok",optionServices.delete(id),"")
        );
    }
}
