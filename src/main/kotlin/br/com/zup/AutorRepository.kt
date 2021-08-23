package br.com.zup

import io.micronaut.data.annotation.Query
import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository
import java.util.*

@Repository
interface AutorRepository : JpaRepository<Autor, Long> {

    @Query("SELECT a FROM Autor a WHERE a.email = :email")   // JPQL

    fun buscaPorEmail(email: String): Optional<Autor>
    fun findByEmail(email: String): Optional<Autor>

}