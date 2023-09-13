package k.ms.usuarios.dto

data class RespuestaDTO (var listMessages: List<String> = listOf(), var errorsExits: Boolean = false, var users: List<UsuarioDTO> = listOf())