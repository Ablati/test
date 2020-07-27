package kz.ablati.test.controller;

import kz.ablati.test.common.ConflictException;
import kz.ablati.test.common.ExceptionResponse;
import kz.ablati.test.dto.ContactsDto;
import kz.ablati.test.service.ContactsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/contact")
public class ContactsController {
    @Autowired
    private ContactsService contactService;

    @PostMapping("add")
    ResponseEntity<Map<String,  Object>> addClient(@RequestBody ContactsDto dto) {
        try {
            Map<String, Object> result = contactService.add(dto);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (ConflictException exc) {
            return new ResponseEntity<>(ExceptionResponse.create(exc.getMessage(), exc.getStatus()), HttpStatus.valueOf(exc.getStatus()));
        } catch (Exception exc) {
            return new ResponseEntity<>(ExceptionResponse.create(exc.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping ("delete")
    ResponseEntity<Map<String,  Object>> deleteClient (@RequestBody ContactsDto dto) {
        try {
            Map<String, Object> result = contactService.delete(dto);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (ConflictException exc) {
            return new ResponseEntity<>(ExceptionResponse.create(exc.getMessage(), exc.getStatus()), HttpStatus.valueOf(exc.getStatus()));
        } catch (Exception exc) {
            return new ResponseEntity<>(ExceptionResponse.create(exc.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping ("update")
    ResponseEntity<Map<String,  Object>> updateClient (@RequestBody ContactsDto dto) {
        try {
            Map<String, Object> result = contactService.update(dto);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (ConflictException exc) {
            return new ResponseEntity<>(ExceptionResponse.create(exc.getMessage(), exc.getStatus()), HttpStatus.valueOf(exc.getStatus()));
        } catch (Exception exc) {
            return new ResponseEntity<>(ExceptionResponse.create(exc.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.FORBIDDEN);
        }
    }
}
