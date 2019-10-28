package app.prototipo.hotelmanager.repository

interface BaseRepository<T>{
    fun save(t: T)
    fun remove(vararg t: T)
    fun findById(id: Long, callback: (T?) -> Unit)
    fun search(term: String, callback: (List<T>) -> Unit)
}