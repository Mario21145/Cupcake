package com.example.cupcake

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.cupcake.databinding.FragmentButtonFlavorBinding
import com.example.cupcake.databinding.FragmentNameBinding
import com.example.cupcake.model.OrderViewModel


class ButtonFlavorFragment : Fragment() {

    private var binding: FragmentButtonFlavorBinding? = null
    private val sharedViewModel: OrderViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val binding = FragmentButtonFlavorBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {

        fun newInstance(param1: String, param2: String) =
            ButtonFlavorFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}