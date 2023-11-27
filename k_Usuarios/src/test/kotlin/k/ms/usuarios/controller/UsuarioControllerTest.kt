import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.validation.Validation
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
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.util.ReflectionTestUtils
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.validation.BeanPropertyBindingResult

@SpringBootTest(classes = [KUsuariosApplication::class])
@ExtendWith(SpringExtension::class)
@AutoConfigureMockMvc
class UsuarioControllerTest {

    @Mock
    private lateinit var usuarioService: UsuarioService

    private lateinit var usuarioController: UsuarioController

    @Autowired
    private lateinit var mockMvc: MockMvc


    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        //FIXME Al inicializar usuarioController, inicializa a su vez usuarioService.
        // Este debe estar en el constructor del controller
        usuarioController = UsuarioController(usuarioService)
    }

    @Test
    fun testList_NoUserFound() {
        val listEmpty = listOf<UsuarioDTO>() //list empty
        // Mock of list empty with usuarioService
        `when`(usuarioService.findAll()).thenReturn(listEmpty)
        //  Call to the list of controller methods
        val response: ResponseEntity<RespuestaDTO> = usuarioController.list()
        // Checking test responses
        assertTrue(response.body!!.errorsExits)
        val messageString = response.body!!.listMessages[0]
        assertEquals("No hay ningún usuario registrado", messageString)
    }

    @Test
    fun testList_IsNull() {
        val listNull = null // list is null
        //Mock
        `when`(usuarioService.findAll()).thenReturn(listNull)
        // Controller
        val response = usuarioController.list()
        // Checking test responses
        assertEquals(HttpStatus.NOT_FOUND, response.statusCode)
    }

    @Test
    fun `user does not exist`(){
        val name: String = "nameNoExist"
        val user = UsuarioDTO(nombre = "prueba", email = "email", pass = "pass")

        // Create a simulated BindingResult with errors
        val bindingResult = BeanPropertyBindingResult(user, "user")

        // Mock
        `when`(usuarioService.exitsUser(name)).thenReturn(false)
        // Controller
        val response = usuarioController.udpate(user, name, bindingResult)

        // Assertions based on the response
        assertTrue(response.body!!.errorsExits)
        assertEquals(HttpStatus.BAD_REQUEST, response.statusCode)
    }

    // FIXME Test to simulate an http request
    @Test
    fun testUpdate_NonExistentUser() {
        val nonExistentName = "NombreInexistente"

        val user = UsuarioDTO(nombre = "prueba1", email = "email", pass = "password")

        val content = ObjectMapper().writeValueAsString(user)

        mockMvc.perform(
            MockMvcRequestBuilders.put("/k_usuarios/edit/$nonExistentName")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isBadRequest)
    }

    @Test
    fun createError() {
        // User with errors
        val user = UsuarioDTO(nombre = "", email = "invalidEmail", pass = "")

        // Creating a simulated BindingResult with errors
        val validator = Validation.buildDefaultValidatorFactory().validator
        val violations = validator.validate(user)
        val bindingResult = BeanPropertyBindingResult(user, "user")
        for (violation in violations) {
            bindingResult.rejectValue(violation.propertyPath.toString(), "", violation.message)
        }

        // Execute controller
        val response: ResponseEntity<RespuestaDTO> = usuarioController.createUser(user, bindingResult)
        // Error list
        val listMenError = response.body!!.listMessages
        // Response test
        assertEquals(HttpStatus.BAD_REQUEST, response.statusCode)
        assertTrue(response.body!!.errorsExits)
        assertEquals(3, listMenError.size)

    }

}