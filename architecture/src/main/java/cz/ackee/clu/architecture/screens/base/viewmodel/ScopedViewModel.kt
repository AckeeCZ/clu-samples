package cz.ackee.clu.architecture.screens.base.viewmodel

import cz.ackee.clu.architecture.utils.AppDispatchers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

/**
 * ViewModel with support for Coroutine Scope
 */
abstract class ScopedViewModel : RxAwareViewModel(), CoroutineScope {

    private val job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + AppDispatchers.Main

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}