package kz.ablati.test.repository;

import kz.ablati.test.entity.Client;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ClientRepository extends PagingAndSortingRepository<Client, Long> {

    List<Client> findAllByIdNotNull(Pageable pageable);
    Long countAllByIdNotNull();
}
