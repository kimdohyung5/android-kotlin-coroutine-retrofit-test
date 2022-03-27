package com.kimdo.retrofittest.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kimdo.retrofittest.databinding.ItemCountryBinding
import com.kimdo.retrofittest.model.Country
class CountryListAdapter(var countries: ArrayList<Country>): RecyclerView.Adapter<CountryListAdapter.CountryViewHolder>() {

    class CountryViewHolder(var binding: ItemCountryBinding): RecyclerView.ViewHolder(binding.root)

    fun updateCountries(newCountries: List<Country>) {
        countries.clear()
        countries.addAll( newCountries)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val inflator = LayoutInflater.from(parent.context)
        val binding = ItemCountryBinding.inflate(inflator, parent, false)
        return CountryViewHolder( binding )
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.binding.name.text = countries[position].countryName
        holder.binding.capital.text = countries[position].capital
        holder.binding.imageView.loadImage( countries[position].flag)
    }

    override fun getItemCount(): Int {
        return countries.size
    }
}