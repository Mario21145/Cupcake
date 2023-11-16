/*
 * Copyright (C) 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.cupcake.model

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.cupcake.R
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import kotlin.math.absoluteValue

private const val PRICE_PER_CUPCAKE = 2.00
private const val PRICE_FOR_SAME_DAY_PICKUP = 3.00

class OrderViewModel : ViewModel() {

    private val _quantity = MutableLiveData<Int>()
    val quantity: LiveData<Int> = _quantity

    private val _counterCupcake = MutableLiveData<Int>()
    val counterCupcake: LiveData<Int> = _counterCupcake



    private val _counterVanilla = MutableLiveData<Int>()
    val counterVanilla: LiveData<Int> = _counterVanilla

    private val _counterChocolate = MutableLiveData<Int>()
    val counterChocolate: LiveData<Int> = _counterChocolate

    private val _counterRedVelvet = MutableLiveData<Int>()
    val counterRedVelvet: LiveData<Int> = _counterRedVelvet

    private val _counterSaltedCaramel = MutableLiveData<Int>()
    val counterSaltedCaramel: LiveData<Int> = _counterSaltedCaramel

    private val _counterCoffee = MutableLiveData<Int>()
    val counterCoffee: LiveData<Int> = _counterCoffee



    private val _flavor = MutableLiveData<String>()
    val flavor: LiveData<String> = _flavor

    val dateOptions: List<String> = getPickupOptions()

    val flavorsSelected: MutableList<String> = mutableListOf()

    private val _date = MutableLiveData<String>()
    val date: LiveData<String> = _date

    private val _price = MutableLiveData<Double>()
    val price: LiveData<String> = Transformations.map(_price) {
        NumberFormat.getCurrencyInstance().format(it)
    }

    private val _name = MutableLiveData<String>()
    val name: LiveData<String> = _name

    private val _surname = MutableLiveData<String>()
    val surname: LiveData<String> = _surname

    private val _phone = MutableLiveData<String>()
    val phone: LiveData<String> = _phone

    private val _adress = MutableLiveData<String>()
    val adress: LiveData<String> = _adress

    init {
        resetOrder()
    }

    fun setQuantity(numberCupcakes: Int) {
        _quantity.value = numberCupcakes
        updatePrice()
    }

    fun setFlavor(desiredFlavor: String) {
        _flavor.value = desiredFlavor
    }

    fun hasNoFlavorSet(): Boolean {
        return _flavor.value.isNullOrEmpty()
    }

    fun setDate(pickupDate: String) {
        _date.value = pickupDate
        updatePrice()
    }

    fun setName(name : String ){
        _name.value = name
    }

    fun setSurname(surname : String ){
        _surname.value = surname
    }

    fun setNumber(number : String ){
        _phone.value = number
    }

    fun setAdress(adress : String ){
        _adress.value = adress
    }



    fun incrementCounterCupcake() {
        _counterCupcake.value = _counterCupcake.value?.plus(1)
    }

    fun decrementCounterCupcake(){
        _counterCupcake.value = _counterCupcake.value?.minus(1)
    }

    fun countCupcakes(){
        _counterVanilla.value = flavorsSelected.count { it == "Vanilla" }
        _counterChocolate.value = flavorsSelected.count { it == "Chocolate" }
        _counterRedVelvet.value = flavorsSelected.count { it == "Red Velvet" }
        _counterSaltedCaramel.value = flavorsSelected.count { it == "Salted Caramel" }
        _counterCoffee.value = flavorsSelected.count { it == "Coffee" }
    }

    fun resetOrder() {
        _quantity.value = 0
        _flavor.value = ""
        _date.value = dateOptions[0]
        _price.value = 0.0

        _counterCupcake.value = 0

        _name.value = ""
        _surname.value = ""
        _phone.value = ""
        _adress.value = ""
    }

    private fun updatePrice() {
        var calculatedPrice = (quantity.value ?: 0) * PRICE_PER_CUPCAKE
        if (dateOptions[0] == _date.value) {
            calculatedPrice += PRICE_FOR_SAME_DAY_PICKUP
        }
        _price.value = calculatedPrice
    }

    private fun getPickupOptions(): List<String> {
        val options = mutableListOf<String>()
        val formatter = SimpleDateFormat("E MMM d", Locale.getDefault())
        val calendar = Calendar.getInstance()
        repeat(4) {
            options.add(formatter.format(calendar.time))
            calendar.add(Calendar.DATE, 1)
        }
        return options
    }

    fun showToast(context: Context, msg: String, duration: Int) {
        Toast.makeText(context, msg, duration).show()
    }



}