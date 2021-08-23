package br.com.zup.br.com.zup

import br.com.zup.*
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import javax.inject.Inject

@MicronautTest
internal class BuscaAutorControllerTest {

    @field:Inject
    lateinit var autorRepository: AutorRepository

    @field:Inject
    @field:Client("/")
    lateinit var client: HttpClient

    lateinit var autor: Autor

    @BeforeEach
    internal fun setup() {

        val enderecoResponse = EnderecoResponse("Rua das Laranejeiras", "Rio de Janeiro", "RJ")
        val endereco = Endereco(enderecoResponse, "37", "11222333")
        autor = Autor("Rafael Ponte", "rafael.ponte@zup.com.br", "Marajá dos Legados", endereco)
        autorRepository.save(autor)

    }

    @AfterEach // limpar BD após insert
    internal fun tearDown() {
        autorRepository.deleteAll()
    }

    @Test
    internal fun `Deve buscar um autor quando um email valido eh informado`() {

        val response = client.toBlocking().exchange("/autores?email=${autor.email}", DetalhesAutoresDto::class.java)

        assertEquals(HttpStatus.OK, response.status)
        assertNotNull(response.body())
        assertEquals(autor.nome, response.body()!!.nome)
        assertEquals(autor.descricao, response.body()!!.descricao)
        assertEquals(autor.email, response.body()!!.email)

    }
    }