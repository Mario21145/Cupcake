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
package com.example.cupcake

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.cupcake.databinding.FragmentNameBinding
import com.example.cupcake.databinding.FragmentPickupBinding
import com.example.cupcake.model.OrderViewModel



/**
 * [PickupFragment] allows the user to choose a pickup date for the cupcake order.
 */
class NameFragment : Fragment() {

    private var binding: FragmentNameBinding? = null

    private val sharedViewModel: OrderViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val fragmentBinding = FragmentNameBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val name = binding?.nameOrder
        val surname = binding?.surnameOrder
        val number = binding?.phoneOrder
        val adress = binding?.adressOrder


        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = sharedViewModel
            nameFragment = this@NameFragment
        }


        name?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                sharedViewModel.setName(name.text.toString())
                Log.d("Variables" , sharedViewModel.name.value.toString())
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })


        surname?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                sharedViewModel.setSurname(surname.text.toString())
                Log.d("Variables" , sharedViewModel.surname.value.toString())
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })


        number?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                sharedViewModel.setNumber(number.text.toString())
                Log.d("Variables" , sharedViewModel.phone.value.toString())
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })


        adress?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                sharedViewModel.setAdress(adress.text.toString())
                Log.d("Variables" , sharedViewModel.adress.value.toString())
            }

            override fun afterTextChanged(s: Editable?) {
            }
        })
    }

    /**
     * Navigate to the next screen to see the order summary.
     */
    fun goToNextScreen() {
        if (sharedViewModel.name.value!!.isEmpty() || sharedViewModel.surname.value!!.isEmpty() || sharedViewModel.phone.value!!.isEmpty() || sharedViewModel.adress.value!!.isEmpty()) {
            sharedViewModel.showToast(requireContext() , "Some fields are empty!" , 30)
        } else {
            findNavController().navigate(R.id.action_nameFragment_to_summaryFragment)
        }
    }

    /**
     * Cancel the order and start over.
     */
    fun cancelOrder() {
        sharedViewModel.resetOrder()
        findNavController().navigate(R.id.action_nameFragment_to_startFragment)
    }




    /**
     * This fragment lifecycle method is called when the view hierarchy associated with the fragment
     * is being removed. As a result, clear out the binding object.
     */
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}