package com.gl4.suivi_etudiant

class Student(val firstName: String, val lastName: String, var state: State) {
    enum class State {
        PRESENT,
        ABSENT
    }
}