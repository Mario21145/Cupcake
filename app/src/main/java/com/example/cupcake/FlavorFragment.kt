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
import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cupcake.adapter.FlavorAdapter
import com.example.cupcake.databinding.FragmentFlavorBinding
import com.example.cupcake.model.OrderViewModel
import kotlin.properties.Delegates

/**
 * [FlavorFragment] allows a user to choose a cupcake flavor for the order.
 */
class FlavorFragment : Fragment() {

    // Binding object instance corresponding to the fragment_flavor.xml layout
    // This property is non-null between the onCreateView() and onDestroyView() lifecycle callbacks,
    // when the view hierarchy is attached to the fragment.
    private var binding: FragmentFlavorBinding? = null
    private val sharedViewModel: OrderViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val fragmentBinding = FragmentFlavorBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        return fragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = sharedViewModel
            flavorFragment = this@FlavorFragment
            nextButton.setOnClickListener { goToNextScreen() }
        }

        setHasOptionsMenu(true)

        val recyclerView = binding!!.flavorOptions
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val flavorAdapter = FlavorAdapter(sharedViewModel)
        recyclerView.adapter = flavorAdapter

    }

    /**
     * Navigate to the next screen to choose pickup date.
     */

    fun goToNextScreen() {
//        if(sharedViewModel.quantity.value!! > sharedViewModel.counterCupcake.value!! ){
//            sharedViewModel.incrementCounterCupcake()
//
//            val numberButtons = binding?.flavorOptions?.childCount
//            for (i in 0 until numberButtons!!) {
//                val radioButton = binding?.flavorOptions?.getChildAt(i) as RadioButton
//
//                if (radioButton.isChecked) {
//                    sharedViewModel.flavorsSelected.add(radioButton.text.toString())
//                    break
//                }
//            }
//
//
//            Log.d("ListFlavors" , sharedViewModel.flavorsSelected.toString())
//            findNavController().navigate(R.id.action_flavorFragment_self)
//        } else {

            sharedViewModel.countCupcakes()
            findNavController().navigate(R.id.action_flavorFragment_to_pickupFragment)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                if(sharedViewModel.counterCupcake.value == 0){
                    sharedViewModel.resetOrder()
                } else {
                    sharedViewModel.decrementCounterCupcake()
                    sharedViewModel.flavorsSelected.removeLast()
                }

                findNavController().popBackStack()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
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