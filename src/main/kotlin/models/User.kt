package models

class User(
        val id: Long,
        val name: String,
        val mobile: String,
        val email: String,
        val address: String,
        val city: String,
        val country: String,
        val zip: String)