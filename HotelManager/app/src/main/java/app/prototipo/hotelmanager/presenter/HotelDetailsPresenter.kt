package app.prototipo.hotelmanager.presenter

import app.prototipo.hotelmanager.model.Hotel
import app.prototipo.hotelmanager.repository.HotelRepository
import app.prototipo.hotelmanager.view.DetailsView

class HotelDetailsPresenter(private val view: DetailsView<Hotel>, private val repository: HotelRepository){
    fun loadDetails(id: Long){
        repository.findById(id){
            if(it != null){
                view.showDetails(it)
            }
            else{
                view.errorNotFound()
            }
        }
    }
}