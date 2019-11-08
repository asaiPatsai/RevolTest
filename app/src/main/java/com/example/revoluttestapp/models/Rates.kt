package com.example.revoluttestapp.models

import com.google.gson.annotations.SerializedName

data class Rates (
    @SerializedName("AUD")
    var aud: Double = 1.0,
    @SerializedName("BGN")
    var bgn: Double = 1.0,
    @SerializedName("BRL")
    var brl: Double = 1.0,
    @SerializedName("CAD")
    var cad: Double = 1.0,
    @SerializedName("CHF")
    var chf: Double = 1.0,
    @SerializedName("CNY")
    var cny: Double = 1.0,
    @SerializedName("CZK")
    var czk: Double = 1.0,
    @SerializedName("DKK")
    var dkk: Double = 1.0,
    @SerializedName("GBP")
    var gbp: Double = 1.0,
    @SerializedName("HKD")
    var hkd: Double = 1.0,
    @SerializedName("HRK")
    var hrk: Double = 1.0,
    @SerializedName("HUF")
    var huf: Double = 1.0,
    @SerializedName("IDR")
    var idr: Double = 1.0,
    @SerializedName("ILS")
    var ils: Double = 1.0,
    @SerializedName("INR")
    var inr: Double = 1.0,
    @SerializedName("ISK")
    var isk: Double = 1.0,
    @SerializedName("JPY")
    var jpy: Double = 1.0,
    @SerializedName("KRW")
    var krw: Double = 1.0,
    @SerializedName("MXN")
    var mxn: Double = 1.0,
    @SerializedName("MYR")
    var myr: Double = 1.0,
    @SerializedName("NOK")
    var nok: Double = 1.0,
    @SerializedName("NZD")
    var nzd: Double = 1.0,
    @SerializedName("PHP")
    var php: Double = 1.0,
    @SerializedName("PLN")
    var pln: Double = 1.0,
    @SerializedName("RON")
    var ron: Double = 1.0,
    @SerializedName("RUB")
    var rub: Double = 1.0,
    @SerializedName("SEK")
    var sek: Double = 1.0,
    @SerializedName("SGD")
    var sgd: Double = 1.0,
    @SerializedName("THB")
    var thb: Double = 1.0,
    @SerializedName("TRY")
    var TRY: Double = 1.0,
    @SerializedName("USD")
    var usd: Double = 1.0,
    @SerializedName("ZAR")
    var zar: Double = 1.0,
    @SerializedName("EUR")
    var eur: Double = 1.0,
    val size: Int = 32
)