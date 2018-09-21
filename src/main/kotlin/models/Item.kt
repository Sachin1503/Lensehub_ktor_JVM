package models

import java.util.*

class Item(
        var id: Long,
        var userId: Long,
        var categoryKey: Int,
        var lhImages: List<Long>?,
        var itemName: String,
        var brand: String,
        var model: String,
        var purchasedYear: String,
        var city: String,
        var rentPrice: Double,
        var rentPriceUnit: Int,
        var rentPriceCurrency: Int
)