package io.juliodias.ze.controller

import io.juliodias.ze.model.PartnerSkeleton
import io.juliodias.ze.service.PartnerService
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.core.io.Resource

@WebMvcTest
class PartnerControllerTest : AbstractControllerTest() {

    @Value("classpath:data/new-partner.json")
    lateinit var newPartnerJson: Resource

    @MockBean
    lateinit var partnerService: PartnerService

    @Test
    fun `Endpoint should create new partner`() {
        val newPartner = jsonFromResource(newPartnerJson)
        val response = doPostRequest(PARTNER_ENDPOINT, newPartner)

        val partnerSkeleton = objectMapper.convertValue(response.contentAsString, PartnerSkeleton::class.java)
    }

    companion object {
        private const val PARTNER_ENDPOINT = "/partners"
    }

}