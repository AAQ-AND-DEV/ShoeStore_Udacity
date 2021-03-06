package com.udacity.shoestore.views.shoes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe


class ShoesViewModel : ViewModel() {

    private val backShoeList = mutableListOf<Shoe>()
    private val _shoesList = MutableLiveData<List<Shoe>>()
    val shoesList: LiveData<List<Shoe>>
        get() = _shoesList

    private val _loggedIn = MutableLiveData<Boolean>()
    val loggedIn: LiveData<Boolean>
        get() = _loggedIn

    init{
        _shoesList.value = createList()
        _loggedIn.value = false
    }

    fun createList(): List<Shoe>{


        val pumaTread = Shoe("Treaders", 12.0, "Puma", "comfortable x-trainer", mutableListOf("img") )
        val nikeCross = Shoe("Cross-Fit", 9.5, "Nike", "great for cross-fit", mutableListOf("img"))
        val adidasYups = Shoe("Yups", 11.0, "Adidas", "Perfect for yupping", mutableListOf("img"))
        val reebokDoos = Shoe("Doos", 7.5, "Reebok", "Amazing for doing things", mutableListOf("img"))
        val boots = Shoe("Boots", 10.5, "Boots4U", "Boots4U Boots are Bootiful!", mutableListOf("img"))
        val heels = Shoe("Heels", 7.0, "Heel Factory", "Just great heels", mutableListOf("img"))
        val clogs = Shoe("Clogs", 14.5, "Big Clogs", "Big Clogs has the clogs for you", mutableListOf("img"))

        backShoeList.add(pumaTread)
        backShoeList.add(nikeCross)
        backShoeList.add(adidasYups)
        backShoeList.add(reebokDoos)
        backShoeList.add(boots)
        backShoeList.add(heels)
        backShoeList.add(clogs)

        return backShoeList
    }
    fun addShoe(shoe: Shoe) {
        backShoeList.add(shoe)
        _shoesList.value = backShoeList
    }

    override fun onCleared() {
        super.onCleared()
    }

    fun logOut(){
        _loggedIn.value = false
    }

    fun logIn(){
        _loggedIn.value = true
    }
}