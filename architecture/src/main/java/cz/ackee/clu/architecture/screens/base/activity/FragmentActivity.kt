package cz.ackee.clu.architecture.screens.base.activity

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout.LayoutParams
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import cz.ackee.clu.architecture.R
import cz.ackee.clu.architecture.screens.base.fragment.BaseFragment
import cz.ackee.clu.architecture.screens.layout.FragmentContainerLayout
import cz.ackee.clu.architecture.screens.layout.ToolbarLayout
import cz.ackee.extensions.android.attrDimen
import cz.ackee.extensions.anko.layout.customLayout
import org.jetbrains.anko.UI
import org.jetbrains.anko.frameLayout
import org.jetbrains.anko.matchParent

/**
 * Activity that contains logic for operating with fragments. It can be created with the name of fragment that is initially shown
 * and has ability to easily replace fragments in view container.
 *
 * Note: If you need activity that does not have fragment or has some different navigation logic (viewpager,...) just inherit from NaviAppCompatActivity but dont forget to use
 * [CommonActivityLogicExtension].
 */
open class FragmentActivity : AppCompatActivity() {

    companion object {
        val CONTENT_VIEW_ID = R.id.fragment_container
        internal val KEY_EXTRA_FRAGMENT_NAME = "fragment_name"
        internal val KEY_EXTRA_FRAGMENT_ARGUMENTS = "fragment_arguments"
        internal val KEY_EXTRA_PROVIDE_TOOLBAR = "provide_toolbar"
    }

    /**
     * Find toolbar that has to have specific id R.id.toolbar
     */
    val toolbar: Toolbar?
        get() = findViewById(R.id.toolbar)

    /**
     * Get currently displayed fragment
     */
    val currentFragment: Fragment?
        get() = supportFragmentManager.findFragmentById(CONTENT_VIEW_ID)

    /**
     * Returns the name of the fragment to be instantiated.
     *
     * Note: If you will inherit from FragmentActivity and do not provide fragment Name via intent or
     * with overriding this attribute, exception will be thrown
     */
    open val fragmentName: String?
        get() = intent?.getStringExtra(KEY_EXTRA_FRAGMENT_NAME)

    /**
     * If activity contains toolbar or fragment has its own toolbar
     */
    open val provideToolbar
        get() = intent?.getBooleanExtra(KEY_EXTRA_PROVIDE_TOOLBAR, true) ?: true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = onCreateContentView()
        setContentViewInternal(view, LayoutParams(matchParent, matchParent))
        if (provideToolbar) {
            initToolbar()
        }
        val fragmentName = fragmentName ?: throw IllegalStateException("Fragment name must be provided")
        val args = intent?.getBundleExtra(KEY_EXTRA_FRAGMENT_ARGUMENTS)

        var fragment: Fragment? = supportFragmentManager.findFragmentByTag(fragmentName)
        if (fragment == null && savedInstanceState == null) {
            fragment = Fragment.instantiate(this, fragmentName)
            if (args != null) {
                fragment.arguments = args
            }
            supportFragmentManager.beginTransaction().add(CONTENT_VIEW_ID, fragment, fragment.javaClass.name).commit()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        currentFragment?.onActivityResult(requestCode, resultCode, data)
    }

    override fun onBackPressed() {
        //back press is not working if fragment handle this callback on its own
        if (currentFragment == null || !(currentFragment as BaseFragment<*>).overrideOnBackPressed()) {
            super.onBackPressed()
        }
    }

    /**
     * Replace fragment in content view with [fragment] provided in property
     * @param fragment       fragment for container to be replaced with
     * @param name           of the transaction, null if not needed
     * @param addToBackStack if fragment will be added to backstack or notl
     * @param transition transition animation between new and old fragment
     */
    @JvmOverloads
    fun replaceFragment(fragment: Fragment, name: String = fragment.javaClass.name, addToBackStack: Boolean = true,
        transition: Int = FragmentTransaction.TRANSIT_FRAGMENT_OPEN) {
        val transaction = supportFragmentManager.beginTransaction().replace(CONTENT_VIEW_ID, fragment, fragment.javaClass.name)
        if (addToBackStack) {
            transaction.addToBackStack(name)
        }
        transaction.setTransition(transition).commit()
    }

    /**
     * Method that creates content view with fragment container. Container must have id set to R.id.fragmentContainer
     */
    protected open fun onCreateContentView(): View {
        return UI {
            frameLayout {
                layoutParams = ViewGroup.LayoutParams(matchParent, matchParent)

                val container = customLayout<FragmentContainerLayout>()

                if (provideToolbar) {
                    customLayout<ToolbarLayout>()
                    // add top margin to fragment container if toolbar is provided
                    container.view.lparams(matchParent, matchParent) {
                        topMargin = attrDimen(R.attr.actionBarSize)
                    }
                }
            }
        }.view
    }

    /**
     * Init activity actionbar as toolbar from layout
     */
    protected fun initToolbar() {
        setSupportActionBar(findViewById(R.id.toolbar))
    }

    protected fun setContentViewInternal(view: View?, params: ViewGroup.LayoutParams) {
        setContentView(view, params)
    }
}

/**
 * Extension to Activity that starts activity that is child of FragmentActivity and set activity properties
 */
internal inline fun <reified T : FragmentActivity> Activity.startFragmentActivity(
    fragmentName: String? = null,
    fragmentArgs: Bundle? = null,
    activityBundle: Bundle? = null,
    provideToolbar: Boolean = true) {
    startActivity(getFragmentActivityIntent<T>(this, fragmentName, fragmentArgs, activityBundle, provideToolbar))
}

/**
 * Extension to Fragment that starts activity that is child of FragmentActivity and set activity properties
 */
internal inline fun <reified T : FragmentActivity> Fragment.startFragmentActivity(
    fragmentName: String? = null,
    fragmentArgs: Bundle? = null,
    activityBundle: Bundle? = null,
    provideToolbar: Boolean = true) {
    startActivity(getFragmentActivityIntent<T>(activity, fragmentName, fragmentArgs, activityBundle, provideToolbar))
}

/**
 * Extension to Activity that starts activity  that is child of FragmentActivity and set activity properties and expects result
 */
internal inline fun <reified T : FragmentActivity> Activity.startFragmentActivityForResult(
    fragmentName: String? = null,
    fragmentArgs: Bundle? = null,
    activityBundle: Bundle? = null,
    provideToolbar: Boolean = true,
    requestCode: Int) {
    startActivityForResult(getFragmentActivityIntent<T>(this, fragmentName, fragmentArgs, activityBundle, provideToolbar), requestCode)
}

/**
 * Extension to Fragment that starts activity  that is child of FragmentActivity and set activity properties and expects result
 */
internal inline fun <reified T : FragmentActivity> Fragment.startFragmentActivityForResult(
    fragmentName: String? = null,
    fragmentArgs: Bundle? = null,
    activityBundle: Bundle? = null,
    provideToolbar: Boolean = true,
    requestCode: Int) {
    startActivityForResult(getFragmentActivityIntent<T>(activity, fragmentName, fragmentArgs, activityBundle, provideToolbar), requestCode)
}

/**
 * Get intent with properties for starting of FragmentActivity
 */
internal inline fun <reified T : FragmentActivity> getFragmentActivityIntent(ctx: Context?, fragmentName: String? = null,
    fragmentArgs: Bundle? = null,
    activityBundle: Bundle? = null,
    provideToolbar: Boolean = true): Intent {
    val intent: Intent = Intent(ctx, T::class.java).provideToolbar(provideToolbar)
    intent.putExtra(FragmentActivity.KEY_EXTRA_FRAGMENT_NAME, fragmentName)
    intent.putExtra(FragmentActivity.KEY_EXTRA_FRAGMENT_ARGUMENTS, fragmentArgs)
    if (activityBundle != null) {
        intent.putExtras(activityBundle)
    }
    return intent
}

/**
 * Extension to intent that sets property if activity provides toolbar or delegates providing toolbar to fragments
 */
internal fun Intent.provideToolbar(ownsToolbar: Boolean): Intent {
    return putExtra(FragmentActivity.KEY_EXTRA_PROVIDE_TOOLBAR, ownsToolbar)
}