package k.ms.usuarios.service

import k.ms.usuarios.dto.RespuestaDTO
import k.ms.usuarios.dto.UsuarioDTO

interface UsuarioService {

    fun findAll(): List<UsuarioDTO>

    fun findByNombre(name: String): UsuarioDTO

<<<<<<< HEAD
    fun saveUser(user: UsuarioDTO): UsuarioDTO

    fun deleteByName(name: String): RespuestaDTO

    fun exitsUser(name: String): Boolean
=======
   //FIXME Ver si se usa este método
    fun exitsByName(name: String): Boolean

    fun saveUser(user: UsuarioDTO): UsuarioDTO

    fun deleteByName(name: String): RespuestaDTO;
>>>>>>> 72efa57edec8d92680719ecd13cd17824bfa6d26


}