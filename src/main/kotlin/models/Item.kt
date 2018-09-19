package models

class Item(
        var id: Long,
        var user: User?,
        var categoryName: String?,
        var lhImages: List<LHImage>?,
        var itemName: String,
        var brand: String,
        var model: String,
        var purchasedYear: String,
        var city: String,
        var rentPrice: Double
)