package cz.ackee.clu.architecture.screens.ad.epoxy

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import cz.ackee.extensions.anko.layout.ViewLayout
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.textView

/**
 * Layout of section title
 */
class SectionTitleLayout(parent: ViewGroup) : ViewLayout(parent) {

    lateinit var txtSectionTitle: TextView

    override fun createView(ui: AnkoContext<Context>): View {
        return with(ui) {
            textView().also { txtSectionTitle = it }
        }
    }
}