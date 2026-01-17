package com.emp.spec;

import com.emp.dto.DeptSearchCriteria;
import com.emp.model.Dept;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public class DeptSpecification {

    public static Specification<Dept> byCriteria(DeptSearchCriteria c) {
        return (root, query, cb) -> {
            Predicate p = cb.conjunction();

            if (c == null) return p;

            if (c.getDeptName() != null) {
                p = cb.and(p, cb.like(cb.lower(root.get("deptName")), "%" + c.getDeptName().toLowerCase() + "%"));
            }


            return p;
        };
    }
}
