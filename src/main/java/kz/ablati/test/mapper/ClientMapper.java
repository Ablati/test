package kz.ablati.test.mapper;

import kz.ablati.test.dto.ClientDto;
import kz.ablati.test.entity.Client;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ClientMapper {
    default List<ClientDto> toDtos(Collection<Client> clients) {
        return clients.stream().map(this::toDto).collect(Collectors.toList());
    }


    ClientDto toDto (Client client);
    Collection<ClientDto> toClientResponseDtos (Collection<Client> clients);


}
