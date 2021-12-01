package io.juliodias.ze.service

import io.juliodias.ze.exception.PartnerAlreadyExistsException
import io.juliodias.ze.model.Partner
import io.juliodias.ze.model.PartnerSkeleton
import io.juliodias.ze.repository.PartnerRepository
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

    companion object {
        private val logger: Logger = LogManager.getLogger()
    }
}