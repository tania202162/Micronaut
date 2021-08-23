package br.com.zup

import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.QueryValue
import javax.transaction.Transactional

@Controller("/autores")
class BuscaAutorController(val autorRepository: AutorRepository) {

    //autores?email:tania.alves@zup.com.br
    //(@QueryValue(defaultValue = "") email: String) -> pesquisa por email sendo informado ou não
   // fun busca(@QueryValue(defaultValue = "") email: String): HttpResponse<Any> {

    @Get
    @Transactional
    fun lista(@QueryValue(defaultValue = "") email: String) : HttpResponse<Any> {   // lista por email
        if(email.isBlank()) {
            val lista = autorRepository.findAll()
            val resposta = lista.map { autor -> DetalhesAutoresDto(autor) }  // Response
            return HttpResponse.ok(resposta)
        }
            val possivelAutor = autorRepository.findByEmail(email) //optionalAutor

        if(possivelAutor.isEmpty) {
            return HttpResponse.notFound()
        }
        val autor = possivelAutor.get()
        return HttpResponse.ok(DetalhesAutoresDto(autor.id, autor.nome,  autor.email, autor.descricao))

        //println("autor: ${DetalhesAutoresDto(autor)}")
        //    println("Endereço: ${autor.endereco}")

    }
}