package com.alntvs.service.web.repositories.specification;

import com.alntvs.service.web.entities.Client;
import org.springframework.data.jpa.domain.Specification;

public class ClientSpecifications {
    public static Specification<Client> nameLike(String name) {
        return (Specification<Client>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("name"), "%" + name + "%");
    }

    public static Specification<Client> surnameLike(String surname) {
        return (Specification<Client>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("surname"), "%" + surname + "%");
    }
}
