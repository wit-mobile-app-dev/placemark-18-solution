package org.wit.placemark.views.placemarklist

import com.google.firebase.auth.FirebaseAuth
import org.wit.placemark.models.PlacemarkModel
import org.wit.placemark.views.BasePresenter
import org.wit.placemark.views.BaseView
import org.wit.placemark.views.VIEW

class PlacemarkListPresenter(view: BaseView) : BasePresenter(view) {

  fun doAddPlacemark() {
    view?.navigateTo(VIEW.PLACEMARK)
  }

  fun doEditPlacemark(placemark: PlacemarkModel) {
    view?.navigateTo(VIEW.PLACEMARK, 0, "placemark_edit", placemark)
  }

  fun doShowPlacemarksMap() {
    view?.navigateTo(VIEW.MAPS)
  }

  fun loadPlacemarks() : List<PlacemarkModel>{
    return app.placemarks.findAll()
  }

  fun doLogout() {
    FirebaseAuth.getInstance().signOut()
    app.placemarks.clear()
    view?.navigateTo(VIEW.LOGIN)
  }
}