package br.com.myfinances.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import br.com.myfinances.R
import br.com.myfinances.databinding.CategoryItemBinding
import br.com.myfinances.rest.CategoryResponse

class CategoryAdapter(private val categories: List<CategoryResponse>) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val dataBinding = DataBindingUtil.inflate<CategoryItemBinding>(inflater, R.layout.category_item, parent, false)
        return CategoryViewHolder(dataBinding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.dataBinding.category = categories[position]
    }

    override fun getItemCount() = categories.size

    inner class CategoryViewHolder(val dataBinding: CategoryItemBinding) : RecyclerView.ViewHolder(dataBinding.root)
}