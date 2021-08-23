package br.com.zup

import io.micronaut.http.HttpResponse
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Consumes
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Produces
import io.micronaut.http.annotation.QueryValue
import io.micronaut.http.client.annotation.Client


@Client("\${clients.cep.url}")    //("http://localhost:8081/cep/busca") Yuri//     //"\${clients.cep.url}")  esses dados estão em application.yml
interface EnderecoClient {

  //  @Get("{cep}/json")   // ´passo o cep para o site e retorna cidade, cep e rua
 //   @Get(consumes = [ MediaType.APPLICATION_XML]) //  SERIALIZAR para XML
//                   OU
  //  @Consumes(MediaType.APPLICATION_XML)
//    @Produces(MediaType.APPLICATION_XML) // para METODO POST USA OS 2

    @Get("{cep}/json")
    fun consulta( cep: String): HttpResponse<EnderecoResponse>  // EnderecoResponse = DTO
}