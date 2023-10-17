package k.ms.usuarios.dto

data class RespuestaDTO(
    /** List of messages */
    var listMessages: List<String> = listOf(),
    /** Exits errors */
    var errorsExits: Boolean = false,
        /** List of users */
    var users: List<UsuarioDTO> = listOf()
)