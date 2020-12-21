package com.udacity.shoestore.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Shoe(var name: String, var size: Double, var company: String, var description: String,
                val images: List<String> = mutableListOf()) : Parcelable{

    override fun equals(other: Any?): Boolean {
        val otherShoe = other as Shoe
        return (this.name==otherShoe.name && this.company==otherShoe.company
                && this.size == otherShoe.size && this.description == otherShoe.description)
    }
                }