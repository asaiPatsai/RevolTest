package com.example.revoluttestapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.revoluttestapp.adapters.RatesAdapter
import com.example.revoluttestapp.interfaces.AmountChangedInterface
import com.example.revoluttestapp.interfaces.ApiInterface
import com.example.revoluttestapp.models.RatesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import kotlin.collections.ArrayList


private const val BASE_URL = "https://revolut.duckdns.org/"

class MainActivity : AppCompatActivity(), AmountChangedInterface {
    private lateinit var mApi: ApiInterface
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mAdapter: RatesAdapter
    private var mBase: String? = null
    private var mDate: String? = null
    private var mRates: Map<String, Double>? = null
    private var mNewInput = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        mApi = retrofit.create(ApiInterface::class.java)

        mRecyclerView = findViewById(R.id.recycler_list)
        mRecyclerView.layoutManager = LinearLayoutManager(this)

        startGettingRates()
    }

    override fun amountChanged(amount: Double) {
        mNewInput = true
        val updatedList = ArrayList<Double>()
        mRates?.forEach {
            updatedList.add(it.value * amount)
        }
        mAdapter.updateValues(updatedList)
    }

    private fun updateRecyclerView() {
        if (!::mAdapter.isInitialized) {
            mAdapter = RatesAdapter(
                ArrayList(mRates!!.keys),
                ArrayList(mRates!!.values),
                this,
                mRecyclerView
            )
        }
        mRecyclerView.adapter = mAdapter
    }

    private fun startGettingRates() {
        Timer().scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                getRates("EUR")
            }
        }, 0, 10000)
    }

    private fun getRates(baseCurrency: String) {
        val call = mApi.getRates(baseCurrency)

        call.enqueue(object : Callback<RatesResponse> {
            override fun onResponse(call: Call<RatesResponse>?, response: Response<RatesResponse>?) {
                val ratesResponse = response?.body()

                mBase = ratesResponse?.base
                mDate = ratesResponse?.date
                mRates = ratesResponse?.rates

                if (mRates != null && mNewInput) {
                    updateRecyclerView()
                    mNewInput = false
                }
            }

            override fun onFailure(call: Call<RatesResponse>, t: Throwable) {
                Log.e("Error", t.toString())
            }
        })
    }
}
