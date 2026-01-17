package com.emp.controller;

import com.emp.dto.EmpDTO;
import com.emp.dto.EmpSearchCriteria;
import com.emp.service.EmpService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/employee/emp")
@RequiredArgsConstructor
public class EmpController {

    @Autowired
    private EmpService empService;


    @GetMapping("/{id}")
    public ResponseEntity<EmpDTO> getById(@PathVariable Long id) {
        EmpDTO dto = empService.getById(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }



    @PostMapping
    public ResponseEntity<EmpDTO> create(@RequestBody EmpDTO newEmp) {
        EmpDTO created = empService.createEmployee(newEmp);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping
    public ResponseEntity<EmpDTO> update(@RequestBody EmpDTO updatedEmp) {
        EmpDTO result = empService.updateEmployee(updatedEmp);
        return result != null ? ResponseEntity.ok(result) : ResponseEntity.notFound().build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        empService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }

    /**
     *
     * @param criteria
     * @param pageable  e.g. https://localhost:8443/employee/emp/search?page=1&size=10&sort=lastName,asc means
     * page=0 and size=10 control pagination (which slice of results the server returns).
     * page=0 = first page (Spring Data pages are zero-based).
     * size=10 = up to 10 items per page.
     * Offset (row index) = page * size. So page=0,size=10 returns rows with indexes 0..9; page=1,size=10 returns 10..19.
      *sort=lastName tells Spring Data to order the query results by the lastName property of the Emp entity. By default the direction is ascending; you can set direction explicitly with ,asc or ,desc. Multiple sort params are allowed (applied in order).
     * @return
     */
    @PostMapping("/search")
    public ResponseEntity<Page<EmpDTO>> search(@RequestBody EmpSearchCriteria criteria, Pageable pageable) {
        Page<EmpDTO> page = empService.search(criteria, pageable);
        return ResponseEntity.ok(page);
    }

}


