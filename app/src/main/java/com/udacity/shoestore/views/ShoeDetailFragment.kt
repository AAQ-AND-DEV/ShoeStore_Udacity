package com.udacity.shoestore.views

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.views.shoes.ShoesViewModel
import java.lang.NumberFormatException

class ShoeDetailFragment : Fragment() {

    private lateinit var binding: FragmentShoeDetailBinding
    private val viewModel: ShoesViewModel by activityViewModels()
    private lateinit var newShoe: Shoe
    private val emptyShoe = Shoe("",0.0,"","", mutableListOf(""))

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_shoe_detail, container, false
        )

        binding.detailFragment = this
        newShoe = Shoe("",0.0,"","", mutableListOf(""))
        binding.shoe = newShoe

        binding.lifecycleOwner = this

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        menu.clear()
        super.onPrepareOptionsMenu(menu)
    }

    fun addShoe() {
//        val shoe : Shoe?
//        var size : Double
//        if (binding.nameEt.text.isNotBlank() && binding.sizeEt.text.isNotBlank() &&
//            binding.brandEt.text.isNotBlank() && binding.descEt.text.isNotBlank()
//        ) {
//            size = try{
//                binding.sizeEt.text.toString().toDouble()
//            } catch(e: NumberFormatException){
//                e.printStackTrace()
//                0.0
//            }
//
//            shoe = Shoe(
//                binding.nameEt.text.toString(),
//                size,
//                binding.brandEt.text.toString(),
//                binding.descEt.text.toString(),
//                mutableListOf("img")
//            )
//        } else{
//            shoe = null
//            Toast.makeText(context, "Must enter values for shoe", Toast.LENGTH_LONG).show()
//        }
        hideKeyboard()
        if (newShoe != emptyShoe) {
            viewModel.addShoe(newShoe)
            findNavController().navigate(ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoesFragment())
        } else{
            Toast.makeText(context, "Must enter values for shoe properties", Toast.LENGTH_LONG).show()
        }

    }

    fun hideKeyboard(){
        val imm = context?.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.brandEt.windowToken, 0)
    }
}