package br.com.app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
@SpringBootTest
public abstract class DatabaseTest {

    @BeforeEach //Executa antes de cada teste
    public abstract void setUp();
}
