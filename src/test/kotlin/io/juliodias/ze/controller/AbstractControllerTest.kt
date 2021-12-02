package io.juliodias.ze.controller

import com.fasterxml.jackson.databind.ObjectMapper
import java.nio.charset.Charset
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.Resource
import org.springframework.http.MediaType
import org.springframework.mock.web.MockHttpServletResponse
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post
import org.springframework.util.StreamUtils

abstract class AbstractControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var objectMapper: ObjectMapper

    protected fun doPostRequest(url: String, requestBody: String): MockHttpServletResponse {
        val response = mockMvc.post(url) {
            contentType = MediaType.APPLICATION_JSON
            content = requestBody
        }.andExpect {
            status { isCreated() }
            content { contentType(MediaType.APPLICATION_JSON) }
        }.andReturn().response
        return response
    }

    protected fun jsonFromResource(resource: Resource): String {
        return StreamUtils.copyToString(resource.inputStream, Charset.forName(Charsets.UTF_8.name()))
    }
}