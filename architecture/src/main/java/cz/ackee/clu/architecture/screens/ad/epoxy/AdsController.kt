package cz.ackee.clu.architecture.screens.ad.epoxy

import com.airbnb.epoxy.TypedEpoxyController
import cz.ackee.clu.architecture.screens.ad.AdsWithTitle

/**
 * Epoxy controller of [Ad]s
 */
class AdsController : TypedEpoxyController<List<AdsWithTitle>>() {

    override fun buildModels(ads: List<AdsWithTitle>?) {
        ads?.forEachIndexed { i, data ->
            sectionTitle {
                id(i)
                sectionTitle(data.title)
            }
            data.ads.forEach {
                ad {
                    id(it.id)
                    ad(it)
                }
            }
        }
    }
}