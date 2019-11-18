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

    private val adsStateObserver = StateObserver<List<Ad>>()

    init {
        launch {
            adsStateObserver.loading()
            try {
                adsStateObserver.loaded(adRepository.getAds())
            } catch (e: Exception) {
                adsStateObserver.error(e)
            }
        }
    }

    fun observeAdsState() = adsStateObserver.observeState()
}