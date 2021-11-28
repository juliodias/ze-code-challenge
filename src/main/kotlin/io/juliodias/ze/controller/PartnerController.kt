package io.juliodias.ze.controller

import io.juliodias.ze.model.PartnerSkeleton
import org.apache.logging.log4j.LogManager
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("partners")
class PartnerController {

    @PostMapping
    fun create(@RequestBody partnerSkeleton: PartnerSkeleton): ResponseEntity<String> {
        logger.info("Request to create partner: $partnerSkeleton")
        return ResponseEntity.ok("Hello world!")
    }

    companion object {
        private val logger = LogManager.getLogger()
    }
}