package cz.ackee.clu.architecture.model.api.ad

import com.squareup.moshi.JsonClass
import java.util.*

import cz.ackee.clu.architecture.model.repository.ad.Ad as DomainAd


/**
 * Ad retrieved from API
 */
@JsonClass(generateAdapter = true)
data class Ad(
    val id: Long,
    val name: String,
    val description: String,
    val creationDate: Long
)

fun Ad.toDomainAd(): DomainAd {
    return DomainAd(
        id,
        name,
        description,
        Date(creationDate)
    )
}