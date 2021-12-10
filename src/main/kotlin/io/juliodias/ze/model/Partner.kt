package io.juliodias.ze.model

import com.bedatadriven.jackson.datatype.jts.serialization.GeometryDeserializer
import com.bedatadriven.jackson.datatype.jts.serialization.GeometrySerializer
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.vividsolutions.jts.geom.Geometry
import com.vividsolutions.jts.geom.Point
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "partners")
data class Partner(
    @Column val address: Point,
    @Column val document: String,
    @Column val ownerName: String,
    @Column val tradingName: String,
    @Column val coverageArea: Geometry
) : BaseEntity() {

    fun toSkeleton(): PartnerSkeleton = PartnerSkeleton(address, document, ownerName, tradingName, coverageArea)
}

data class PartnerSkeleton (
    val address: Point,
    val document: String,
    val ownerName: String,
    val tradingName: String,
    @JsonSerialize(using = GeometrySerializer::class)
    @JsonDeserialize(using = GeometryDeserializer::class)
    val coverageArea: Geometry
) {

    fun toPartner(): Partner = Partner(address, document, ownerName, tradingName, coverageArea)
}