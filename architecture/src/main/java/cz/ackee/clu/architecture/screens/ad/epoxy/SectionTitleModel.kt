package cz.ackee.clu.architecture.screens.ad.epoxy

import android.view.ViewGroup
import com.airbnb.epoxy.EpoxyAttribute
import com.airbnb.epoxy.EpoxyModelClass
import cz.ackee.extensions.epoxy.EpoxyModelWithLayout

/**
 * Epoxy model of [SectionTitleLayout]
 */
@EpoxyModelClass
abstract class SectionTitleModel : EpoxyModelWithLayout<SectionTitleLayout>() {

    @EpoxyAttribute
    lateinit var sectionTitle: String

    override fun createViewLayout(parent: ViewGroup) = SectionTitleLayout(parent)

    override fun SectionTitleLayout.bind() {
        txtSectionTitle.text = sectionTitle
    }
}