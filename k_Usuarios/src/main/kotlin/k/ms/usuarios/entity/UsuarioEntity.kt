package k.ms.usuarios.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "usuarios")
data class UsuarioEntity(

    /** User ID */
    @Id
    val id: String? = null,

    /** Unique user name */
    @Indexed(unique = true)
    val nombre: String,

    /** Unique user email */
    @Indexed(unique = true)
    val email: String,

    /** Unique user password */
    val pass: String
)
