package cz.ackee.clu.architecture.screens.base.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.appcompat.app.ActionBar
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import cz.ackee.clu.architecture.R
import cz.ackee.clu.architecture.screens.base.activity.FragmentActivity
import cz.ackee.clu.architecture.utils.AppDispatchers
import cz.ackee.extensions.android.hideIme
import cz.ackee.extensions.anko.layout.ViewLayout
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import org.jetbrains.anko.appcompat.v7.toolbar
import kotlin.coroutines.CoroutineContext

/**
 * Fragment from which all applications fragment should inherit
 */
abstract class BaseFragment<T : ViewLayout> : Fragment(), CoroutineScope {

    val fragmentActivity: FragmentActivity?
        get() = activity as? FragmentActivity

    /**
     * Indicator if this fragment has its own toolbar and does not use toolbar in activity. Useful
     * when multiple fragments with different kinds of toolbars are used in the same activity
     */
    open var provideToolbar: Boolean = false
    protected var disposables = CompositeDisposable()

    private val job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + AppDispatchers.Main

    protected val toolbar: Toolbar?
        get() {
            // if fragment is providing its own toolbar, return this one otherwise toolbar from activity
            if (provideToolbar) {
                return providedToolbar
            }
            return fragmentActivity?.toolbar()
        }

    open val providedToolbar: Toolbar?
        get() {
            return view?.findViewById(R.id.toolbar)
        }

    protected lateinit var layout: T

    init {
        if (arguments == null) {
            arguments = Bundle()
        }
    }

    abstract fun createLayout(parent: Context): T

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return createLayout(container!!.context).also { layout = it }.view
    }

    open fun T.viewCreated(savedState: Bundle?) = Unit

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        layout.viewCreated(savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setHasOptionsMenu(true)
        if (provideToolbar) {
            fragmentActivity?.setSupportActionBar(toolbar)
        }
        onInitActionBar(fragmentActivity?.supportActionBar, toolbar)
    }

    /**
     * Method that is called when actionbar (toolbar) should be initialized for this fragment
     *
     * @param actionBar of activity
     * @param toolbar   representation of ActionBar
     */
    open fun onInitActionBar(actionBar: ActionBar?, toolbar: Toolbar?) {
    }

    /**
     * Basic settings of actionbar. You should modify this with project specific settings
     */
    protected fun baseActionBarSettings(actionBar: ActionBar?) {
        actionBar?.setDisplayShowTitleEnabled(true)
        actionBar?.setDisplayShowHomeEnabled(true)
        actionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onResume() {
        super.onResume()
        setTitle(getTitle())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        hideIme()
        disposables.clear()
        job.cancel()
    }

    /**
     * Indicator if fragment handles [android.app.Activity.onBackPressed] call from activity. Useful for form fragments when
     * you want to show user warning dialog when back button is pressed
     */
    fun overrideOnBackPressed(): Boolean {
        return false
    }

    /**
     * Hides keyboard if it is shown
     */
    protected fun hideIme() {
        // try to hide keyboard when destroying fragment view
        view?.findFocus()?.hideIme()
    }

    /**
     * Set title of toolbar
     */
    protected fun setTitle(@StringRes title: Int) {
        setTitle(getString(title))
    }

    /**
     * Set title of toolbar
     */
    protected fun setTitle(title: String?) {
        // intentional not-safe way to call activity, we want to crash an app when activity is null because
        // we are probably doing something bad
        activity!!.title = title
    }

    /**
     * Get title of screen. Children should override this if they want custom title
     */
    open fun getTitle(): String {
        return ""
    }
}
