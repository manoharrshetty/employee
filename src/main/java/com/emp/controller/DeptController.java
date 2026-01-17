package com.emp.controller;

import com.emp.dto.DeptDTO;
import com.emp.dto.DeptSearchCriteria;
import com.emp.service.DeptService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/employee/dept")
@RequiredArgsConstructor
public class DeptController {

    @Autowired
    private DeptService deptService;


    @GetMapping("/{id}")
    public ResponseEntity<DeptDTO> getById(@PathVariable Long id) {
        DeptDTO dto = deptService.getById(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }


    // Create
    @PostMapping
    public ResponseEntity<DeptDTO> create(@RequestBody DeptDTO newDept) {
        DeptDTO created = deptService.createDept(newDept);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
    /*
     * Remember for put and delete body @RequestBody can exist too !!
     */

    // Update (id in path for clarity)
    @PutMapping("/{id}")
    public ResponseEntity<DeptDTO> update(@PathVariable Long id, @RequestBody DeptDTO updateDept) {
         DeptDTO result = deptService.updateDept(updateDept);
        return result != null ? ResponseEntity.ok(result) : ResponseEntity.notFound().build();
    }

    // Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        deptService.deleteDept(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/search")
    public ResponseEntity<Page<DeptDTO>> search(@RequestBody DeptSearchCriteria criteria, Pageable pageable) {
        Page<DeptDTO> page = deptService.search(criteria, pageable);
        return ResponseEntity.ok(page);
    }

}


