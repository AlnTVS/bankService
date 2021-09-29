package com.alntvs.service.web.repositories;

import com.alntvs.service.web.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientsRepository extends JpaRepository<Client, Long>, JpaSpecificationExecutor<Client> {
}
