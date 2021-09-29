package com.alntvs.service.web.services;

import com.alntvs.service.web.entities.BankAccount;
import com.alntvs.service.web.entities.Client;
import com.alntvs.service.web.exceptions.ClientNotFoundException;
import com.alntvs.service.web.repositories.ClientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClientsService {
    private ClientsRepository clientsRepository;
    private BankAccountsService bankAccountsService;

    @Autowired
    public void setClientsRepository(ClientsRepository clientsRepository) {
        this.clientsRepository = clientsRepository;
    }

    public List<Client> findAll() {
        return clientsRepository.findAll();
    }

    public Client saveOrUpdate(Client client) {
        return clientsRepository.save(client);
    }

    public Page<Client> findAll(Specification<Client> spec, Integer page) {
        if (page < 1) {
            page = 1;
        }
        return clientsRepository.findAll(spec, PageRequest.of(page - 1, 5));
    }

    public Client findById(Long clientId) {
        return clientsRepository.findById(clientId).orElseThrow(() -> new ClientNotFoundException("Can't found client with id = " + clientId));
    }

    @Transactional
    public void deleteById(Long clientId) {
        for (BankAccount ba : findById(clientId).getBankAccounts()) {
            {
                Long id = ba.getId();
                bankAccountsService.deleteById(id);
            }
            clientsRepository.deleteById(clientId);
        }
    }
}
