package org.wit.placemark.activities

import org.jetbrains.anko.intentFor
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.startActivityForResult
import org.wit.placemark.main.MainApp
import org.wit.placemark.models.PlacemarkModel

class PlacemarkListPresenter(val activity: PlacemarkListActivity) {

  var app: MainApp

  init {
    app = activity.application as MainApp
  }

  fun getPlacemarks() = app.placemarks.findAll()

  fun doAddPlacemark() {
    activity.startActivityForResult<PlacemarkActivity>(0)
  }

  fun doEditPlacemark(placemark: PlacemarkModel) {
    activity.startActivityForResult(activity.intentFor<PlacemarkActivity>().putExtra("placemark_edit", placemark), 0)
  }

  fun doShowPlacemarksMap() {
    activity.startActivity<PlacemarkMapsActivity>()
  }
}