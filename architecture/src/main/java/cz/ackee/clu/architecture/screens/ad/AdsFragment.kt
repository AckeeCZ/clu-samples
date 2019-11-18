package cz.ackee.clu.architecture.screens.ad

import android.content.Context
import android.os.Bundle
import android.view.View
import cz.ackee.clu.architecture.model.repository.State
import cz.ackee.clu.architecture.model.repository.ad.Ad
import cz.ackee.clu.architecture.screens.base.fragment.BaseFragment
import cz.ackee.extensions.rx.observeOnMainThread
import io.reactivex.rxkotlin.plusAssign
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Screen displaying list of ads
 */
class AdsFragment : BaseFragment<AdsLayout>() {

    private val viewModel: AdsViewModel by viewModel()

    override fun createLayout(parent: Context) = AdsLayout(parent)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        disposables += viewModel.observeAdsState()
            .observeOnMainThread()
            .subscribe {
                onLoading(it is State.Loading)

                when (it) {
                    is State.Loaded -> onSuccess(it.data)
                    is State.Error -> onError(it.error)
                }
            }
    }

    private fun onLoading(isLoading: Boolean) {
        // display/hide loading
    }

    private fun onSuccess(ads: List<Ad>) {
        // display data
    }

    private fun onError(e: Throwable) {
        // display error
    }
}