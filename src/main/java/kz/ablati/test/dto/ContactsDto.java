package kz.ablati.test.dto;

import kz.ablati.test.entity.Client;
import lombok.Data;

@Data
public class ContactsDto {
    private Long clientId;
    private String number;
    private byte type;
}
