import k.ms.usuarios.KUsuariosApplication
import k.ms.usuarios.controller.UsuarioController
import k.ms.usuarios.dto.RespuestaDTO
import k.ms.usuarios.dto.UsuarioDTO
import k.ms.usuarios.service.UsuarioService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.util.ReflectionTestUtils

@SpringBootTest(classes = [KUsuariosApplication::class])
@ExtendWith(SpringExtension::class)
class UsuarioControllerTest {

    @Mock
    private lateinit var usuarioService: UsuarioService

    private lateinit var usuarioController: UsuarioController

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        usuarioController = UsuarioController()
        ReflectionTestUtils.setField(usuarioController, "serv", usuarioService)
    }

    @Test
    fun testList_NoUserFound() {
        val listEmpty = listOf<UsuarioDTO>()
        Mockito.`when`(usuarioService.findAll()).thenReturn(listEmpty)

        // Ejecutamos directamente el método list() del controlador
        val response: ResponseEntity<RespuestaDTO> = usuarioController.list()

        assertTrue(response.body!!.errorsExits)
    }
}
