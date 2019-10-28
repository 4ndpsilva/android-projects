package app.prototipo.hotelmanager.model

data class Hotel(
    var id: Long = 0,
    var name: String = "",
    var address: String = "",
    var rating: Double = 0.0
) {
    override fun toString() = name
}