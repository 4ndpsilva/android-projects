package app.prototipo.hotelmanager.repository.impl

import app.prototipo.hotelmanager.model.Hotel
import app.prototipo.hotelmanager.repository.HotelRepository

object MemoryRepositoryImpl : HotelRepository {
    private var nextId = 1L
    private val list = mutableListOf<Hotel>()

    init{
        save(Hotel(0, "New Beach Hotel", "Av. Nações Unidas", 4.5))
        save(Hotel(0, "Recife Hotel", "Av. Rodrigues Alves", 4.0))
        save(Hotel(0, "Canario Hotel", "Rua Gustavo Maciel", 3.0))
        save(Hotel(0, "Byanca Beach Hotel", "Av. Getúlio Vargas", 4.0))
        save(Hotel(0, "Hotel Cool", "Av. 9 de Julho", 3.5))
        save(Hotel(0, "Hotel Infinito", "Av. Boa Viagem", 4.0))
        save(Hotel(0, "Tulipa Hotel", "Av. Don Antonio", 5.0))
        save(Hotel(0, "Grand Dor Hotel", "Av. Rui Barbosa", 5.0))
    }

    override fun save(model: Hotel) {
        if(model.id == 0L){
            model.id = nextId++
            list.add(model)
        }
        else{
            val index = list.indexOfFirst { it.id == model.id }
            if(index > -1){
                list[index] = model
            }
            else{
                list.add(model)
            }
        }
    }

    override fun remove(vararg models: Hotel) {
        list.removeAll(models)
    }

    override fun findById(id: Long, callback: (Hotel?) -> Unit) {
        callback(list.find{ it.id == id})
    }

    override fun search(term: String, callback: (List<Hotel>) -> Unit) {
        if(term.isNotEmpty()){
            callback(list.filter { it.name.toUpperCase().contains(term.toUpperCase()) })
        }
        else{
            callback(list)
        }
    }
}