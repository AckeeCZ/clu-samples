package cz.ackee.clu.architecture.screens.ad

import cz.ackee.clu.architecture.model.repository.StateObserver
import cz.ackee.clu.architecture.model.repository.ad.Ad
import cz.ackee.clu.architecture.model.repository.ad.AdRepository
import cz.ackee.clu.architecture.screens.base.viewmodel.ScopedViewModel
import kotlinx.coroutines.launch

/**
 * ViewModel of [AdsFragment]
 */
class AdsViewModel(private val adRepository: AdRepository) : ScopedViewModel() {

    private val adsStateObserver = StateObserver<List<AdsWithTitle>>()

    init {
        launch {
            adsStateObserver.loading()
            try {
                adsStateObserver.loaded(adRepository.getAds().mapToAdsWithTitle())
            } catch (e: Exception) {
                adsStateObserver.error(e)
            }
        }
    }

    private fun List<Ad>.mapToAdsWithTitle(): List<AdsWithTitle> {
        return chunked(3).mapIndexed { index, ads -> AdsWithTitle("Title ${index + 1}", ads) }
    }

    fun observeAdsState() = adsStateObserver.observeState()
}