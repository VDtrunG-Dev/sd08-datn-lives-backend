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
    public ResponseEntity<ResponseObject> getAllRoles() {
        List<TRole> allRoles = roleService.getAllRoles();
        if (!allRoles.isEmpty()) {
            return ResponseEntity.ok(new ResponseObject("ok", "danh sách không phân trang", allRoles));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject("failed", "không có dữ liệu", null));
        }
    }

    @GetMapping("get-all-status")
    public ResponseEntity<?> getAllStatus(@RequestParam(defaultValue = "0", name = "page") Integer page) {

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(
                "OK", "Phân trang thành công", roleService.getAllStatus(page)
        ));
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
    @GetMapping("get-active-roles")
    public ResponseEntity<ResponseObject> getAll(@RequestParam(defaultValue = "0", name = "page") Integer page) {
        Page<TRole> activeRoles = roleService.getAll(1, PageRequest.of(page, 10));
        if (!activeRoles.isEmpty()) {
            return ResponseEntity.ok(new ResponseObject("ok", "Danh sách vai trò hoạt động", activeRoles));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject("failed", "Không có dữ liệu vai trò hoạt động", null));
        }
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
    @PutMapping("delete-fake/{id}")
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
    @PutMapping("return-data/{id}")
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
    @GetMapping("in-active-roles")
    public ResponseEntity<ResponseObject> getInActiveRoles(@RequestParam(defaultValue = "0", name = "page") Integer page) {
        Page<TRole> inActiveRoles = roleService.getInActiveRoles(0, PageRequest.of(page, 10));
        if (!inActiveRoles.isEmpty()) {
            return ResponseEntity.ok(new ResponseObject("ok", "Danh sách vai trò không hoạt động", inActiveRoles));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseObject("failed", "Không có dữ liệu vai trò không hoạt động", null));
        }
    }


    @GetMapping("search-all/")
    public ResponseEntity<ResponseObject> searchAll(
            @RequestParam String nameRole,
            @RequestParam String description,
            @RequestParam String createBy,
            @RequestParam Long id,
            @RequestParam(defaultValue = "0", name = "page") Integer page) {
        Page<TRole> resultPage = roleService.searchAll(nameRole, description, id, createBy, PageRequest.of(page, 10));
        if (!resultPage.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Tìm kiếm thành công", resultPage)
            );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("failed", "Không tìm thấy kết quả", null)
            );
        }
    }


    @GetMapping("search-by-keyword/")
    public ResponseEntity<ResponseObject> searchByKeyword(
            @RequestParam String keyword,
            @RequestParam(defaultValue = "0", name = "page") Integer page) {
        Page<TRole> resultPage = roleService.searchByKeyword(keyword, PageRequest.of(page, 5));
        if (!resultPage.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Tìm kiếm thành công", resultPage)
            );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("failed", "Không tìm thấy kết quả", null)
            );
        }
    }


}
