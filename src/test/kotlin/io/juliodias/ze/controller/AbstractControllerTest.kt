package io.juliodias.ze.controller

import com.fasterxml.jackson.databind.ObjectMapper
import java.nio.charset.Charset
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.core.io.Resource
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.mock.web.MockHttpServletResponse
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post
import org.springframework.util.StreamUtils

@SpringBootTest
@AutoConfigureMockMvc
abstract class AbstractControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var objectMapper: ObjectMapper

    protected fun doGetRequest(url: String, expectedStatus: HttpStatus): MockHttpServletResponse {
        return mockMvc.get(url)
            .andExpect { status { expectedStatus } }
            .andReturn().response
    }

    protected fun doPostRequest(url: String, requestBody: String): MockHttpServletResponse {
        return mockMvc.post(url) {
            accept = MediaType.APPLICATION_JSON
            contentType = MediaType.APPLICATION_JSON
            content = requestBody
        }.andExpect {
            status { isCreated() }
            content { contentType(MediaType.APPLICATION_JSON) }
        }.andReturn().response
    }

    protected fun jsonFromResource(resource: Resource) = StreamUtils.copyToString(resource.inputStream, Charset.forName(Charsets.UTF_8.name()))
}