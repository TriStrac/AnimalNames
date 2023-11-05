package com.busal.animal.midterm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import com.busal.animal.midterm.databinding.ActivityManageBlockBinding

class ManageBlockActivity : AppCompatActivity() {
    private lateinit var binding:ActivityManageBlockBinding
    private lateinit var animalList:List<AnimalClass>
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityManageBlockBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        animalList = saved(this).animalList
        displayAnimalNames()

        binding.managelockAnimalsBackButton.setOnClickListener {
            finish()
        }

        binding.blockAnimalsList.setOnItemClickListener{_, _, position, _ ->
            val selectedAnimal = blockedList[position]
            selectedAnimal.status=true
            val animalList = saved(this).animalList.toMutableList()
            val index = animalList.indexOfFirst { it.name == selectedAnimal.name }
            if (index != -1) {
                animalList[index] = selectedAnimal
            }
            saved(this).animalList = animalList
            Toast.makeText(this, "${selectedAnimal.name} unblocked", Toast.LENGTH_SHORT).show()
            displayAnimalNames()
        }
    }
    private lateinit var blockedList:List<AnimalClass>

    private fun filterAnimals() {
        blockedList = animalList.filter { !it.status }
    }

    private fun displayAnimalNames() {
        animalList.sortedBy { it.name }
        filterAnimals()
        blockedList = blockedList.sortedBy { it.name }
        val filteredAnimals = blockedList.filter { !it.status }
        val filteredAnimalNames = filteredAnimals.map { it.name }
        val adapter = ArrayAdapter(
            this,
            R.layout.activity_blocked_list_view,
            R.id.animalNameTextViewList,
            filteredAnimalNames
        )
        binding.blockAnimalsList.adapter = adapter
    }
}