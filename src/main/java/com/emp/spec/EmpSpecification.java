package com.emp.spec;

import com.emp.model.Emp;
import com.emp.dto.EmpSearchCriteria;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

public class EmpSpecification {
    /**
     * Builds a Specification<Emp> based on the provided EmpSearchCriteria.
     *
     * @param c the search criteria
     * @return a Specification<Emp> that can be used to query the database
     */
    public static Specification<Emp> byCriteria(EmpSearchCriteria c) {
        return (root, query, cb) -> {
            Predicate p = cb.conjunction();

            if (c == null) return p;

            if (c.getFirstName() != null) {
                p = cb.and(p, cb.like(cb.lower(root.get("firstName")), "%" + c.getFirstName().toLowerCase() + "%"));
            }
            if (c.getLastName() != null) {
                p = cb.and(p, cb.like(cb.lower(root.get("lastName")), "%" + c.getLastName().toLowerCase() + "%"));
            }
            if (c.getGender() != null) {
                p = cb.and(p, cb.equal(root.get("gender"), c.getGender()));
            }
            if (c.getSkills() != null) {
                p = cb.and(p, cb.like(cb.lower(root.get("skills")), "%" + c.getSkills().toLowerCase() + "%"));
            }
            if (c.getDeptId() != null) {
                Join<Object,Object> deptJoin = root.join("dept", JoinType.LEFT);
                p = cb.and(p, cb.equal(deptJoin.get("id"), c.getDeptId()));
            }
            if (c.getHireDateFrom() != null) {
                p = cb.and(p, cb.greaterThanOrEqualTo(root.get("hireDate"), c.getHireDateFrom()));
            }
            if (c.getHireDateTo() != null) {
                p = cb.and(p, cb.lessThanOrEqualTo(root.get("hireDate"), c.getHireDateTo()));
            }
            return p;
        };
    }
}
