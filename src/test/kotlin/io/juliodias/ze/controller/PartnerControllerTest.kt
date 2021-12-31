package io.juliodias.ze.controller

import io.juliodias.ze.exception.ErrorResponse
import io.juliodias.ze.exception.PartnerErrorResponse
import io.juliodias.ze.model.PartnerSkeleton
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.Resource
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.post

class PartnerControllerTest : AbstractControllerTest() {

    @Value("classpath:data/new-partner.json")
    lateinit var newPartnerJson: Resource

    @Value("classpath:data/duplicated-partner.json")
    lateinit var duplicatedPartnerJson: Resource

    @Test
    fun `Endpoint should create new partner`() {
        val partnerRequest = jsonFromResource(newPartnerJson)
        val response = doPostRequest(PARTNER_ENDPOINT, partnerRequest)

        val partnerSkeleton = objectMapper.readValue(response.contentAsString, PartnerSkeleton::class.java)
        assertThat(partnerSkeleton.ownerName).isEqualTo("Nelson Rodrigues")
        assertThat(partnerSkeleton.document).isEqualTo("36.096.143/0001-96")
        assertThat(partnerSkeleton.tradingName).isEqualTo("Bar do Nelson")
    }

    @Test
    fun `Duplicated partner should return bad request`() {
        val partnerRequest = jsonFromResource(duplicatedPartnerJson)
        val response = mockMvc.post(PARTNER_ENDPOINT) {
            contentType = MediaType.APPLICATION_JSON
            content = partnerRequest
        }.andExpect {
            status { isBadRequest() }
        }.andReturn().response

        val responseBody = response.contentAsString
        val partnerErrorResponse = objectMapper.readValue(responseBody, PartnerErrorResponse::class.java)
        assertThat(partnerErrorResponse.error).isEqualTo(ErrorResponse.PARTNER_ALREADY_EXISTS)
        assertThat(partnerErrorResponse.description).isEqualTo("This partner already registed")
    }

    @Test
    fun `Endpoint should return No Content when partner doesn't exist`() {
        val url = "$PARTNER_ENDPOINT/76328bf9-47e3-454c-aa45-b93dc77004d0"

        val response = doGetRequest(url, HttpStatus.NO_CONTENT)
        assertThat(response.contentAsString).isBlank
    }

    @Test
    fun `Endpoint should list partner found by existing uuid`() {
        val url = "$PARTNER_ENDPOINT/a0638bd7-cc97-4649-90e5-102ed1a727fb"

        val response = doGetRequest(url, HttpStatus.OK)
        val partnerSkeleton = objectMapper.readValue(response.contentAsString, PartnerSkeleton::class.java)
        assertThat(partnerSkeleton.ownerName).isEqualTo("Zezinho")
        assertThat(partnerSkeleton.document).isEqualTo("1432132123891/0001")
        assertThat(partnerSkeleton.tradingName).isEqualTo("Adeguinha do Zezinho")
    }

    companion object {
        private const val PARTNER_ENDPOINT = "/partners"
    }

}