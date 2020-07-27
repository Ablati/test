package kz.ablati.test.service;

import kz.ablati.test.common.ConflictException;
import kz.ablati.test.dto.ClientDto;
import kz.ablati.test.dto.RequestDto;
import kz.ablati.test.entity.Client;
import kz.ablati.test.mapper.ClientMapper;
import kz.ablati.test.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ClientService {


    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ClientMapper clientMapper;

    public Map<String, Object> addClient(RequestDto dto) {
        Map<String, Object> result = new HashMap<>();
        if(StringUtils.isEmpty(dto.getFirstName())&& StringUtils.isEmpty(dto.getLastName())){
            throw new ConflictException("Введите корректно имя клиента!");
        }
        setClient(dto.getFirstName(), dto.getLastName(), dto.getMiddleName(), new Client());
        result.put("status", 200);
        return result;
    }


    public Map<String, Object> deleteClient(RequestDto dto) {
        Map<String, Object> result = new HashMap<>();
        if (dto.getId() == null){
            throw new ConflictException("Введи id клиента!");
        }
        Optional<Client> client = clientRepository.findById(dto.getId());
        if (!client.isPresent()){
            throw new ConflictException("Клиент не существует!");
        }
        clientRepository.delete(client.get());
        result.put("status", 200);
        return result;
    }

    public Map<String, Object> getClient(int pageNumber) {
        Map<String, Object> result = new HashMap<>();
        Pageable pageRequest = PageRequest.of(pageNumber, 10, Sort.by("id").descending());
        List<Client> clients = clientRepository.findAllByIdNotNull(pageRequest);
        List<ClientDto> clientDtos = clientMapper.toDtos(clients);
        result.put("totalCount",(clientRepository.countAllByIdNotNull()));
        result.put("status", 200);
        result.put("list", clientDtos);
        return result;
    }


    public Map<String, Object> updateClient(RequestDto dto) {
        if (dto.getId() == null){
            throw new ConflictException("Введи id клиента!");
        }
        Optional<Client> client = clientRepository.findById(dto.getId());
        if (!client.isPresent()){
            throw new ConflictException("Клиент не существует!");
        }
        Map<String, Object> result = new HashMap<>();
        setClient(dto.getFirstName(), dto.getLastName(), dto.getMiddleName(),  client.get());
        result.put("status", 200);
        return result;
    }

    private void setClient(String firstName, String lastName, String middleName, Client client) {
        client.setFirstName(firstName);
        client.setLastName(lastName);
        client.setMiddleName(middleName);
        clientRepository.save(client);
    }

}

