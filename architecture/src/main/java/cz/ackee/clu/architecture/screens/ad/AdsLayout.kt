package cz.ackee.clu.architecture.screens.ad

import android.content.Context
import android.view.View
import cz.ackee.extensions.anko.layout.ViewLayout
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.textView

/**
 * Layout of [AdsFragment]
 */
class AdsLayout(ctx: Context) : ViewLayout(ctx) {

    override fun createView(ui: AnkoContext<Context>): View {
        return with(ui) {
            textView()
        }
    }
}