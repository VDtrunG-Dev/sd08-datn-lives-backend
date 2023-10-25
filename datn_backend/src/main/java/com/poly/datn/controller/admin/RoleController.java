package com.poly.datn.controller.admin;

import com.poly.datn.dto.ResponseObject;
import com.poly.datn.model.TRole;
import com.poly.datn.service.impl.RoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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

    @DeleteMapping("delete/{id}")
    public ResponseEntity<ResponseObject> deleteRole(@PathVariable Long id) {
        roleService.deleteRole(id);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Xóa vai trò thành công", "")
        );
    }
}
