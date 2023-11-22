package com.example.cupcake

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cupcake.databinding.FragmentButtonFlavorBinding
import com.example.cupcake.databinding.FragmentNameBinding


class ButtonFlavorFragment : Fragment() {

    private var binding: FragmentButtonFlavorBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val binding = FragmentButtonFlavorBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)




    }

    companion object {

        fun newInstance(param1: String, param2: String) =
            ButtonFlavorFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}