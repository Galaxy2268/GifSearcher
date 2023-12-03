package com.task.testchili

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide



class GifsAdapter(private var context: Context, private val gifs: MutableList<DataObjet>) : RecyclerView.Adapter<GifsAdapter.GifsHolder>() {

    //method which clears all from list and sets new items
    @SuppressLint("NotifyDataSetChanged")
    fun update(newData: MutableList<DataObjet>) {
        gifs.clear()
        gifs.addAll(newData)
        notifyDataSetChanged()
    }
    //methods updates the list without clearing items
    @SuppressLint("NotifyDataSetChanged")
    fun add(newData: MutableList<DataObjet>) {
        gifs.addAll(newData)
        notifyDataSetChanged()
    }
    //gifsHolder class
    class GifsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView

        init {
            imageView = itemView.findViewById(R.id.testImageView)
        }
    }
    //standard onCreateViHol method
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GifsHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.gif_item, parent, false)
        return GifsHolder(view)
    }

    override fun getItemCount(): Int {
        return gifs.size
    }

    override fun onBindViewHolder(holder: GifsHolder, position: Int) {
        val data = gifs[position]

        //load our url to gif in imageView
        Glide.with(context).load(data.images.ogImage.url).into(holder.imageView)


    }


}