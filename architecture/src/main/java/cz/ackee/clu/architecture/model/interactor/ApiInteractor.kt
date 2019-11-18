package cz.ackee.clu.architecture.model.interactor

import cz.ackee.clu.architecture.model.repository.ad.Ad

/**
 * Interactor that communicates with API
 */
interface ApiInteractor {

    suspend fun getAds(): List<Ad>
}