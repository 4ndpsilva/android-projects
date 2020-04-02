package br.com.myfinances.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import br.com.myfinances.MobileApi
import br.com.myfinances.R
import br.com.myfinances.data.repository.CategoryRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CategoryFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_category, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val repository = CategoryRepository(MobileApi())

        GlobalScope.launch{
            val categories = repository.getAll()
            Toast.makeText(requireContext(), categories.toString(), Toast.LENGTH_LONG).show()
        }
    }
}