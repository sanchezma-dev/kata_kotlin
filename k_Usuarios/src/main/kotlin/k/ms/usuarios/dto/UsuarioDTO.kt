package k.ms.usuarios.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty
import org.springframework.data.mongodb.core.index.Indexed

data class UsuarioDTO(

    /** Identificador del usuario */
    val id: String? = null,

    /** Nombre único del usuario */
    @field:NotEmpty(message = "El nombre del usuario debe estar informado")
    val nombre: String,

    /** Email único del usuario */
    @field:NotEmpty(message = "El campo email no puede estar vacío")
    @field:Email(message = "El formato del email no es el correcto")
    @field:Indexed(unique = true)
    var email: String,

    /** Password del usuario */
    @field:NotEmpty(message = "La contraseña no puede estar vacía")
    var pass: String
)
