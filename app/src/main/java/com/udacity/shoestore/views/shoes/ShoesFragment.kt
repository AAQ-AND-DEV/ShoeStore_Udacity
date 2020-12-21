package com.udacity.shoestore.views.shoes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout.LayoutParams
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
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
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoes, container, false)

        viewModel.shoesList.observe(viewLifecycleOwner, {
            shoes->
            val shoesLL = binding.shoesLl
            for (shoe in shoes){
                //Attained programmatic cardview params from https://android--code.blogspot.com/2018/02/android-kotlin-create-cardview.html
                val cardView = CardView(requireContext())
                val layoutParams = LayoutParams(
                    LayoutParams.MATCH_PARENT, // CardView width
                    LayoutParams.WRAP_CONTENT // CardView height
                )
                layoutParams.bottomMargin = 8
                cardView.layoutParams = layoutParams
                cardView.radius = 12F
                cardView.setContentPadding(25, 25,25,25)
                cardView.elevation = 8F
                cardView.maxCardElevation = 12F

                val tv = TextView(requireContext())
                tv.text = resources.getString(R.string.shoe_item_desc,shoe.company,
                    shoe.name, shoe.size.toString(), shoe.description)

                cardView.addView(tv)
                shoesLL.addView(cardView)
            }
        })

        binding.newShoeButton.setOnClickListener {
            findNavController().navigate(ShoesFragmentDirections.actionShoesFragmentToShoeDetailFragment())
        }
        return binding.root
    }
}