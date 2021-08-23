package br.com.zup

import io.micronaut.core.annotation.Introspected
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@Introspected
data class NovoAutorRequest(
    @field:NotBlank val nome: String,
    @field:NotBlank @field:Email val email: String,
    @field:NotBlank @field:Size(max = 1024) val descricao: String,
    @field:NotBlank var cep: String,
    @field:NotBlank var numero: String
) {
    fun toModel(enderecoResponse: EnderecoResponse): Autor {

        val endereco = Endereco(enderecoResponse, numero, cep)
        return Autor(nome, email, descricao, endereco)
    }

/*    fun paraAutor(): Autor {
        return Autor(nome, email, descricao,endereco)
    }*/
}
