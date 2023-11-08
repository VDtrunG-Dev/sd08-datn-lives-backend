package com.poly.datn.controller.admin;

import com.poly.datn.dto.ResponseObject;
import com.poly.datn.model.TPoints;
import com.poly.datn.service.impl.PointServiceimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/admin/point")
public class PointController {


    @Autowired
    private PointServiceimpl pointService;


    @PostMapping("/create")
    public ResponseEntity<ResponseObject> add(@RequestBody TPoints tPoints) {
        TPoints savedPoint = pointService.save(tPoints);
        if (savedPoint != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    new ResponseObject("ok", "Thêm mới point thành công!", savedPoint)
            );
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ResponseObject("false", "Thêm mới point thất bại!", null)
            );
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<TPoints> update(@RequestBody TPoints tPoints) {
        return ResponseEntity.ok(pointService.save(tPoints));
    }

    @DeleteMapping("/delete{id}")
    public ResponseEntity<ResponseObject> delete(@PathVariable Long id) {
        try {
            pointService.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Xóa thành công ", "")
            );
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("Xóa thất bại", "Không thể tìm thấy point để xóa", "")
            );
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<TPoints> getById(@PathVariable Long id) {
        TPoints tPoints = pointService.getById(id);
        if (tPoints != null) {
            return ResponseEntity.ok(tPoints);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/read")
    public ResponseEntity<List<TPoints>> getAll() {
        return ResponseEntity.ok(pointService.getAll());
    }


    @GetMapping("/paginated")
    public ResponseEntity<ResponseObject> getAllPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        int tongPoint = pointService.getAll().size();
        int soTrang = tongPoint / size;
        if (page > soTrang) {
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(
                    "ok", "Không có dữ liệu", ""));
        }

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(
                "ok", "Phân trang thành công.", pointService.getAllPaginated(page, size)));
    }


    @GetMapping("search/{id}")
    public ResponseEntity<ResponseObject> findById(@PathVariable Long id) {
        Optional<TPoints> foundPoints = pointService.getPointsById(id);
        if (foundPoints.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Tìm kiếm point thành công!", foundPoints)
            );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("false", "Không thể tìm thấy point có id  = " + id, "")
            );
        }
    }


    @GetMapping("/bystatus")
    public ResponseEntity<List<TPoints>> getAllByStatus() {
        List<TPoints> points = pointService.getAllByStatus(0);
        if (points.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(points);
    }

    // Xóa ảo
    @PutMapping("isDeleted/{id}")
    public ResponseEntity<ResponseObject> markPointAsDeleted(@PathVariable Long id) {
        Optional<TPoints> pointOptional = pointService.getPointsById(id);
        if (pointOptional.isPresent()) {
            TPoints point = pointOptional.get();
            point.setStatus(0);

            TPoints updatedPoint = pointService.update(id, point);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Đánh dấu xóa điểm thành công", updatedPoint)
            );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("failed", "Không tồn tại điểm này", null)
            );
        }
    }

    // Khôi phục xóa ảo
    @PutMapping("restore/{id}")
    public ResponseEntity<ResponseObject> restorePoint(@PathVariable Long id) {
        Optional<TPoints> pointOptional = pointService.getPointsById(id);
        if (pointOptional.isPresent()) {
            TPoints point = pointOptional.get();

            if (point.getStatus() == 0) {
                point.setStatus(1);
                TPoints restoredPoint = pointService.update(id, point);
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("ok", "Khôi phục điểm thành công", restoredPoint)
                );
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                        new ResponseObject("failed", "Điểm này chưa bị xóa", null)
                );
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("failed", "Không tồn tại điểm này", null)
            );
        }
    }


    @GetMapping("/byStatus/1/paged")
    public ResponseEntity<ResponseObject> getAllByStatus1Paged(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Page<TPoints> points = pointService.getAllByStatusPaged(1, page, size);

        if (points.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(
                "ok", "Thành công", pointService.getAllByStatusPaged(1, page, size)));

    }

    // tìmkiem
    @GetMapping("/search")
    public List<TPoints> searchPoints(
            @RequestParam(value = "customerId", required = false) Long customerId,
            @RequestParam(value = "transactionDate", required = false) Date transactionDate,
            @RequestParam(value = "transactionAmount", required = false) BigDecimal transactionAmount,
            @RequestParam(value = "pointsEarned", required = false) Integer pointsEarned) {
        return pointService.searchAll(customerId, transactionDate, transactionAmount, pointsEarned);
    }


    @GetMapping("/searchByKeyword")
    public List<TPoints> searchPointsByKeyword(@RequestParam("keyword") String keyword) {
        return pointService.searchByKeyword(keyword);
    }

}
