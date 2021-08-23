package br.com.zup

class DetalhesAutoresDto(
    val id: Long?,
    val nome: String,
    val email: String,
    val descricao: String,
) {
    constructor(autor: Autor) : this(
          id = autor.id,
        nome = autor.nome,
        email = autor.email,
        descricao = autor.descricao
    )
}