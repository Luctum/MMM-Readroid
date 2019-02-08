package palla_boitard_mubanzo.readroid

import android.app.Application
import com.google.firebase.FirebaseApp

class FireBaseContext : Application() {
    override fun onCreate() {
        super.onCreate();
        FirebaseApp.initializeApp(this)
    }
}