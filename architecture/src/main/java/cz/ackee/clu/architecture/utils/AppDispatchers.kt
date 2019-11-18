package cz.ackee.clu.architecture.utils

import androidx.annotation.VisibleForTesting
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

/**
 * Dispatchers for Coroutines that can be replaced in tests
 */
object AppDispatchers {

    var IO: CoroutineDispatcher = Dispatchers.IO
        @VisibleForTesting set

    var Main: CoroutineDispatcher = Dispatchers.Main
        @VisibleForTesting set
}