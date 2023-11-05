package com.busal.animal.midterm

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.busal.animal.midterm.databinding.ActivityAnimalDetailsBinding

class AnimalDetailsActivity : AppCompatActivity() {
    private lateinit var binding:ActivityAnimalDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityAnimalDetailsBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val selectedAnimal = intent.getSerializableExtra("PARAM_ANIMAL") as AnimalClass
        binding.animalNameTextView.text = selectedAnimal.name
        binding.animalDetailsTextView.text = selectedAnimal.details

        binding.animalDetailsBackButton.setOnClickListener{
            finish()
        }
        binding.blockAnimalButton.setOnClickListener {
            selectedAnimal.status=false
            val animalList = saved(this).animalList.toMutableList()
            val index = animalList.indexOfFirst { it.name == selectedAnimal.name }
            if (index != -1) {
                animalList[index] = selectedAnimal
            }
            saved(this).animalList = animalList
            Toast.makeText(this, "${selectedAnimal.name} blocked", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}