package io.juliodias.ze.service

import io.juliodias.ze.exception.PartnerAlreadyExistsException
import io.juliodias.ze.model.Partner
import io.juliodias.ze.model.PartnerSkeleton
import io.juliodias.ze.repository.PartnerRepository
import java.util.UUID
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import org.springframework.stereotype.Service

@Service
class PartnerService(val partnerRepository: PartnerRepository) {

    fun createPartner(partnerSkeleton: PartnerSkeleton): Partner {
        val document = partnerSkeleton.document
        if (partnerRepository.existsByDocument(document)) {
            throw PartnerAlreadyExistsException(document)
        }
        val partner = partnerSkeleton.toPartner()
        val newPartner = partnerRepository.save(partner)
        logger.info("New partner created with success: $newPartner")
        return newPartner
    }

    fun getPartner(externalId: String): Partner? {
        val partner = partnerRepository.findByExternalId(UUID.fromString(externalId))
        logger.info("Finding for partner with external id: $externalId")
        return partner
    }

    fun getNearestPartner(latitude: Double, longitude: Double): Partner? {
        val partner = partnerRepository.findNearestPartner(latitude, longitude)
        logger.info("Finding for nearest partner around this coordinates (latitude: $latitude, longitude: $longitude)")
        return partner
    }

    companion object {
        private val logger: Logger = LogManager.getLogger()
    }
}