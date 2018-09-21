package models

class Category(
        val id: Long,
        val categoryKey: Int,
        val categoryValue: String,
        val itemList: ArrayList<Item>?
)