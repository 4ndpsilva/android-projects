package app.prototipo.hotelmanager.view

interface DetailsView<T>{
    fun showDetails(t: T)
    fun errorNotFound()
}