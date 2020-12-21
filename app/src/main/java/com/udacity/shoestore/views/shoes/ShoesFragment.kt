package com.udacity.shoestore.views.shoes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoesBinding

class ShoesFragment : Fragment() {

    private lateinit var binding: FragmentShoesBinding

    // Use the 'by activityViewModels()' Kotlin property delegate
    // from the fragment-ktx artifact
    private val viewModel : ShoesViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoes, container, false)

        viewModel.shoesList.observe(viewLifecycleOwner, Observer {
            shoes->
            val shoesLL = binding.shoesLl
            for (shoe in shoes){
                val newLL = LinearLayout(context)
                newLL.orientation = LinearLayout.VERTICAL
                val makeBrandTV = TextView(context)
                makeBrandTV.text = resources.getString(R.string.shoe_item_desc,shoe.company, shoe.name, shoe.size.toString())
                val descTV = TextView(context)
                descTV.text = shoe.description
                newLL.addView(makeBrandTV)
                newLL.addView(descTV)
                shoesLL.addView(newLL)
                val divider = View(context)
                divider.setBackgroundColor(0)
                divider.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 2)
                shoesLL.addView(divider)
            }
        })

        return binding.root
    }
}