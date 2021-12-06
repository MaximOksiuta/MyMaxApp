package com.example.myapplication.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.FilmCategoryActivity
import com.example.myapplication.databinding.FilmGroupRvBinding
import com.example.myapplication.model.FilmGroup

class CategoriesAdapter() :
    RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder>() {

    private var films = emptyList<FilmGroup>()

    fun setData(input: List<FilmGroup>){
        films = input
        notifyDataSetChanged()
    }

    class CategoriesViewHolder(val binding: FilmGroupRvBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = FilmGroupRvBinding.inflate(inflater, parent, false)
        return CategoriesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        val filmGroupName = films[position].groupName
        val films = films[position].films
        with(holder.binding) {
            tvCategoryTitle.text = filmGroupName
            tvCategoryTitle.setOnClickListener {
                var intent = Intent(it.context, FilmCategoryActivity::class.java)
                intent.putExtra("groupName", filmGroupName)
                intent.putExtra("groupId", position)
                it.context.startActivity(intent)
            }
            rvFilmsInCategory.adapter = FilmsAdapter(films.films)
        }
    }

    override fun getItemCount(): Int = films.size
}