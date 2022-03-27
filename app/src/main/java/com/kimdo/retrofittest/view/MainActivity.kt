package com.kimdo.retrofittest.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.kimdo.retrofittest.R
import com.kimdo.retrofittest.databinding.ActivityMainBinding
import com.kimdo.retrofittest.viewmodel.ListViewModel

class MainActivity : AppCompatActivity() {

    private  lateinit var  viewModel:ListViewModel

    private val countriesAdapter = CountryListAdapter(arrayListOf())
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(ListViewModel::class.java)
        viewModel.refresh()

        binding.countriesList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = countriesAdapter
        }

        observeViewModel()
    }
    fun observeViewModel() {
        viewModel.countries.observe(this) { countries ->
            countries?.let {
                binding.countriesList.visibility = View.VISIBLE
                countriesAdapter.updateCountries(it)
            }
        }
        viewModel.countryLoadError.observe(this) { isError ->
            binding.listError.visibility = if( isError == null) View.GONE else View.VISIBLE
        }
        viewModel.loading.observe(this) { isLoading ->
            isLoading?.let {
                binding.loadingView.visibility = if(it) View.VISIBLE else View.GONE
                if(it) {
                    binding.listError.visibility = View.GONE
                    binding.countriesList.visibility = View.GONE
                }
            }
        }
    }
}