package br.com.zup

import javax.persistence.Embeddable

//Quer dizer que a classe está habilitada a compor uma entidade (Autor)
@Embeddable  // dizer para o hibernate salvar no banco de dados como uma entidade
class Endereco(enderecoResponse: EnderecoResponse,
               val numero: String,
               val cep: String) {

    val rua = enderecoResponse.logradouro
    val cidade = enderecoResponse.localidade
    val estado = enderecoResponse.uf
}