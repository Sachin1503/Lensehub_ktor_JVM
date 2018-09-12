package application

import db.DBConnection
import db.DBHelper
import db.datasource.CategoryDataSource
import db.datasource.ImageDataSource
import db.datasource.ItemDataSource
import db.datasource.UserDataSource

class LHApplication() {

    private val connection = DBConnection.getConnection()
    var userDataSource: UserDataSource? = null
    var itemDataSource: ItemDataSource? = null
    var categoryDataSource: CategoryDataSource? = null
    var lhImageDataSource: ImageDataSource? = null

    init {
        userDataSource = UserDataSource(connection)
        itemDataSource = ItemDataSource(connection, this)
        categoryDataSource = CategoryDataSource(connection, this)
        lhImageDataSource = ImageDataSource(connection, this)

        createTables()
    }

    private fun createTables() {
        connection?.createStatement()?.execute(DBHelper.CREATE_TABLE_USER)
        connection?.createStatement()?.execute(DBHelper.CREATE_TABLE_ITEM)
        connection?.createStatement()?.execute(DBHelper.CREATE_TABLE_CATEGORY)
        connection?.createStatement()?.execute(DBHelper.CREATE_TABLE_IMAGE)
    }
}