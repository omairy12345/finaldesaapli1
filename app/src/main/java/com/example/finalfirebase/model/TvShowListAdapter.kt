package com.example.finalfirebase.model

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.finalfirebase.databinding.TvShowListItemBinding

class TvShowListAdapter (val tvshows:List<Tvshow>):RecyclerView.Adapter<TvShowListAdapter.ViewHolder> (){

  public class ViewHolder(binding: TvShowListItemBinding):RecyclerView.ViewHolder(binding.root){
      val binding= binding
  }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding=TvShowListItemBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
      holder.binding.tvShowName.text=tvshows[position].name
    }

    override fun getItemCount(): Int {
        return tvshows.size
    }

}