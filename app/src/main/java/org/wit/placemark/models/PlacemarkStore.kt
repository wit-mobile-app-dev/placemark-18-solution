package org.wit.placemark.models

interface PlacemarkStore {
  fun findAll(): List<PlacemarkModel>
  fun findById(id:Long) : PlacemarkModel?
  fun create(placemark: PlacemarkModel)
  fun update(placemark: PlacemarkModel)
  fun delete(placemark: PlacemarkModel)
  fun clear()
}