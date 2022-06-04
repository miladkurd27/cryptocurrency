package com.example.cryptocurrency.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cryptocurrency.R
import com.example.cryptocurrency.databinding.TopCurrencyLayoutBinding
import com.example.cryptocurrency.models.CryptoCurrency

class TopMarkerAdapter(var context:Context,val list:List<CryptoCurrency>):
    RecyclerView.Adapter<TopMarkerAdapter.ViewHolder>() {


    inner class ViewHolder(view:View) :RecyclerView.ViewHolder(view){
        var binding=TopCurrencyLayoutBinding.bind(view)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopMarkerAdapter.ViewHolder {
       // return ViewHolder(LayoutInflater.from(context).inflate(R.layout.top_currency_layout),parent,false))
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.top_currency_layout,parent,false))
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: TopMarkerAdapter.ViewHolder, position: Int) {
        val item=list[position]
        holder.binding.topCurrencyNameTextView.text=item.name
        Glide.with(context)
            .load("https://s2.coinmarketcap.com/static/img/coins/64x64/"+ item.id +".png")
            .thumbnail(Glide.with(context).load(R.drawable.spinner))
            .into(holder.binding.topCurrencyImageView)


        if(item.quotes!![0].percentChange24h> 0){
            holder.binding.topCurrencyChangeTextView.setTextColor(context.resources.getColor(R.color.green))
            holder.binding.topCurrencyChangeTextView.text ="+ ${String.format("%.02f", item.quotes[0].percentChange24h)} %"


        }else{
            holder.binding.topCurrencyChangeTextView.setTextColor(context.resources.getColor(R.color.red))
            holder.binding.topCurrencyChangeTextView.text =" ${String.format("%.02f", item.quotes[0].percentChange24h)} %"

        }
    }

    override fun getItemCount(): Int {
       return list.size
    }

}