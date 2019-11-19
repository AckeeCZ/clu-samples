package cz.ackee.clu.architecture.screens.ad.epoxy

import android.content.Context
import android.view.View
import android.widget.TextView
import cz.ackee.extensions.anko.layout.ViewLayout
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.textView
import org.jetbrains.anko.verticalLayout

/**
 * View layout of ad item in list
 */
class AdLayout(ctx: Context) : ViewLayout(ctx) {

    lateinit var txtName: TextView

    lateinit var txtDescription: TextView

    override fun createView(ui: AnkoContext<Context>): View {
        return with(ui) {
            verticalLayout {
                txtName = textView()
                txtDescription = textView()
            }
        }
    }
}