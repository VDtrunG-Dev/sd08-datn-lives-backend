package com.poly.datn.controller.admin;

import com.poly.datn.dto.ResponseObject;
import com.poly.datn.model.TPoints;
import com.poly.datn.service.impl.PointServiceimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/admin/point")
public class PointController {


    @Autowired
    private PointServiceimpl pointService;


    @PostMapping("/add")
    public ResponseEntity<TPoints> add(@RequestBody TPoints tPoints) {
        return ResponseEntity.ok(pointService.save(tPoints));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TPoints> update(@RequestBody TPoints tPoints) {
        return ResponseEntity.ok(pointService.save(tPoints));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        pointService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<TPoints> getById(@PathVariable Long id) {
        TPoints tPoints = pointService.getById(id);
        if (tPoints != null) {
            return ResponseEntity.ok(tPoints);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/view")
    public ResponseEntity<List<TPoints>> getAll() {
        return ResponseEntity.ok(pointService.getAll());
    }


    @GetMapping("/paginated")
    public ResponseEntity<Page<TPoints>> getAllPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size) {
        return ResponseEntity.ok(pointService.getAllPaginated(page, size));
    }


    @GetMapping("/{id}")
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
    @PutMapping("markAsDeleted/{id}")
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


}