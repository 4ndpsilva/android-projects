package app.prototipo.hotelmanager.view.fragment

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.fragment.app.ListFragment
import app.prototipo.hotelmanager.model.Hotel
import app.prototipo.hotelmanager.presenter.HotelListPresenter
import app.prototipo.hotelmanager.repository.impl.MemoryRepositoryImpl
import app.prototipo.hotelmanager.view.HotelListView

class HotelListFragment : ListFragment(), HotelListView{
    private val presenter = HotelListPresenter(this, MemoryRepositoryImpl)

    override fun onActivityCreated(savedInstanceState: Bundle?){
        super.onActivityCreated(savedInstanceState)
        presenter.search("")
    }

    override fun showList(models: List<Hotel>) {
        val adapter = ArrayAdapter<Hotel>(requireContext(), android.R.layout.simple_list_item_1, models)
        listAdapter = adapter
    }

    override fun showDetails(model: Hotel) {

    }
}