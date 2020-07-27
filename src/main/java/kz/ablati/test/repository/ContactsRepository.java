package kz.ablati.test.repository;

import kz.ablati.test.entity.Client;
import kz.ablati.test.entity.Contacts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ContactsRepository extends JpaRepository<Contacts, Long>, JpaSpecificationExecutor<Contacts> {

    List<Contacts> findByClient(Client client);
    Contacts findByNumber(String number);


}
