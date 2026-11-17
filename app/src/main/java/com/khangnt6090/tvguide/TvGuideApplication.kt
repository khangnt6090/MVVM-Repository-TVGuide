package com.khangnt6090.tvguide

import android.app.Application
import com.khangnt6090.tvguide.di.AppContainer

class TvGuideApplication : Application() {
    val container: AppContainer by lazy {
        AppContainer(applicationContext)
    }
}
