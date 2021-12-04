package io.juliodias.ze.repository

import io.juliodias.ze.model.Partner
import java.util.UUID
import org.springframework.data.jpa.repository.JpaRepository

interface PartnerRepository : JpaRepository<Partner, Long> {

    fun existsByDocument(document: String): Boolean

    fun findByExternalId(externalId: UUID): Partner?
}