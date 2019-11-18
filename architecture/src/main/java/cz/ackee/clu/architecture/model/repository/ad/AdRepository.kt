package cz.ackee.clu.architecture.model.repository.ad

import cz.ackee.clu.architecture.model.interactor.ApiInteractor

/**
 * Repository of ads
 */
interface AdRepository {

    suspend fun getAds(): List<Ad>
}

class AdRepositoryImpl(private val apiInteractor: ApiInteractor) : AdRepository {

    override suspend fun getAds() = apiInteractor.getAds()
}