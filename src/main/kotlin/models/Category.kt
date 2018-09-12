package models

class Category(
        val id: Long,
        val categoryName: String,
        val itemList: ArrayList<Item>?
)