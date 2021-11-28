package io.juliodias.ze.repository

import io.juliodias.ze.model.Partner
import org.springframework.data.jpa.repository.JpaRepository

interface PartnerRepository : JpaRepository<Partner, Long>