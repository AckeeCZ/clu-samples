package cz.ackee.clu.architecture.screens.ad

import cz.ackee.clu.architecture.model.repository.ad.Ad

/**
 * Encapsulates ads with title
 */
data class AdsWithTitle(
    val title: String,
    val ads: List<Ad>
)