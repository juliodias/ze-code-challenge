package io.juliodias.ze.model

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
): BaseEntity()

data class PartnerSkeleton(
    val address: Point,
    val document: String,
    val ownerName: String,
    val tradingName: String,
    val coverageArea: Geometry
)