package k.ms.usuarios.convert

import k.ms.usuarios.dto.UsuarioDTO
import k.ms.usuarios.entity.UsuarioEntity

interface UsuarioConvert {

    /**
     * Convert user of entity to dto
     *
     * @param source user entity
     * @return user dto
     */
    fun convertUsuarioDTO(source: UsuarioEntity): UsuarioDTO

    /**
     * Convert user of dto to entity
     *
     * @param source user dto
     * @return user entity
     */
    fun convertUsuarioEntity(source: UsuarioDTO): UsuarioEntity

}