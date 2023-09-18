package k.ms.usuarios.service

import k.ms.usuarios.dto.RespuestaDTO
import k.ms.usuarios.dto.UsuarioDTO

interface UsuarioService {

    /**
     * Returns the list of users
     *
     * @return list of users
     */
    fun findAll(): List<UsuarioDTO>

    /**
     * Returns the user matching the searched name
     *
     * @param name name user
     * @return user dto
     */
    fun findByNombre(name: String): UsuarioDTO

    /**
     * Saves and returns the user
     *
     * @param user user dto
     * @return user dto
     */
    fun saveUser(user: UsuarioDTO): UsuarioDTO

    /**
     * Removes the user
     *
     * @param name user
     * @return
     */
    fun deleteByName(name: String): RespuestaDTO

    /**
     * Returns true or false if user is found
     *
     * @param name name user
     * @return true or false
     */
    fun exitsUser(name: String): Boolean

}