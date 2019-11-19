package cz.ackee.clu.architecture.screens.ad

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.airbnb.epoxy.EpoxyRecyclerView
import cz.ackee.clu.architecture.utils.epoxyRecyclerView
import cz.ackee.extensions.anko.layout.ViewLayout
import org.jetbrains.anko.AnkoContext

/**
 * Layout of [AdsFragment]
 */
class AdsLayout(ctx: Context) : ViewLayout(ctx) {

    lateinit var epoxyRecycler: EpoxyRecyclerView

    override fun createView(ui: AnkoContext<Context>): View {
        return with(ui) {
            epoxyRecyclerView {
                layoutManager = LinearLayoutManager(context)
            }.also { epoxyRecycler = it }
        }
    }
}