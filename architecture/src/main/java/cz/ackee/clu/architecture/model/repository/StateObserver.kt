package cz.ackee.clu.architecture.model.repository

import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject

// Observers of [State]

/**
 * Class responsible for propagating [State] of some loading and exposing [Observable] that is
 * emitted every time state changes.
 */
open class StateObserver<T>(default: State<T> = State.Idle) {

    private val stateSubject: BehaviorSubject<State<T>> = BehaviorSubject.createDefault<State<T>>(default)

    val currentState: State<T>
        get() = stateSubject.value!!

    fun loaded(data: T) {
        stateSubject.onNext(State.Loaded(data))
    }

    fun loading() {
        stateSubject.onNext(State.Loading)
    }

    fun error(err: Throwable) {
        stateSubject.onNext(State.Error(err))
    }

    fun empty() {
        stateSubject.onNext(State.Empty)
    }

    fun idle() {
        stateSubject.onNext(State.Idle)
    }

    fun observeState() = stateSubject.hide()
}

/**
 * [StateObserver] that has no value type and therefore is emulated by [Unit] type
 */
class NoValueStateObserver : StateObserver<Unit>() {

    fun loaded() {
        loaded(Unit)
    }
}