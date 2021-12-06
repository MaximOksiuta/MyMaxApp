package com.example.myapplication.adapter

import android.content.Intent
import com.example.myapplication.databinding.FilmOnCategoryActivityBinding
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.myapplication.FilmInfoActivity
import com.example.myapplication.model.Film
import com.example.myapplication.utils.Constants

class FilmsOnCategoryScreenAdapter() :
    RecyclerView.Adapter<FilmsOnCategoryScreenAdapter.FilmsOnCategoryScreenViewHolder>(),
    View.OnClickListener {

    private var films = emptyList<Film>()

    fun setData(input: List<Film>){
        films = input
        notifyDataSetChanged()
    }

    class FilmsOnCategoryScreenViewHolder(val binding: FilmOnCategoryActivityBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FilmsOnCategoryScreenViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = FilmOnCategoryActivityBinding.inflate(inflater, parent, false)
        binding.root.setOnClickListener(this)
        return FilmsOnCategoryScreenViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FilmsOnCategoryScreenViewHolder, position: Int) {
        val film = films[position]
        holder.itemView.tag = film
        with(holder.binding) {
            tvFilmTitle.text = film.title
            pbLoadImage.visibility = View.VISIBLE
            Glide.with(ivImage.context).load(Constants.BASE_URL + Constants.PICTURE_URL + film.imageURL)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        pbLoadImage.visibility = View.GONE
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        pbLoadImage.visibility = View.GONE
                        return false
                    }
                }).into(ivImage)
        }
    }

    override fun getItemCount(): Int = films.size
    override fun onClick(v: View?) {
        val film = v?.tag as Film
        val intent = Intent(v.context, FilmInfoActivity::class.java)
        intent.putExtra("title", film.title)
        intent.putExtra("year", film.year)
        intent.putExtra("rating", film.rating)
        intent.putExtra("description", film.description)
        intent.putExtra("imageURL", film.imageURL)
        v.context.startActivity(intent)
    }


}