package k.ms.usuarios.controller

import jakarta.validation.Valid
import k.ms.usuarios.dto.RespuestaDTO
import k.ms.usuarios.dto.UsuarioDTO

import k.ms.usuarios.service.UsuarioService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("k_usuarios")
class UsuarioController {

    /** Service usuario */
    @Autowired
    private lateinit var serv: UsuarioService

    @GetMapping("/listUsers")
    fun list(): ResponseEntity<RespuestaDTO> {
        val userList: List<UsuarioDTO> = serv.findAll()
        val responseController = RespuestaDTO()
        if (userList.isNullOrEmpty()) {
            responseController.errorsExits = true
            responseController.listMessages = listOf("No hay ningún usuario registrado")
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseController)
        }
        responseController.users = userList
        return ResponseEntity.ok().body(responseController)
    }

    @PutMapping("edit/{name}")
    fun udpate(
        @Valid @RequestBody userDto: UsuarioDTO, @PathVariable name: String,
        bindingResult: BindingResult
    ): ResponseEntity<RespuestaDTO> {
        val responseController = RespuestaDTO()
        if (!serv.exitsUser(name)) {
            return ResponseEntity.badRequest().body(
                RespuestaDTO(
                    listMessages = listOf("No existe un usuario con ese nombre"),
                    errorsExits = true
                )
            )
        }
        if (bindingResult.hasErrors()) {
            responseController.errorsExits = true
            val errorsList = ArrayList<String>()
            for (fieldError in bindingResult.fieldErrors) {
                val defaultMessage = fieldError.defaultMessage
                defaultMessage?.let { errorsList.add(it) }
            }
            responseController.listMessages = errorsList
            return ResponseEntity.badRequest().body(responseController)
        }
        val userUpdate: UsuarioDTO = serv.findByNombre(name)
        userUpdate.email = userDto.email
        userUpdate.pass = userDto.pass
        responseController.users = listOf(serv.saveUser(userUpdate))
        return ResponseEntity.ok().body(responseController)
    }


    @PostMapping("/createUser")
    fun createUser(@Valid @RequestBody user: UsuarioDTO, bindingResult: BindingResult): ResponseEntity<*> {
        val responseController = RespuestaDTO()
        if (bindingResult.hasErrors()) {
            responseController.errorsExits = true
            val errorsList = ArrayList<String>()
            for (fieldError in bindingResult.fieldErrors) {
                val defaultMessage = fieldError.defaultMessage
                defaultMessage?.let { errorsList.add(it) }
            }
            responseController.listMessages = errorsList
            return ResponseEntity.badRequest().body(responseController)
        }
        responseController.users = listOf(serv.saveUser(user))
        return ResponseEntity.ok().body(responseController)
    }

    @DeleteMapping("/deleteUser/{name}")
    fun deleteUser(@PathVariable name: String): ResponseEntity<RespuestaDTO> {
        val responseController = serv.deleteByName(name)
        return if (responseController.errorsExits) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseController)
        } else {
            ResponseEntity.ok(responseController)
        }
    }


}