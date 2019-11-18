package cz.ackee.clu.architecture.di

import cz.ackee.clu.architecture.model.interactor.ApiInteractor
import cz.ackee.clu.architecture.model.interactor.ApiInteractorImpl
import org.koin.dsl.module

val remoteModule = module {
    single<ApiInteractor>{ ApiInteractorImpl(get()) }
}