package org.wit.placemark.activities

import android.content.Intent
import org.jetbrains.anko.intentFor
import org.wit.placemark.helpers.showImagePicker
import org.wit.placemark.main.MainApp
import org.wit.placemark.models.Location
import org.wit.placemark.models.PlacemarkModel

class PlacemarkPresenter(val activity: PlacemarkActivity) {

  val IMAGE_REQUEST = 1
  val LOCATION_REQUEST = 2

  var placemark = PlacemarkModel()
  var location = Location(52.245696, -7.139102, 15f)
  var app: MainApp
  var edit = false;

  init {
    app = activity.application as MainApp
    if (activity.intent.hasExtra("placemark_edit")) {
      edit = true
      placemark = activity.intent.extras.getParcelable<PlacemarkModel>("placemark_edit")
      activity.showPlacemark(placemark)
    }
  }

  fun doAddOrSave(title: String, description: String) {
    placemark.title = title
    placemark.description = description
    if (edit) {
      app.placemarks.update(placemark)
    } else {
      app.placemarks.create(placemark)
    }
    activity.finish()
  }

  fun doCancel() {
    activity.finish()
  }

  fun doDelete() {
    app.placemarks.delete(placemark)
    activity.finish()
  }

  fun doSelectImage() {
    showImagePicker(activity, IMAGE_REQUEST)
  }

  fun doSetLocation() {
    if (placemark.zoom != 0f) {
      location.lat = placemark.lat
      location.lng = placemark.lng
      location.zoom = placemark.zoom
    }
    activity.startActivityForResult(activity.intentFor<MapsActivity>().putExtra("location", location), LOCATION_REQUEST)
  }

  fun doActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
    when (requestCode) {
      IMAGE_REQUEST -> {
        placemark.image = data.data.toString()
        activity.showPlacemark(placemark)
      }
      LOCATION_REQUEST -> {
        location = data.extras.getParcelable<Location>("location")
        placemark.lat = location.lat
        placemark.lng = location.lng
        placemark.zoom = location.zoom
      }
    }
  }
}