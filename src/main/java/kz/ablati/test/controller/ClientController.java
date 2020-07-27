package kz.ablati.test.controller;

import kz.ablati.test.common.ConflictException;
import kz.ablati.test.common.ExceptionResponse;
import kz.ablati.test.dto.PageNumberDto;
import kz.ablati.test.dto.RequestDto;
import kz.ablati.test.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/client")
public class ClientController {

  @Autowired
  private ClientService clientService;


    @PostMapping ("add")
    ResponseEntity<Map<String,  Object>> addClient(@RequestBody RequestDto dto) {
        try {
            Map<String, Object> result = clientService.addClient(dto);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (ConflictException exc) {
            return new ResponseEntity<>(ExceptionResponse.create(exc.getMessage(), exc.getStatus()), HttpStatus.valueOf(exc.getStatus()));
        } catch (Exception exc) {
            return new ResponseEntity<>(ExceptionResponse.create(exc.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.FORBIDDEN);
        }
  }

    @PostMapping ("get")
    ResponseEntity<Map<String,  Object>> getClient(@RequestBody PageNumberDto dto) {
        try {
            Map<String, Object> result = clientService.getClient(dto.getPageNumber());
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (ConflictException exc) {
            return new ResponseEntity<>(ExceptionResponse.create(exc.getMessage(), exc.getStatus()), HttpStatus.valueOf(exc.getStatus()));
        } catch (Exception exc) {
            return new ResponseEntity<>(ExceptionResponse.create(exc.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping ("delete")
    ResponseEntity<Map<String,  Object>> deleteClient (@RequestBody RequestDto dto) {
        try {
            Map<String, Object> result = clientService.deleteClient(dto);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (ConflictException exc) {
            return new ResponseEntity<>(ExceptionResponse.create(exc.getMessage(), exc.getStatus()), HttpStatus.valueOf(exc.getStatus()));
        } catch (Exception exc) {
            return new ResponseEntity<>(ExceptionResponse.create(exc.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping ("update")
    ResponseEntity<Map<String,  Object>> updateClient (@RequestBody RequestDto dto) {
        try {
            Map<String, Object> result = clientService.updateClient(dto);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (ConflictException exc) {
            return new ResponseEntity<>(ExceptionResponse.create(exc.getMessage(), exc.getStatus()), HttpStatus.valueOf(exc.getStatus()));
        } catch (Exception exc) {
            return new ResponseEntity<>(ExceptionResponse.create(exc.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value()), HttpStatus.FORBIDDEN);
        }
    }




}
