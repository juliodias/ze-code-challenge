package io.juliodias.ze.model

import java.io.Serializable
import java.time.ZonedDateTime
import java.util.UUID
import javax.persistence.Column
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.MappedSuperclass
import javax.persistence.PreUpdate

@MappedSuperclass
abstract class BaseEntity : Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    @Column(nullable = false)
    val externalId = UUID.randomUUID()

    @Column(nullable = false)
    val createdAt: ZonedDateTime = ZonedDateTime.now()

    @Column(nullable = false, updatable = true)
    var updatedAt: ZonedDateTime = ZonedDateTime.now()

    @PreUpdate
    private fun onUpdate() {
        this.updatedAt = ZonedDateTime.now()
    }
}