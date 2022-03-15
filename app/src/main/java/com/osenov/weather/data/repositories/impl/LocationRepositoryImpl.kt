package com.osenov.weather.data.repositories.impl

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.telephony.CellInfoGsm
import android.telephony.CellInfoLte
import android.telephony.CellInfoWcdma
import android.telephony.TelephonyManager
import androidx.core.app.ActivityCompat
import com.osenov.weather.data.api.ApiHelper
import com.osenov.weather.data.entities.location.Cell
import com.osenov.weather.data.entities.location.CellInfo
import com.osenov.weather.data.entities.location.CellLocation
import com.osenov.weather.data.entities.location.RadioType
import com.osenov.weather.data.repositories.LocationRepository
import retrofit2.Response
import javax.inject.Inject

class LocationRepositoryImpl @Inject constructor(
    private val apiHelper: ApiHelper,
    private val context: Context
) :
    LocationRepository {

    override suspend fun getLocationApi(): Response<CellLocation> {
        return apiHelper.getLocation(getCurrentCellInfo()[0])
    }

    @SuppressLint("MissingPermission")
    fun getCurrentCellInfo(): List<CellInfo> {
        val telephonyManager = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        val allCellInfo = telephonyManager.allCellInfo

        return allCellInfo.mapNotNull {
            when (it) {
                is CellInfoGsm -> getCellInfo(it)
                is CellInfoWcdma -> getCellInfo(it)
                is CellInfoLte -> getCellInfo(it)
                else -> null
            }
        }
    }

    fun getCellInfo(info: CellInfoGsm): CellInfo {
        val cellInfo = CellInfo()
        cellInfo.radio = RadioType.GSM

        info.cellIdentity.let {
            val (mcc, mnc) = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                Pair(it.mccString?.toInt() ?: 0, it.mncString?.toInt() ?: 0)
            } else {
                Pair(it.mcc, it.mnc)
            }
            cellInfo.mcc = mcc
            cellInfo.mnc = mnc
            cellInfo.cells = listOf(Cell(it.lac, it.cid, it.psc))
        }

        return cellInfo
    }

    fun getCellInfo(info: CellInfoWcdma): CellInfo {
        val cellInfo = CellInfo()
        cellInfo.radio = RadioType.CDMA

        info.cellIdentity.let {
            val (mcc, mnc) = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                Pair(it.mccString?.toInt() ?: 0, it.mncString?.toInt() ?: 0)
            } else {
                Pair(it.mcc, it.mnc)
            }
            cellInfo.mcc = mcc
            cellInfo.mnc = mnc
            cellInfo.cells = listOf(Cell(it.lac, it.cid, it.psc))
        }

        return cellInfo
    }

    fun getCellInfo(info: CellInfoLte): CellInfo {
        val cellInfo = CellInfo()
        cellInfo.radio = RadioType.LTE

        info.cellIdentity.let {
            val (mcc, mnc) = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                Pair(it.mccString?.toInt() ?: 0, it.mncString?.toInt() ?: 0)
            } else {
                Pair(it.mcc, it.mnc)
            }
            cellInfo.mcc = mcc
            cellInfo.mnc = mnc
            //cellInfo.cells = listOf(Cell(it.lac, it.cid, it.psc))
        }

        return cellInfo
    }



}