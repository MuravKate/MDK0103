package com.example.pr01.data

data class Profile(
    val id: String? = null,
    var firstName: String = "",
    var lastName: String = "",
    var email: String = "",
    var password: String = "",
    var passwordConfirm: String = ""
)
