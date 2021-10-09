package com.qoiu.holybibleapp.sl

import android.content.Context
import com.google.gson.Gson
import com.qoiu.holybibleapp.core.RealmProvider
import com.qoiu.holybibleapp.core.ResourceProvider
import com.qoiu.holybibleapp.presentation.Navigator
import com.qoiu.holybibleapp.presentation.book.BookCache
import com.qoiu.holybibleapp.presentation.main.MainViewModel
import com.qoiu.holybibleapp.presentation.main.NavigationCommunication
import io.realm.Realm
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

class CoreModule : BaseModule<MainViewModel>{

    private companion object {
        const val BASE_URL = "https://bible-go-api.rkeplin.com/v1/"
    }

    lateinit var resourceProvider: ResourceProvider
    lateinit var gson: Gson
    lateinit var realmProvider: RealmProvider
    lateinit var navigator: Navigator
    lateinit var navigationCommunication: NavigationCommunication
    lateinit var bookCache: BookCache // TODO: 09.10.2021 move to common for 2 modules when added ChapterCache
    private lateinit var retrofit: Retrofit

   fun init(context: Context) {
       Realm.init(context)
       val client = OkHttpClient.Builder()
           .connectTimeout(1, TimeUnit.MINUTES)
           .readTimeout(1, TimeUnit.MINUTES)
           .addInterceptor(HttpLoggingInterceptor().apply {
               level = HttpLoggingInterceptor.Level.BODY
           }).build()

       gson = Gson()
       realmProvider = RealmProvider.Base()
       retrofit = Retrofit.Builder().client(client).baseUrl(BASE_URL).build()
       resourceProvider = ResourceProvider.Base(context)
       bookCache = BookCache.Base(resourceProvider)

       navigator = Navigator.Base(resourceProvider)
       navigationCommunication = NavigationCommunication.Base()

   }

    fun <T> makeService(clazz: Class<T>): T = retrofit.create(clazz)

    override fun getViewModel() = MainViewModel(navigator,navigationCommunication)
}