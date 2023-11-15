package com.gl4.suivi_etudiant

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.Filter
import android.widget.Filterable
import android.widget.RadioButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.Locale

class StudentAdapter(private val context:Context,private var studentList: List<Student>) :
RecyclerView.Adapter<StudentAdapter.StudentViewHolder>(){
    inner class StudentViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.studentFirstLastName)
        val checkpresent: RadioButton = itemView.findViewById(R.id.radioButtonPresent)
        val checkabsent: RadioButton = itemView.findViewById(R.id.radioButtonAbsent)
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): StudentAdapter.StudentViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.student_item, parent, false)
        return StudentViewHolder(view)
    }

    override fun onBindViewHolder(holder: StudentAdapter.StudentViewHolder, position: Int) {
        val currentStudent = studentList[position]
        holder.checkpresent.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                currentStudent.state = Student.State.PRESENT
            }
        }

        holder.checkabsent.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                currentStudent.state = Student.State.ABSENT
            }
        }
        holder.name.text = currentStudent.lastName+" "+currentStudent.firstName
        val state = currentStudent.state
        when(state){
            Student.State.PRESENT ->{
                holder.checkpresent.isChecked = true
                holder.checkabsent.isChecked = false
            }
            Student.State.ABSENT ->{
                holder.checkpresent.isChecked = false
                holder.checkabsent.isChecked = true
            }
            else -> {
                holder.checkpresent.isChecked = false
                holder.checkabsent.isChecked = false
            }
        }

    }

    override fun getItemCount(): Int {
        return studentList.size
    }

}