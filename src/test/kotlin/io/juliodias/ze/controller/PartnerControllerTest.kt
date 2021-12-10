package io.juliodias.ze.controller

import io.juliodias.ze.model.PartnerSkeleton
import io.juliodias.ze.service.PartnerService
import java.util.UUID
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.core.io.Resource
import org.springframework.http.HttpStatus

@WebMvcTest
class PartnerControllerTest : AbstractControllerTest() {

    @Value("classpath:data/new-partner.json")
    lateinit var newPartnerJson: Resource

    @MockBean
    lateinit var partnerService: PartnerService

    @Test
    fun `Endpoint should create new partner`() {
        val partnerRequest = jsonFromResource(newPartnerJson)
        val response = doPostRequest(PARTNER_ENDPOINT, partnerRequest)

        val partnerSkeleton = objectMapper.convertValue(response.contentAsString, PartnerSkeleton::class.java)
        Assertions.assertThat(partnerSkeleton.ownerName).isEqualTo("Zé da Silva")
        Assertions.assertThat(partnerSkeleton.document).isEqualTo("1432132123891/0001")
        Assertions.assertThat(partnerSkeleton.tradingName).isEqualTo("Adega da Cerveja - Pinheiros")
    }

    @Test
    fun `Endpoint should return No Content when partner doesn't exist`() {
        val url = "$PARTNER_ENDPOINT/${UUID.randomUUID()}"

        val response = doGetRequest(url, HttpStatus.NO_CONTENT)

        Assertions.assertThat(response.contentAsString).isBlank
    }

    @Test
    fun `Endpoint should list partner found by existing uuid`() {
        val url = "$PARTNER_ENDPOINT/a0638bd7-cc97-4649-90e5-102ed1a727fb"

        val response = doGetRequest(url, HttpStatus.OK)

        val partnerSkeleton = objectMapper.convertValue(response.contentAsString, PartnerSkeleton::class.java)
        Assertions.assertThat(partnerSkeleton.ownerName).isEqualTo("Zezinho")
        Assertions.assertThat(partnerSkeleton.document).isEqualTo("1432132123891/0001")
        Assertions.assertThat(partnerSkeleton.tradingName).isEqualTo("Adeguinha do Zezinho")
    }

    companion object {
        private const val PARTNER_ENDPOINT = "/partners"
    }

}