package com.emp.spec;

import com.emp.dto.UserSearchCriteria;
import com.emp.model.User;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public class UserSpecification {
    /**
     * Builds a Specification<Emp> based on the provided EmpSearchCriteria.
     *
     * @param c the search criteria
     * @return a Specification<Emp> that can be used to query the database
     */
    public static Specification<User> byCriteria(UserSearchCriteria c) {
        return (root, query, cb) -> {
            Predicate p = cb.conjunction();

            if (c == null) return p;

            if (c.getName() != null) {
                p = cb.and(p, cb.like(cb.lower(root.get("name")), "%" + c.getName().toLowerCase() + "%"));
            }
            if (c.getRole() != null) {
                p = cb.and(p, cb.like(cb.lower(root.get("role")), "%" + c.getRole().toLowerCase() + "%"));
            }

            return p;
        };
    }
}
