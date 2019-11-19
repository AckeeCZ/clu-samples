package cz.ackee.clu.architecture.screens.ad.epoxy

import android.view.ViewGroup
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import cz.ackee.clu.architecture.model.repository.ad.Ad
import cz.ackee.extensions.epoxy.EpoxyModelWithLayout

/**
 * Epoxy model of [AdLayout]
 */
@EpoxyModelClass
abstract class AdModel : EpoxyModelWithLayout<AdLayout>() {

    @EpoxyAttribute
    lateinit var ad: Ad

    override fun createViewLayout(parent: ViewGroup) = AdLayout(parent.context)

    override fun AdLayout.bind() {
        txtName.text = ad.name
        txtDescription.text = ad.description
    }
}