package cz.ackee.clu.architecture.screens.layout

import android.content.Context
import android.view.View
import android.view.ViewGroup
import cz.ackee.clu.architecture.R
import cz.ackee.extensions.android.attrDimen
import cz.ackee.extensions.anko.layout.ViewLayout
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.appcompat.v7.themedToolbar
import org.jetbrains.anko.backgroundResource
import org.jetbrains.anko.matchParent

/**
 * UI component of toolbar
 */
class ToolbarLayout(parent: ViewGroup) : ViewLayout(parent) {

    override fun createView(ui: AnkoContext<Context>): View {
        return with(ui) {
            themedToolbar(theme = R.style.ToolbarTheme) {
                backgroundResource = R.color.primary
                id = R.id.toolbar
                layoutParams = ViewGroup.LayoutParams(matchParent, ctx.attrDimen(R.attr.actionBarSize))
            }
        }
    }
}