package br.com.app;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public abstract class CommonBaseTest {

    @BeforeEach //Executa antes de cada teste
    public abstract void setUp();

}
