package cz.ackee.clu.architecture.di

import cz.ackee.clu.architecture.model.repository.ad.AdRepository
import cz.ackee.clu.architecture.model.repository.ad.AdRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<AdRepository> { AdRepositoryImpl(get()) }
}