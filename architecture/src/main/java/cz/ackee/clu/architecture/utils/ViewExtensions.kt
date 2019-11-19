package cz.ackee.clu.architecture.utils

import android.view.ViewManager
import com.airbnb.epoxy.EpoxyRecyclerView
import org.jetbrains.anko.AnkoViewDslMarker
import org.jetbrains.anko.custom.ankoView

inline fun ViewManager.epoxyRecyclerView(init: (@AnkoViewDslMarker EpoxyRecyclerView).() -> Unit = {}): EpoxyRecyclerView {
    return ankoView({ context -> EpoxyRecyclerView(context) }, theme = 0) { init() }
}