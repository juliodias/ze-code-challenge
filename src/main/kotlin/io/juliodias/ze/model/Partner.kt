package io.juliodias.ze.model

import com.bedatadriven.jackson.datatype.jts.serialization.GeometrySerializer
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.vividsolutions.jts.geom.MultiPolygon
import com.vividsolutions.jts.geom.Point
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "partners")
data class Partner(
    @Column val document: String,
    @Column val ownerName: String,
    @Column val tradingName: String,
    @Column val address: Point,
    @Column val coverageArea: MultiPolygon
) : BaseEntity() {

    fun toSkeleton(): PartnerSkeleton = PartnerSkeleton(document, ownerName, tradingName, address, coverageArea)
}

data class PartnerSkeleton (
    val document: String,
    val ownerName: String,
    val tradingName: String,
    @JsonSerialize(using = GeometrySerializer::class)
    val address: Point,
    @JsonSerialize(using = GeometrySerializer::class)
    val coverageArea: MultiPolygon
) {

    fun toPartner(): Partner = Partner(document, ownerName, tradingName, address, coverageArea)
}