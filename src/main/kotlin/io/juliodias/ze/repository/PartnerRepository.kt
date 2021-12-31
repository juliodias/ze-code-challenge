package io.juliodias.ze.repository

import io.juliodias.ze.model.Partner
import java.util.UUID
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface PartnerRepository : JpaRepository<Partner, Long> {

    fun existsByDocument(document: String): Boolean

    fun findByExternalId(externalId: UUID): Partner?

    @Query("SELECT *, ST_Distance(ST_MakePoint(:longitude, :latitude), coverage_area) AS dist FROM partners ORDER BY dist LIMIT 1", nativeQuery = true)
    fun findNearestPartner(@Param("latitude") latitude: Double, @Param("longitude") longitude: Double): Partner?
}