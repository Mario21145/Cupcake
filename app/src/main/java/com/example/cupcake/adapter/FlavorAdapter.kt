package com.example.cupcake.adapter

import android.graphics.Color
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.cupcake.ButtonFlavorFragment
import com.example.cupcake.R
import com.example.cupcake.data.DataSource
import com.example.cupcake.databinding.FragmentButtonFlavorBinding
import com.example.cupcake.model.OrderViewModel

class FlavorAdapter(private val viewModel: OrderViewModel) :
    RecyclerView.Adapter<FlavorAdapter.FlavorViewHolder>() {

    private val dataSource = DataSource.flavors

    class FlavorViewHolder(view: View?) : RecyclerView.ViewHolder(view!!) {
        var nameFlavor: TextView = view!!.findViewById(R.id.flavorName)
        var numberCupcake: TextView = view!!.findViewById(R.id.numberCupCake)
        var minusButton: Button = view!!.findViewById(R.id.minus)
        var plusButton: Button = view!!.findViewById(R.id.plus)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): FlavorViewHolder {
        val binding =
            FragmentButtonFlavorBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FlavorViewHolder(binding.root)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onBindViewHolder(holder: FlavorViewHolder, position: Int) {
        val data = viewModel.dataSource[position]

        holder.nameFlavor.text = data.name
        holder.numberCupcake.text = data.number.toString()

        holder.minusButton.isEnabled = data.number != 0
        holder.plusButton.isEnabled =
            viewModel.currentQuantity.value!!.toInt() <= viewModel.quantity.value!!.toInt()

        holder.plusButton.setOnClickListener {
            viewModel.flavorsSelected.add(data.name)
            viewModel.incrementCounterCupcake(position)
            viewModel.countCupcakes()
            notifyDataSetChanged()
        }
        holder.minusButton.setOnClickListener {
            viewModel.flavorsSelected.remove(data.name)
            viewModel.decrementCounterCupcake(position)
            viewModel.countCupcakes()
            notifyDataSetChanged()

        }
    }

    override fun getItemCount(): Int {
        return dataSource.size
    }
}