package com.gl4.suivi_etudiant

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CheckBox
import android.widget.RadioButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    val studentList = arrayListOf<Student>(
        Student("Emna", "Gaidi", Student.State.PRESENT),
        Student("Hamza", "Ben Ammar", Student.State.PRESENT),
        Student("Rasslen", "Ansi", Student.State.ABSENT),
        Student("Ranya", "Boubich", Student.State.ABSENT),
        Student("Arij", "Kouki", Student.State.PRESENT),
        Student("Safa", "Oueslati", Student.State.ABSENT),
        Student("Fatma", "Gaidi", Student.State.PRESENT),
        Student("Hatem", "Ben Ammar", Student.State.PRESENT),
        Student("Saif", "Hamza", Student.State.ABSENT),
        Student("Rafa", "Boubich", Student.State.ABSENT),
        Student("Aymen", "Kouki", Student.State.PRESENT),
        Student("Dhia", "Oueslati", Student.State.ABSENT)
    )
    lateinit var recyclerView: RecyclerView
    lateinit var checkPresent: CheckBox
    lateinit var checkAbsent: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        checkPresent = findViewById(R.id.checkBox)
        checkAbsent = findViewById(R.id.checkBox2)

        setupCheckBoxListeners()
    }

    private fun setupCheckBoxListeners() {
        checkPresent.setOnCheckedChangeListener { _, isChecked ->
            updateRecyclerView(isChecked, checkAbsent.isChecked)
        }

        checkAbsent.setOnCheckedChangeListener { _, isChecked ->
            updateRecyclerView(checkPresent.isChecked, isChecked)
        }
    }

    private fun updateRecyclerView(isPresentChecked: Boolean, isAbsentChecked: Boolean) {
        val filteredList = when {
            isPresentChecked && isAbsentChecked -> studentList
            isPresentChecked -> filterStudentsByState(Student.State.PRESENT)
            isAbsentChecked -> filterStudentsByState(Student.State.ABSENT)
            else -> emptyList()
        }

        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = StudentAdapter(this@MainActivity, filteredList)
        }
    }

    private fun filterStudentsByState(state: Student.State): List<Student> {
        return studentList.filter { it.state == state }
    }
}

