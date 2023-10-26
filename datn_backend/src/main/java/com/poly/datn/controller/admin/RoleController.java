package com.poly.datn.controller.admin;

import com.poly.datn.dto.ResponseObject;
import com.poly.datn.model.TRole;
import com.poly.datn.service.impl.RoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/role/")
public class RoleController {
    @Autowired
    private RoleServiceImpl roleService;

    @GetMapping("view")
    public List<TRole> getAllRoles() {
        return roleService.getAllRoles();
    }
    @GetMapping("getAllStatus")
    public ResponseEntity<?> getAllStatus(@RequestParam(defaultValue = "0", name = "page") Integer page) {
        Page<TRole> allRoles = roleService.getAllStatus(page);
        return ResponseEntity.ok(allRoles);
    }
    @GetMapping("find/{id}")
    public ResponseEntity<ResponseObject> findById(@PathVariable Long id) {
        Optional<TRole> foundRole = roleService.getRoleById(id);
        if (foundRole.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Truy vấn vai trò thành công!", foundRole)
            );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("false", "Không tìm thấy vai trò với ID = " + id, "")
            );
        }
    }

    @PostMapping("add")
    public ResponseEntity<ResponseObject> createRole(@RequestBody TRole role) {
        TRole createdRole = roleService.createRole(role);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ResponseObject("ok", "Tạo vai trò thành công", createdRole)
        );
    }
    @GetMapping("getActiveRoles")
    public ResponseEntity<?> getAll(@RequestParam(defaultValue = "0", name = "page") Integer page){
        Page<TRole> activeRoles = roleService.getAll(1, PageRequest.of(page, 5));
        return ResponseEntity.ok(activeRoles);
    }
    @PutMapping("update/{id}")
    public ResponseEntity<ResponseObject> updateRole(@RequestBody TRole newRole, @PathVariable Long id) {
        TRole updatedRole = roleService.updateRole(id, newRole);
        if (updatedRole != null) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Cập nhật vai trò thành công", updatedRole)
            );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("failed", "Không tìm thấy vai trò để cập nhật hoặc cập nhật thất bại", "")
            );
        }
    }
    @PutMapping("deleteFake/{id}")
    public ResponseEntity<ResponseObject> deleteFake(@PathVariable Long id) {
        Optional<TRole> roleOptional = roleService.getRoleById(id);
        if (roleOptional.isPresent()) {
            TRole role = roleOptional.get();
            role.setStatus(0);

            TRole delete = roleService.updateRole(id, role);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Xóa thành công", delete)
            );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("failed", "Không tồn tại role này", "")
            );
        }
    }
    @PutMapping("returnData/{id}")
    public ResponseEntity<ResponseObject> returnData(@PathVariable Long id) {
        Optional<TRole> roleOptional = roleService.getRoleById(id);
        if (roleOptional.isPresent()) {
            TRole role = roleOptional.get();
            role.setStatus(1);

            TRole delete = roleService.updateRole(id, role);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Khôi phục thành công", delete)
            );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("failed", "Khôi phục thất bại", "")
            );
        }
    }
    @DeleteMapping("delete/{id}")
    public ResponseEntity<ResponseObject> deleteRole(@PathVariable Long id) {
        roleService.deleteRole(id);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Xóa vai trò thành công", "")
        );
    }
    @GetMapping("inActiveRoles")
    public ResponseEntity<?> getInActiveRoles(@RequestParam(defaultValue = "0", name = "page") Integer page) {
        Page<TRole> inActiveRoles = roleService.getInActiveRoles(0, PageRequest.of(page, 5));
        return ResponseEntity.ok(inActiveRoles);
    }

    @GetMapping("search-all/")
    public List<TRole> searchAll(@RequestParam String nameRole, @RequestParam String description){
        return roleService.searchAll(nameRole, description);
    }
    @GetMapping("search-by-keyword/")
    public List<TRole> searchByKeyword(@RequestParam String keyword){
        return roleService.searchByKeyword(keyword);
    }
}
