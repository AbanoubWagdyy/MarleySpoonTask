package net.marleyspoon

import android.app.Application
import com.contentful.java.cda.CDAArray
import net.marleyspoon.di.remoteDataSourceModule
import net.marleyspoon.di.repositoryModule
import net.marleyspoon.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin


class App : Application() {

    internal lateinit var result: CDAArray

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@App.applicationContext)
            modules(listOf(remoteDataSourceModule, repositoryModule, viewModelModule))
        }

//        client.observe(CDAEntry::class.java)
//            .all()
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribeOn(Schedulers.io())
//            .subscribe(object : Subscriber<CDAArray> {
//                override fun onComplete() {
//                    for (resource in result.items()) {
//                        val entry = resource as CDAEntry
//                        Log.i("Contentful", entry.getField<Any>("productName").toString())
//                    }
//                }
//
//                override fun onSubscribe(s: Subscription?) {
//                }
//
//                override fun onError(error: Throwable) {
//                    Log.e("Contentful", "could not request entry", error)
//                }
//
//                override fun onNext(cdaArray: CDAArray) {
//                    result = cdaArray
//                }
//            })
    }
}