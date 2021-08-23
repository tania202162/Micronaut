package br.com.zup

import io.micronaut.test.annotation.TransactionMode
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import javax.inject.Inject

@MicronautTest(rollback = false, // default=true
                transactionMode = TransactionMode.SINGLE_TRANSACTION, // default = SEPARETE_TRANSACTION
                transactional = true) // default = true Utilizado em servidor GRPC

class CarrosControllerTest {

    @field:Inject
    lateinit var repository: CarroRepository

    @BeforeEach   // limpar a tabela ANTES de cada teste
    internal fun setup() {
        repository.deleteAll()
    }

    @AfterEach  // limpar a tabela APÓS de cada teste
    fun cleanup() {
        repository.deleteAll()
    }

    @Test
    internal fun `Deve inserir um novo carro`() {
        //ação
        repository.save(Carro("Gol", "HPX-1234"))

        //validação
        assertEquals(1, repository.count())
    }

    @Test
    internal fun `Deve encontrar carro por placa`() {
        //cenário
        repository.save(Carro("Palio", "OIP-9876"))

        //ação
        val existente = repository.existsByPlaca("OIP-9876")

        //validação
        assertTrue(existente)
    }

}
/*


        Mockito.`when`(enderecoClient.consulta(novoAutorRequest.cep)).thenReturn(HttpResponse.ok(enderecoResponse))

        val request = HttpRequest.POST("/autores", novoAutorRequest)

        val response = client.toBlocking().exchange(request,Any::class.java)

        assertEquals(HttpStatus.CREATED, response.status)
        assertTrue(response.headers.contains("Location"))
        assertTrue(response.header("Location")!!.matches("/autores/\\d".toRegex()))
    }

    @MockBean(EnderecoClient::class)
    fun enderecoMock(): EnderecoClient {
        return Mockito.mock(EnderecoClient::class.java)
    }
}


}
*/
