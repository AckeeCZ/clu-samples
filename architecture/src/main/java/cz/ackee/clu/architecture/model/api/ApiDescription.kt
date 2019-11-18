package cz.ackee.clu.architecture.model.api

import cz.ackee.clu.architecture.model.api.ad.Ad
import retrofit2.http.GET

/**
 * Description of REST Api for Retrofit
 */
interface ApiDescription {

    @GET("ads")
    suspend fun getAds(): List<Ad>
}