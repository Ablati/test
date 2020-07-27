package kz.ablati.test;

import kz.ablati.test.controller.ClientController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ClientTest {

    @Autowired
    private ClientController clientController;

    @Test
    public void test() throws Exception{
        assertThat(clientController).isNotNull();

    }

}
