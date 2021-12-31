package io.juliodias.ze.controller

import io.juliodias.ze.model.PartnerSkeleton
import io.juliodias.ze.service.PartnerService
import java.util.UUID
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.support.ServletUriComponentsBuilder

@RestController
@RequestMapping("partners")
class PartnerController(val partnerService: PartnerService) {

    @PostMapping
    fun create(@RequestBody partnerSkeleton: PartnerSkeleton): ResponseEntity<PartnerSkeleton> {
        val partner = partnerService.createPartner(partnerSkeleton)
        val location = ServletUriComponentsBuilder.fromCurrentRequestUri()
            .buildAndExpand("{externalId}", UUID.randomUUID())
            .toUri()
        return ResponseEntity.created(location).body(partner.toSkeleton())
    }

    @GetMapping("{externalId}")
    fun listPartner(@PathVariable externalId: String): ResponseEntity<PartnerSkeleton> {
        val partner = partnerService.getPartner(externalId)
            ?: return ResponseEntity.noContent().build()
        return ResponseEntity.ok(partner.toSkeleton())
    }

    @GetMapping
    fun listNearestPartnerByCoordinates(@RequestParam lat: Double, @RequestParam long: Double): ResponseEntity<PartnerSkeleton> {
        val partner = partnerService.getNearestPartner(lat, long)
            ?: return ResponseEntity.noContent().build()
        return ResponseEntity.ok(partner.toSkeleton())
    }
}