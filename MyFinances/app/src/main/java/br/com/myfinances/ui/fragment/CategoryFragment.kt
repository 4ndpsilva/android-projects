package br.com.myfinances.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.myfinances.R
import br.com.myfinances.repository.CategoryRepository
import br.com.myfinances.rest.MobileApi
import br.com.myfinances.ui.adapter.CategoryAdapter
import br.com.myfinances.viewmodel.CategoryViewModel
import br.com.myfinances.viewmodel.factory.Factory
import kotlinx.android.synthetic.main.fragment_category.*

class CategoryFragment : Fragment() {
    private lateinit var viewModel: CategoryViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_category, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val repo = CategoryRepository(MobileApi())
        viewModel = ViewModelProvider(this, Factory(repo)).get(CategoryViewModel::class.java)
        viewModel.getAll()
        viewModel.categories.observe(viewLifecycleOwner, Observer{ categories ->
            rvCategories.also {
                it.layoutManager = LinearLayoutManager(requireContext())
                it.setHasFixedSize(true)
                it.adapter = CategoryAdapter(categories)
            }
        })
    }
}