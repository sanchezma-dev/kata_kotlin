package k.ms.usuarios.convert.impl

import k.ms.usuarios.convert.UsuarioConvert
import k.ms.usuarios.dto.UsuarioDTO
import k.ms.usuarios.entity.UsuarioEntity
import org.springframework.stereotype.Component

@Component
class UsuarioConvertImpl : UsuarioConvert {
    override fun convertUsuarioDTO(source: UsuarioEntity): UsuarioDTO {
        return UsuarioDTO(source.id, source.nombre, source.email, source.pass)
    }

    override fun convertUsuarioEntity(source: UsuarioDTO): UsuarioEntity {
        return UsuarioEntity(source.id, source.nombre, source.email, source.pass)
    }

}
