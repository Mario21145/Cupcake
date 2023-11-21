package com.example.cupcake.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.cupcake.ButtonFlavorFragment
import com.example.cupcake.R
import com.example.cupcake.data.DataSource
import com.example.cupcake.model.OrderViewModel

class FlavorAdapter(private val viewModel: OrderViewModel): RecyclerView.Adapter<FlavorAdapter.FlavorViewHolder>() {

    private val dataSource = DataSource.flavors
    class FlavorViewHolder(view: View?): RecyclerView.ViewHolder(view!!) {
        var nameFragment : TextView = view!!.findViewById(R.id.flavorName)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): FlavorViewHolder {
        val button = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_button_flavor, parent, false)
        return FlavorViewHolder(button)
    }

    override fun onBindViewHolder(holder: FlavorAdapter.FlavorViewHolder, position: Int) {
        val nameFlavor = dataSource[position]

        holder.nameFragment.text = nameFlavor.name
    }

    override fun getItemCount(): Int {
        return dataSource.size
    }
}