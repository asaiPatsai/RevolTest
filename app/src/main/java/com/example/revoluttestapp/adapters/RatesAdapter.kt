package com.example.revoluttestapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.revoluttestapp.R
import com.example.revoluttestapp.interfaces.AmountChangedInterface
import java.text.NumberFormat
import java.util.*


class RatesAdapter(
    val ratesKeys: List<String>,
    var ratesValues: List<Double>,
    amountChangedInterface: AmountChangedInterface,
    val recyclerView: RecyclerView
) :
    RecyclerView.Adapter<RatesAdapter.ViewHolder>() {
    var amountInterface = amountChangedInterface
    var valuesUpdated: Boolean = false
    var inputValue: Double = 0.0
    val mNumberFormat: NumberFormat = NumberFormat.getInstance()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RatesAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.rates_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: RatesAdapter.ViewHolder, position: Int) {
        holder.bindItems(ratesKeys[position], ratesValues[position], amountInterface, this)
    }

    override fun getItemCount(): Int {
        return ratesKeys.size
    }

    fun updateValues(values: List<Double>) {
        ratesValues = values
        recyclerView.post(Runnable { this.notifyDataSetChanged() })
    }

    fun swapItems(fromPosition: Int, toPosition: Int) {
        Collections.swap(ratesKeys, fromPosition, toPosition)
        Collections.swap(ratesValues, fromPosition, toPosition)
        recyclerView.post { this.notifyItemMoved(fromPosition, toPosition)}
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        lateinit var currencyValue: EditText

        fun bindItems(
            key: String,
            value: Double,
            amountInterface: AmountChangedInterface,
            adapter: RatesAdapter
        ) {
            val currencyCode = itemView.findViewById(R.id.currency_code) as TextView
            val currencyTitle = itemView.findViewById(R.id.currency_title) as TextView
            currencyValue = itemView.findViewById(R.id.currency_input) as EditText

            currencyCode.text = key
            currencyTitle.text = Currency.getInstance(key).getDisplayName(Locale.getDefault())

            if (valuesUpdated) {
                mNumberFormat.minimumFractionDigits = 2
                mNumberFormat.maximumFractionDigits = 2

                val temp = Math.round(value * 100)
                val newValue = temp / 100

                currencyValue.setText(mNumberFormat.format(newValue), TextView.BufferType.EDITABLE)
            }

            currencyValue.setOnFocusChangeListener { view, b ->
                if (!b) {
                    if (currencyValue.text.isNotEmpty()) {
                        valuesUpdated = true
                        amountInterface.amountChanged(mNumberFormat.parse(currencyValue.text.toString()).toDouble())
                        inputValue = mNumberFormat.parse(currencyValue.text.toString()).toDouble()
                        adapter.swapItems(layoutPosition, 0)
                        currencyValue.clearFocus()
                    }
                } else {
                    currencyValue.setSelection(currencyValue.text.length)
                }
            }
        }
    }
}