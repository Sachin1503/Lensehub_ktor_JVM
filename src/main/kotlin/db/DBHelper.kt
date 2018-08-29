package db

object DBHelper{

    const val TABLE_USER = "user"
    const val USER_ID = "id"
    const val USER_NAME = "name"
    const val USER_MOBILE_NUMBER = "mobile"
    const val USER_EMAIL = "email"
    const val USER_ADDRESS ="address"
    const val USER_CITY ="city"
    const val USER_COUNTRY ="country"
    const val USER_ZIP ="zip"


    const val CRETAE_TABLE_USER = "CREATE TABLE IF NOT EXISTS " + TABLE_USER + " ( " +
            USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
            USER_NAME + " TEXT "+
            USER_MOBILE_NUMBER + " TEXT "+
            USER_EMAIL + " TEXT "+
            USER_ADDRESS + " TEXT "+
            USER_CITY + " TEXT "+
            USER_COUNTRY + " TEXT "+
            USER_ZIP + " TEXT "+" ) "


}