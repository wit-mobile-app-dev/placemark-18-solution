package org.wit.placemark.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.Marker
import org.wit.placemark.R

class MapsActivity : AppCompatActivity(), GoogleMap.OnMarkerDragListener, GoogleMap.OnMarkerClickListener {

  lateinit var map: GoogleMap
  lateinit var presenter: MapsPresenter

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_maps)
    val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
    presenter = MapsPresenter(this)
    mapFragment.getMapAsync {
      map = it
      map.setOnMarkerDragListener(this)
      map.setOnMarkerClickListener(this)
      presenter.initMap(map)
    }
  }

  override fun onMarkerDragStart(marker: Marker) {}

  override fun onMarkerDrag(marker: Marker) {}

  override fun onMarkerDragEnd(marker: Marker) {
    presenter.doUpdateLocation(marker.position.latitude, marker.position.longitude, map.cameraPosition.zoom)
  }

  override fun onBackPressed() {
    presenter.doOnBackPressed()
  }

  override fun onMarkerClick(marker: Marker): Boolean {
    presenter.doUpdateMarker(marker)
    return false
  }
}