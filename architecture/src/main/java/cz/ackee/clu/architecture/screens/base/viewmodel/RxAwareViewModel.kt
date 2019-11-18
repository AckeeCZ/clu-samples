package cz.ackee.clu.architecture.screens.base.viewmodel

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

/**
 * ViewModel that automatically dispose its [disposables]
 */
abstract class RxAwareViewModel : ViewModel() {

    protected var disposables = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}