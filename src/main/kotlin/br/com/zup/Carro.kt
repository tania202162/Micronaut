package br.com.zup

import io.micronaut.core.annotation.Introspected
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType.IDENTITY
import javax.persistence.Id
import javax.validation.constraints.NotBlank


@Entity
class Carro (
    @field:NotBlank @Column(nullable = false) val modelo: String?,
    @field:NotBlank @Column(nullable = false, unique = true)  val placa: String) //@field:Placa
{
    @Id
    @GeneratedValue(strategy = IDENTITY)
    val id: Long? = null
}