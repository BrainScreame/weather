package com.osenov.weather.ui.main.main

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.osenov.weather.CitiesUploadWorker
import com.osenov.weather.R
import com.osenov.weather.appComponent
import com.osenov.weather.databinding.ActivityMainBinding
import com.osenov.weather.ui.main.city_list.CityListViewModel
import com.osenov.weather.ui.main.weather_city.WeatherCityFragment
import com.osenov.weather.ui.main.weather_city.WeatherCityViewModel
import java.net.Inet4Address
import java.net.NetworkInterface

class MainActivity : AppCompatActivity() {

    companion object {
        private const val ACCESS_FINE_LOCATION_PERMISSION_CODE = 100
    }

    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val viewModel: MainViewModel by viewModels {
        appComponent.viewModelsFactory()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        checkPermission(
            Manifest.permission.ACCESS_FINE_LOCATION,
            ACCESS_FINE_LOCATION_PERMISSION_CODE
        )

//        viewModel.locationLiveData.observe(this, {
//            Toast.makeText(this, it.address, Toast.LENGTH_LONG).show()
//        })

    }

    private fun show() {
        //viewModel.getLocation()
//        findNavController(R.id.nav_host_fragment).navigate(
//            R.id.action_citiListFragment_to_weatherCityFragment
//        )
    }

    private fun showCities() {
//        findNavController(R.id.nav_host_fragment).navigate(
//            R.id.citiListFragment
//        )
    }

    private fun checkPermission(permission: String, requestCode: Int) {
        if (ContextCompat.checkSelfPermission(
                this@MainActivity,
                permission
            ) == PackageManager.PERMISSION_DENIED
        ) {
            ActivityCompat.requestPermissions(this@MainActivity, arrayOf(permission), requestCode)
        } else {
            show()
        }
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == ACCESS_FINE_LOCATION_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                show()
            } else {
                showCities()
            }
        }
    }

}