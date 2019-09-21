package org.wit.placemark.main

import android.app.Application
import com.google.firebase.FirebaseApp
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.placemark.models.json.PlacemarkJSONStore
import org.wit.placemark.models.PlacemarkStore
import org.wit.placemark.models.firebase.PlacemarkFireStore
import org.wit.placemark.room.PlacemarkStoreRoom

class MainApp : Application(), AnkoLogger {

  lateinit var placemarks: PlacemarkStore

  override fun onCreate() {
    super.onCreate()
    //placemarks = PlacemarkJSONStore(applicationContext)
    //placemarks = PlacemarkStoreRoom(applicationContext)
    FirebaseApp.initializeApp(this);
    placemarks = PlacemarkFireStore(applicationContext)
    info("Placemark started")

  }
}