package br.com.zup

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import io.micronaut.http.uri.UriBuilder
import io.micronaut.validation.Validated
import javax.transaction.Transactional
import javax.validation.Valid

@Validated
@Controller("/autores")
class CadastraNovoAutorController(val autorRepository: AutorRepository, //injeção de dependencia via construtor
                                  val enderecoClient: EnderecoClient) {

    @Post
    @Transactional
    fun cadastra (@Body @Valid request: NovoAutorRequest): HttpResponse<Any> {
        println("Request => $request")

      val enderecoResponse = enderecoClient.consulta(cep = request.cep)

        val autor = request.toModel(enderecoResponse.body()!!) // !! é colocado pq temos certeza que não é NULO

        println("Model => ${autor.nome}")//, ${autor.endereco.rua}")
        autorRepository.save(autor)

        val uri = UriBuilder.of("/autor/{id}")
            .expand(mutableMapOf(Pair("id", autor.id)))

        return HttpResponse.created(uri)
    }
}