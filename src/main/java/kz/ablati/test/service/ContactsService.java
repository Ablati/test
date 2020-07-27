package kz.ablati.test.service;

import kz.ablati.test.common.ConflictException;
import kz.ablati.test.dto.ContactsDto;
import kz.ablati.test.entity.Client;
import kz.ablati.test.entity.Contacts;
import kz.ablati.test.repository.ClientRepository;
import kz.ablati.test.repository.ContactsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class ContactsService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ContactsRepository contactRepository;

    public Map<String, Object> add(ContactsDto dto) {
        Map<String, Object> result = new HashMap<>();
        if(StringUtils.isEmpty(dto.getNumber()) && dto.getClientId() != null){
            throw new ConflictException("Введите корректные данные для номера!");
        }
        Optional<Client> client = clientRepository.findById(dto.getClientId());
        if(!client.isPresent()){
            throw new ConflictException("Клиент не существует!");
        }
        setContact(dto.getNumber(), client.get(), dto.getType(), new Contacts());
        result.put("status", 200);
        return result;
    }

    private void setContact(String number, Client client, byte type, Contacts contacts) {
        contacts.setClient(client);
        contacts.setNumber(number);
        contacts.setType(type);
        contactRepository.save(contacts);
    }

    public Map<String, Object> delete(ContactsDto dto) {
        Map<String, Object> result = new HashMap<>();
        if(StringUtils.isEmpty(dto.getNumber())){
            throw new ConflictException("Введенный номер не существует!");
        }
        Contacts contacts = contactRepository.findByNumber(dto.getNumber());
        contactRepository.delete(contacts);
        result.put("status", 200);
        return  result;
    }

    public Map<String, Object> update(ContactsDto dto) {
        Map<String, Object> result = new HashMap<>();
        if(StringUtils.isEmpty(dto.getNumber()) && dto.getClientId() != null){
            throw new ConflictException("Введенный номер не существует!");
        }
        Optional<Client> client = clientRepository.findById(dto.getClientId());
        if(!client.isPresent()){
            throw new ConflictException("Клиент не существует!");
        }
        Contacts contacts = contactRepository.findByNumber(dto.getNumber());
        setContact(dto.getNumber(), client.get(), dto.getType(), contacts);
        result.put("status", 200);
        return  result;
    }

}
