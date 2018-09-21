package db.datasource

import application.LHApplication
import db.DBHelper
import models.Item
import models.User
import utils.QueryUtils
import java.sql.Connection
import java.sql.ResultSet

class ItemDataSource(private val connection: Connection?, private val lhApplication: LHApplication) {

    private fun getColumnsForCreateOrUpdate(): Array<String> {
        return arrayOf(DBHelper.ITEM_USER_ID,
                DBHelper.ITEM_CATEGORY_KEY,
                DBHelper.ITEM_NAME,
                DBHelper.ITEM_BRAND,
                DBHelper.ITEM_MODEL,
                DBHelper.ITEM_PURCHASED_YEAR,
                DBHelper.ITEM_CITY,
                DBHelper.ITEM_RENT_PRICE,
                DBHelper.ITEM_RENT_UNIT,
                DBHelper.ITEM_RENT_CURRENCY)
    }

    fun create(item: Item) {
        val sql = QueryUtils.createQuery(DBHelper.TABLE_ITEM, getColumnsForCreateOrUpdate())
        val preparedStatement = connection?.prepareStatement(sql)
        preparedStatement?.setLong(1, item.userId)
        preparedStatement?.setInt(2, item.categoryKey)
        preparedStatement?.setString(3, item.itemName)
        preparedStatement?.setString(4, item.brand)
        preparedStatement?.setString(5, item.model)
        preparedStatement?.setString(6, item.purchasedYear)
        preparedStatement?.setString(7, item.city)
        preparedStatement?.setDouble(8, item.rentPrice)
        preparedStatement?.setInt(9, item.rentPriceUnit)
        preparedStatement?.setInt(10, item.rentPriceCurrency)
        preparedStatement?.execute()
    }

    fun getItem(id: Long): Item? {
        val sql = QueryUtils.selectQuery(DBHelper.TABLE_ITEM, DBHelper.ITEM_ID + " = " + id)
        val preparedStatement = connection?.prepareStatement(sql)
        val resultSet: ResultSet? = preparedStatement?.executeQuery()
        if (resultSet != null) {
            return moveToResultSet(resultSet)
        }
        return null
    }

    fun getAllItem(): ArrayList<Item> {
        val items = ArrayList<Item>()
        val sql = QueryUtils.selectQuery(DBHelper.TABLE_ITEM, null)
        val preparedStatement = connection?.prepareStatement(sql)
        val resultSet: ResultSet? = preparedStatement?.executeQuery()

        if (resultSet != null) {
            while (resultSet.next()) {
                items.add(moveToResultSet(resultSet))
            }

        }
        return items
    }

    fun getItems(categoryKey: Int, city:String): ArrayList<Item> {
        val items = ArrayList<Item>()
        val sql = QueryUtils.selectQuery(DBHelper.TABLE_ITEM, DBHelper.ITEM_CATEGORY_KEY + " = " + categoryKey+" AND "+DBHelper.ITEM_CITY + " = '" + city+"'")
        val preparedStatement = connection?.prepareStatement(sql)
        val resultSet: ResultSet? = preparedStatement?.executeQuery()

        if (resultSet != null) {
            while (resultSet.next()) {
                items.add(moveToResultSet(resultSet))
            }

        }
        return items
    }

    fun getItems(city:String): ArrayList<Item> {
        val items = ArrayList<Item>()
        val sql = QueryUtils.selectQuery(DBHelper.TABLE_ITEM, DBHelper.ITEM_CITY + " = " + city)
        val preparedStatement = connection?.prepareStatement(sql)
        val resultSet: ResultSet? = preparedStatement?.executeQuery()

        if (resultSet != null) {
            while (resultSet.next()) {
                items.add(moveToResultSet(resultSet))
            }

        }
        return items
    }

    fun update(item: Item) {
        val sql = QueryUtils.createQuery(DBHelper.TABLE_ITEM, getColumnsForCreateOrUpdate())
        val preparedStatement = connection?.prepareStatement(sql)
        preparedStatement?.setLong(1, item.userId)
        preparedStatement?.setInt(2, item.categoryKey)
        preparedStatement?.setString(3, item.itemName)
        preparedStatement?.setString(4, item.brand)
        preparedStatement?.setString(5, item.model)
        preparedStatement?.setString(6, item.purchasedYear)
        preparedStatement?.setString(7, item.city)
        preparedStatement?.setDouble(8, item.rentPrice)
        preparedStatement?.setInt(9, item.rentPriceUnit)
        preparedStatement?.setInt(10, item.rentPriceCurrency)
        preparedStatement?.execute()
    }

    fun delete(id: Long) {
        val sql = QueryUtils.deleteQuery(DBHelper.TABLE_ITEM, DBHelper.ITEM_ID + " = " + id)
        val preparedStatement = connection?.prepareStatement(sql)
        preparedStatement?.execute()
    }

    private fun moveToResultSet(resultSet: ResultSet): Item {
        val id = resultSet.getLong(DBHelper.ITEM_ID)
        val userId = resultSet.getLong(DBHelper.ITEM_USER_ID)
        val categoryKey = resultSet.getInt(DBHelper.ITEM_CATEGORY_KEY)
        val name = resultSet.getString(DBHelper.ITEM_NAME)
        val brand = resultSet.getString(DBHelper.ITEM_BRAND)
        val model = resultSet.getString(DBHelper.ITEM_MODEL)
        val purchasedYear = resultSet.getString(DBHelper.ITEM_PURCHASED_YEAR)
        val city = resultSet.getString(DBHelper.ITEM_CITY)
        val rentPrice = resultSet.getDouble(DBHelper.ITEM_RENT_PRICE)
        val rentPriceUnit = resultSet.getInt(DBHelper.ITEM_RENT_PRICE)
        val rentPriceCurrency = resultSet.getInt(DBHelper.ITEM_RENT_PRICE)

        val images : ArrayList<Long>? = lhApplication.lhImageDataSource?.getAllLHImageIdsByItem(id)

        return Item(id, userId, categoryKey, images, name, brand, model, purchasedYear, city, rentPrice,rentPriceUnit,rentPriceCurrency)
    }
}