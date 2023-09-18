package k.ms.usuarios.repository

import k.ms.usuarios.entity.UsuarioEntity
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository


@Repository
interface UsuarioRepository : MongoRepository<UsuarioEntity, String> {

    /**
     * Returns the user who matches the name
     *
     * @param name name user
     * @return usuarioEntity
     */
    fun findByNombre(name: String): UsuarioEntity

    /**
     * Returns true if there is a user with the specified name, false otherwise
     * @param name name user
     * @return true or false
     */
    fun existsByNombre(name: String): Boolean

    /**
     * Removes the user matching the name
     *
     * @param name name user
     */
    fun deleteByNombre(name: String)

}

