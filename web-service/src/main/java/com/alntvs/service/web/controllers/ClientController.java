package com.alntvs.service.web.controllers;

import com.alntvs.service.web.entities.Client;
import com.alntvs.service.web.utils.ClientFilter;
import com.alntvs.service.web.services.ClientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/client")
public class ClientController {
    private ClientsService clientsService;

    @Autowired
    public void setClientsService(ClientsService clientsService) {
        this.clientsService = clientsService;
    }

    @GetMapping("/list")
    public String showAll(Model model, @RequestParam Map<String, String> requestParams) {
        Integer pageNumber = Integer.parseInt(requestParams.getOrDefault("p", "1"));
        ClientFilter clientFilter = new ClientFilter(requestParams);
        Page<Client> clients =  clientsService.findAll(clientFilter.getSpec(), pageNumber);
        model.addAttribute("clients", clients);
        model.addAttribute("filterDef", clientFilter.getFilterDefinition().toString());
        return "all_clients";
    }

    @GetMapping("/bankAccs")
    public String showBankAccounts(Model model, @RequestParam Long clientId) {
        if(clientId == null) {
            return "all_clients";
        }
        Client client = clientsService.findById(clientId);
        model.addAttribute("client",client);
        return "bank_accounts";
    }

    @GetMapping("/delete")
    public String deleteClientById(@RequestParam Long clientId) {
        clientsService.deleteById(clientId);
        return "redirect:/client/list";
    }

    @GetMapping("/add")
    public String showFromAddNewUser() {
        return "add_user";
    }

    @PostMapping("/add")
    public String addNewUser(@ModelAttribute Client client) {
        clientsService.saveOrUpdate(client);
        return "redirect:/client/list";
    }
}
