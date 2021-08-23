package br.com.zup

import io.micronaut.core.annotation.AnnotationValue
import javax.validation.Constraint
import kotlin.annotation.AnnotationRetention.RUNTIME
import kotlin.annotation.AnnotationTarget.*
import io.micronaut.validation.validator.constraints.*
import io.micronaut.validation.validator.constraints.ConstraintValidator
import javax.inject.Singleton

@MustBeDocumented
@Target(FIELD, CONSTRUCTOR)     // dizer qdo essa anotação vai rodar
@Retention(RUNTIME)
@Constraint(validatedBy = [PlacaValidator::class])

annotation class Placa(
    val message: String = "Placa com formato inválido!"
)
@Singleton
class PlacaValidator : ConstraintValidator<Placa, String> {
    override fun isValid(
        value: String?,
        annotationMetadata: AnnotationValue<Placa>,
        context: ConstraintValidatorContext
    ): Boolean {

        if(value == null){
            return true
        }
        //AAA-0A00    [A-Z]{3}[0-9][0-9A-Z][0-9]{2}
        return value.matches("[A-Z]{3}[0-9][0-9A-Z][0-9]{2}".toRegex())
    }

}


