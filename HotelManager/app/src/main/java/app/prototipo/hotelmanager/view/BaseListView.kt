package app.prototipo.hotelmanager.view

interface BaseListView<T>{
    fun showList(models: List<T>)
    fun showDetails(model: T)
}