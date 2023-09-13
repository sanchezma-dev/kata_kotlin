package k.ms.usuarios.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "usuarios")
data class UsuarioEntity(

    /** Identificador del usuario */
    @Id
    val id: String? = null,

    /** Nombre único del usuario */
    @Indexed(unique = true)
    val nombre: String,

    /** Email único del usuario */
    @Indexed(unique = true)
    val email: String,

    /** Password del usuario */
    val pass: String
)
