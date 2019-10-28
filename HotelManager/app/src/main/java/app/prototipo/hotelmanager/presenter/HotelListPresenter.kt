package app.prototipo.hotelmanager.presenter

import app.prototipo.hotelmanager.model.Hotel
import app.prototipo.hotelmanager.repository.HotelRepository
import app.prototipo.hotelmanager.view.HotelListView

class HotelListPresenter(private val view: HotelListView, private val repository: HotelRepository){
    fun search(term: String){
        repository.search(term) {hotels ->
            view.showList(hotels)
        }
    }

    fun showDetails(hotel: Hotel){
        view.showDetails(hotel)
    }
}