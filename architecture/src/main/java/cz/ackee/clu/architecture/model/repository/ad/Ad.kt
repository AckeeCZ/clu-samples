package cz.ackee.clu.architecture.model.repository.ad

import java.util.*

/**
 * Ad
 */
data class Ad(
    val id: Long,
    val name: String,
    val description: String,
    val creationDate: Date
)