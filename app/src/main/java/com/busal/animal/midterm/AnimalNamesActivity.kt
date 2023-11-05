package com.busal.animal.midterm

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import com.busal.animal.midterm.databinding.ActivityAnimalNamesBinding

class AnimalNamesActivity : AppCompatActivity() {
    private lateinit var binding:ActivityAnimalNamesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityAnimalNamesBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        if(saved(this).isSaved){
            initializeAnimals()
            saved(this).isSaved=false
            saveAnimalStatus()
        }
        animalList = saved(this).animalList
        displayAnimalNames()

        binding.animalNamesList.setOnItemClickListener { _, _, position, _ ->
            val intent = Intent(this, AnimalDetailsActivity::class.java)
            intent.putExtra("PARAM_ANIMAL", unblockedList[position])
            startActivity(intent)
        }

        binding.manageBlockAnimalsButton.setOnClickListener{
            val intent = Intent(this,ManageBlockActivity::class.java)
            startActivity(intent)
        }

    }

    override fun onResume() {
        super.onResume()
        animalList = saved(this).animalList
        displayAnimalNames()
    }


    private lateinit var animalList:List<AnimalClass>
    private fun initializeAnimals(){
        animalList = listOf(
            AnimalClass(getString(R.string.animal2),getString(R.string.animal2Details),false),
            AnimalClass(getString(R.string.animal1),getString(R.string.animal1Details),false),
            AnimalClass(getString(R.string.animal4),getString(R.string.animal4Details),false),
            AnimalClass(getString(R.string.animal3),getString(R.string.animal3Details),true),
            AnimalClass(getString(R.string.animal5),getString(R.string.animal5Details),true),
            AnimalClass(getString(R.string.animal6),getString(R.string.animal6Details),true),
            AnimalClass(getString(R.string.animal7),getString(R.string.animal7Details),true),
            AnimalClass(getString(R.string.animal8),getString(R.string.animal8Details),false),
            AnimalClass(getString(R.string.animal9),getString(R.string.animal9Details),true),
            AnimalClass(getString(R.string.animal10),getString(R.string.animal10Details),true),
            AnimalClass(getString(R.string.animal11),getString(R.string.animal11Details),true),
            AnimalClass(getString(R.string.animal12),getString(R.string.animal12Details),true),
            AnimalClass(getString(R.string.animal13),getString(R.string.animal13Details),true),
            AnimalClass(getString(R.string.animal14),getString(R.string.animal14Details),true),
            AnimalClass(getString(R.string.animal15),getString(R.string.animal15Details),true),
        )
    }

    private lateinit var unblockedList:List<AnimalClass>
    private lateinit var blockedList:List<AnimalClass>

    private fun filterAnimals() {
        unblockedList = animalList.filter { it.status }
        blockedList = animalList.filter { !it.status }
    }

    private fun displayAnimalNames() {
        animalList.sortedBy { it.name }
        filterAnimals()
        unblockedList = unblockedList.sortedBy { it.name }
        val filteredAnimals = unblockedList.filter { it.status }
        val filteredAnimalNames = filteredAnimals.map { it.name }
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            filteredAnimalNames
        )

        binding.animalNamesList.adapter = adapter
    }

    private fun saveAnimalStatus() {
        saved(this).animalList = animalList
    }
}