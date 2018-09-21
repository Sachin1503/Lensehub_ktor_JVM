package db

import models.Category

object DBHelper {

    //User
    const val TABLE_USER = "user"
    const val USER_ID = "id"
    const val USER_NAME = "name"
    const val USER_MOBILE_NUMBER = "mobile"
    const val USER_EMAIL = "email"
    const val USER_ADDRESS = "address"
    const val USER_CITY = "city"
    const val USER_COUNTRY = "country"
    const val USER_ZIP = "zip"

    //Items
    const val TABLE_ITEM = "item"
    const val ITEM_ID = "id"
    const val ITEM_USER_ID = "userId"
    const val ITEM_CATEGORY_KEY = "categoryKey"
    const val ITEM_NAME = "itemName"
    const val ITEM_BRAND = "itemBrand"
    const val ITEM_MODEL = "itemModel"
    const val ITEM_PURCHASED_YEAR = "purchasedYear"
    const val ITEM_CITY = "city"
    const val ITEM_RENT_PRICE = "rentPrice"
    const val ITEM_RENT_UNIT = "rentPriceUnit"
    const val ITEM_RENT_CURRENCY = "rentPriceCurrency"

    //Category
    const val TABLE_CATEGORY = "category"
    const val CATEGORY_ID = "id"
    const val CATEGORY_KEY = "categoryKey"
    const val CATEGORY_VALUE = "categoryValue"

    //Category
    const val TABLE_IMAGE = "image"
    const val IMAGE_ID = "id"
    const val IMAGE_ITEM_ID = "itemId"
    const val IMAGE_PATH = "path"
    const val IMAGE_TYPE = "type"


    const val CREATE_TABLE_USER = "CREATE TABLE IF NOT EXISTS " + TABLE_USER + " ( " +
            USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            USER_NAME + " TEXT, " +
            USER_MOBILE_NUMBER + " TEXT, " +
            USER_EMAIL + " TEXT, " +
            USER_ADDRESS + " TEXT, " +
            USER_CITY + " TEXT, " +
            USER_COUNTRY + " TEXT, " +
            USER_ZIP + " TEXT " + " ) "

    const val CREATE_TABLE_ITEM = "CREATE TABLE IF NOT EXISTS " + TABLE_ITEM + " ( " +
            ITEM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            ITEM_USER_ID + " INTEGER, " +
            ITEM_CATEGORY_KEY + " INTEGER, " +
            ITEM_NAME + " TEXT, " +
            ITEM_BRAND + " TEXT, " +
            ITEM_MODEL + " TEXT, " +
            ITEM_PURCHASED_YEAR + " TEXT, " +
            ITEM_CITY + " TEXT, " +
            ITEM_RENT_PRICE + " REAL, " +
            ITEM_RENT_UNIT + " INTEGER, " +
            ITEM_RENT_CURRENCY + " INTEGER " + " ) "

    const val CREATE_TABLE_CATEGORY = "CREATE TABLE IF NOT EXISTS " + TABLE_CATEGORY + " ( " +
            CATEGORY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            CATEGORY_KEY + " INTEGER , " +
            CATEGORY_VALUE + " TEXT " + " ) "

    const val CREATE_TABLE_IMAGE = "CREATE TABLE IF NOT EXISTS " + TABLE_IMAGE + " ( " +
            IMAGE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            IMAGE_ITEM_ID + " INTEGER, " +
            IMAGE_PATH + " INTEGER , " +
            IMAGE_TYPE + " INTEGER " + " ) "

    val MASTER_CATEGORIES_LIST = arrayOf(
            Category(-1,1,"Camera",null),
            Category(-1,2,"Lens",null),
            Category(-1,3,"Lights",null),
            Category(-1,4,"Equipments",null),
            Category(-1,5,"Audio Sound",null),
            Category(-1,6,"Monitors",null),
            Category(-1,7,"Cases and Bags",null),
            Category(-1,8,"Filters",null))
}