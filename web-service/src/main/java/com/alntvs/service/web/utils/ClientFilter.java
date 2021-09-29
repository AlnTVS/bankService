package com.alntvs.service.web.utils;

import com.alntvs.service.web.entities.Client;
import com.alntvs.service.web.repositories.specification.ClientSpecifications;
import lombok.Getter;
import org.springframework.data.jpa.domain.Specification;

import java.util.Map;

@Getter
public class ClientFilter {
    private Specification<Client> spec;
    private StringBuilder filterDefinition;

    public ClientFilter(Map<String, String> map) {
        this.spec = Specification.where(null);
        this.filterDefinition = new StringBuilder();
        if (map.containsKey("name") && !map.get("name").isEmpty()) {
            String name = map.get("name");
            spec = spec.and(ClientSpecifications.nameLike(name));
            filterDefinition.append("&name=").append(name);
        }
        if (map.containsKey("surname") && !map.get("surname").isEmpty()) {
            String surname = map.get("surname");
            spec = spec.and(ClientSpecifications.surnameLike(surname));
            filterDefinition.append("&surname=").append(surname);
        }
    }
}
