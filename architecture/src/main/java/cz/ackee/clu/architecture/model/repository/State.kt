package cz.ackee.clu.architecture.model.repository

/**
 * Base state that can be maintained by any view model
 */
sealed class State<out T> {

    object Loading : State<Nothing>()
    object Empty : State<Nothing>()
    object Idle : State<Nothing>()
    abstract class ScreenState<T> : State<T>()
    data class Error(val error: Throwable) : State<Nothing>()
    data class Loaded<out T>(val data: T) : State<T>()
    data class Reloading<out T>(val previousData: T?) : State<T>()
}