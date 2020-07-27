package kz.ablati.test.dto;

import lombok.Data;

import java.util.Set;

@Data
public class ClientDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String middleName;
    private Set<ContactsDto> contacts;

}
