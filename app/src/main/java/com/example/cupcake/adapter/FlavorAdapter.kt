package com.example.cupcake.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.cupcake.ButtonFlavorFragment
import com.example.cupcake.R
import com.example.cupcake.data.DataSource
import com.example.cupcake.databinding.FragmentButtonFlavorBinding
import com.example.cupcake.model.OrderViewModel

class FlavorAdapter(private val viewModel: OrderViewModel): RecyclerView.Adapter<FlavorAdapter.FlavorViewHolder>() {

    private val dataSource = DataSource.flavors
    class FlavorViewHolder(view: View?): RecyclerView.ViewHolder(view!!) {
        var nameFragment : TextView = view!!.findViewById(R.id.flavorName)
        var numberCupcake : TextView = view!!.findViewById(R.id.numberCupCake)
        var minusButton : Button = view!!.findViewById(R.id.minus)
        var plusButton : Button = view!!.findViewById(R.id.plus)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): FlavorViewHolder {
        val binding = FragmentButtonFlavorBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FlavorViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: FlavorViewHolder, position: Int) {
        val data = dataSource[position]
        holder.nameFragment.text = data.name
        holder.numberCupcake.text = data.number.toString()

        if(data.number == 0 ){
            holder.minusButton.isEnabled = false
        } else if(data.number > 0){
            holder.minusButton.isEnabled = true
        }

        holder.plusButton.setOnClickListener{
            viewModel.incrementCounterCupcake(position)
            holder.numberCupcake.text = data.number.toString()
        }
        holder.minusButton.setOnClickListener{
            viewModel.decrementCounterCupcake(position)
            holder.numberCupcake.text = data.number.toString()
        }
    }

    override fun getItemCount(): Int {
        return dataSource.size
    }
}