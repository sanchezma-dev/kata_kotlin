package k.ms.usuarios.convert

import k.ms.usuarios.dto.UsuarioDTO
import k.ms.usuarios.entity.UsuarioEntity

interface UsuarioConvert {

    fun convertUsuarioDTO(source: UsuarioEntity): UsuarioDTO

    fun convertUsuarioEntity(source: UsuarioDTO): UsuarioEntity

}