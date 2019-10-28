package app.prototipo.hotelmanager.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import app.prototipo.hotelmanager.R
import app.prototipo.hotelmanager.model.Hotel
import app.prototipo.hotelmanager.presenter.HotelDetailsPresenter
import app.prototipo.hotelmanager.repository.impl.MemoryRepositoryImpl
import app.prototipo.hotelmanager.view.DetailsView
import kotlinx.android.synthetic.main.hotel_details_fragment.*

class HotelDetailsFragment : Fragment(), DetailsView<Hotel>{
    private val presenter = HotelDetailsPresenter(this, MemoryRepositoryImpl)
    private lateinit var hotel: Hotel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.hotel_details_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.loadDetails(arguments?.getLong(EXTRA_HOTEL_ID, -1) ?: -1)
    }

    override fun showDetails(hotel: Hotel) {
        this.hotel = hotel
        tvName.text = hotel.name
        tvAddress.text = hotel.address
        rtRating.rating = hotel.rating.toFloat()
    }

    override fun errorNotFound() {
        tvName.text = getString(R.string.error_not_found)
        tvAddress.visibility = View.GONE
        rtRating.visibility = View.GONE

    }

    companion object{
        const val TAG_DETAILS = "tagDetalhe"
        private const val EXTRA_HOTEL_ID = "hotelId"

        fun newInstance(id: Long) = HotelDetailsFragment().apply{
            arguments = Bundle().apply { putLong(EXTRA_HOTEL_ID, id) }
        }
    }
}