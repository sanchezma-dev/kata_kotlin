package k.ms.usuarios.service.impl

import k.ms.usuarios.convert.UsuarioConvert
import k.ms.usuarios.dto.RespuestaDTO
import k.ms.usuarios.dto.UsuarioDTO
import k.ms.usuarios.service.UsuarioService
import k.ms.usuarios.repository.UsuarioRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
class UsuarioServiceImpl : UsuarioService {

    @Autowired
    private lateinit var repo: UsuarioRepository

    @Autowired
    private lateinit var convert: UsuarioConvert


    override fun findAll(): List<UsuarioDTO> {
        return repo.findAll().stream()
            .map { e -> convert.convertUsuarioDTO(e) }
            .collect(Collectors.toList())
    }

    override fun findByNombre(name: String): UsuarioDTO {
        return convert.convertUsuarioDTO(repo.findByNombre(name))
    }

    override fun saveUser(user: UsuarioDTO): UsuarioDTO =
        convert.convertUsuarioDTO(repo.save(convert.convertUsuarioEntity(user)))

    override fun deleteByName(name: String): RespuestaDTO {
        val responseDto = RespuestaDTO()
        val userExists = repo.existsByNombre(name)
        if (userExists) {
            repo.deleteByNombre(name)
        } else {
            responseDto.listMessages = listOf("No existe el usuario con el nombre proporcionado")
            responseDto.errorsExits = true
        }
        return responseDto
    }

    override fun exitsUser(name: String): Boolean {
        return repo.existsByNombre(name)
    }

}