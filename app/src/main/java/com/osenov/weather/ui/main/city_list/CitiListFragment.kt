package com.osenov.weather.ui.main.city_list

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.osenov.weather.CITIES_PAGE_SIZE
import com.osenov.weather.R
import com.osenov.weather.appComponent
import com.osenov.weather.databinding.FragmentCitiListBinding
import com.osenov.weather.ui.main.main.MainActivity
import com.osenov.weather.ui.main.weather_city.WeatherCityFragment
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject
import androidx.core.content.ContextCompat.getSystemService
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.osenov.weather.CitiesUploadWorker


class CitiListFragment : Fragment() {

    private val viewModel: CityListViewModel by viewModels {
        requireContext().appComponent.viewModelsFactory()
    }

    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        FragmentCitiListBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = CitiesPagingAdapter(viewModel, CityClickListener {
            findNavController().navigate(
                R.id.action_citiListFragment_to_weatherCityFragment,
                bundleOf(WeatherCityFragment.cityModel to it)
            )
        })

        binding.citiesRecyclerView.adapter = adapter

        val touchHelper = ItemTouchHelper(CitiesItemTouchHelperCallback(adapter))
        touchHelper.attachToRecyclerView(binding.citiesRecyclerView)


        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.cities.observe (viewLifecycleOwner, {
                adapter.submitData(lifecycle, it)
            })
        }

        binding.search.imageDeleteQuery.setOnClickListener {
            binding.search.editTextSearch.text.clear()
        }
        binding.search.editTextSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.searchCities(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {
            }

        })

        binding.citiesRecyclerView.setOnTouchListener { v, _ ->
            val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(v.windowToken, 0)
        }

        binding.swipeRefreshLayoutCities.setOnRefreshListener {
            viewModel.searchCities(binding.search.editTextSearch.text.toString())
            binding.swipeRefreshLayoutCities.isRefreshing = false
        }

        viewModel.citiesWorkManager?.observe(this, {
            when (it.state) {
                WorkInfo.State.SUCCEEDED -> {
                    viewModel.searchCities(binding.search.editTextSearch.text.toString())
                    binding.layoutCitiesInfo.visibility = View.GONE
                }
                WorkInfo.State.FAILED -> binding.layoutCitiesInfo.visibility = View.GONE
                WorkInfo.State.RUNNING -> {
                    binding.layoutCitiesInfo.visibility = View.VISIBLE
                    binding.citiesWorkerTextInfo.text =
                        resources.getString(R.string.cities_upload_running)
                }
                else -> binding.layoutCitiesInfo.visibility = View.GONE
            }
        })

    }

}