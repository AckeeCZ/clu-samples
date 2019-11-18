package cz.ackee.clu.architecture.model.interactor

import cz.ackee.clu.architecture.model.api.ApiDescription
import cz.ackee.clu.architecture.model.api.ad.toDomainAd
import cz.ackee.clu.architecture.model.repository.ad.Ad

/**
 * [ApiInteractor] implementation
 */
class ApiInteractorImpl(private val apiDescription: ApiDescription) : ApiInteractor {

    override suspend fun getAds(): List<Ad> {
        return apiDescription.getAds().map { it.toDomainAd() }
    }
}