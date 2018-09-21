package db.datasource

import application.LHApplication
import db.DBHelper
import models.Category
import models.Item
import utils.QueryUtils
import java.sql.Connection
import java.sql.ResultSet

class CategoryDataSource(val connection: Connection?, val lhApplication: LHApplication) {

    private fun getColumnForCreateOrUpdate(): Array<String> {
        return arrayOf(
                DBHelper.CATEGORY_KEY,
                DBHelper.CATEGORY_VALUE)
    }

    fun create(category: Category) {
        val sql = QueryUtils.createQuery(DBHelper.TABLE_CATEGORY, getColumnForCreateOrUpdate())
        val preparedStatement = connection?.prepareStatement(sql)
        preparedStatement?.setInt(1, category.categoryKey)
        preparedStatement?.setString(2, category.categoryValue)
        preparedStatement?.execute()
    }

    fun getCategory(key: Int, city: String?): Category? {
        val sql = QueryUtils.selectQuery(DBHelper.TABLE_CATEGORY, DBHelper.CATEGORY_ID + " = " + key)
        val preparedStatement = connection?.prepareStatement(sql)
        val resultSet: ResultSet? = preparedStatement?.executeQuery()
        if (resultSet != null) {
            return moveToResultSet(resultSet,city)
        }
        return null
    }

    fun getCategories(itemsCity: String?): ArrayList<Category> {
        val categorys = ArrayList<Category>()
        val sql = QueryUtils.selectQuery(DBHelper.TABLE_CATEGORY, null)
        val preparedStatement = connection?.prepareStatement(sql)
        val resultSet: ResultSet? = preparedStatement?.executeQuery()

        if (resultSet != null) {
            while (resultSet.next()) {
                categorys.add(moveToResultSet(resultSet,itemsCity))
            }

        }
        return categorys
    }

    fun getCategoryStings(): ArrayList<String> {
        val categorys = ArrayList<String>()
        val sql = QueryUtils.selectQuery(DBHelper.TABLE_CATEGORY, null)
        val preparedStatement = connection?.prepareStatement(sql)
        val resultSet: ResultSet? = preparedStatement?.executeQuery()

        if (resultSet != null) {
            while (resultSet.next()) {
                categorys.add(resultSet.getString(DBHelper.CATEGORY_VALUE))
            }

        }
        return categorys
    }

    fun update(category: Category) {
        val sql = QueryUtils.updateQuery(DBHelper.TABLE_CATEGORY, getColumnForCreateOrUpdate(), DBHelper.CATEGORY_ID + " = " + category.id)
        val preparedStatement = connection?.prepareStatement(sql)
        preparedStatement?.setString(1, category.categoryValue)
        preparedStatement?.execute()
    }

    fun delete(id: Long) {
        val sql = QueryUtils.deleteQuery(DBHelper.TABLE_CATEGORY, DBHelper.CATEGORY_ID + " = " + id)
        val preparedStatement = connection?.prepareStatement(sql)
        preparedStatement?.execute()
    }

    private fun moveToResultSet(resultSet: ResultSet,city:String?): Category {
        val id = resultSet.getLong(DBHelper.CATEGORY_ID)
        val key = resultSet.getInt(DBHelper.CATEGORY_KEY)
        val value = resultSet.getString(DBHelper.CATEGORY_VALUE)
        val items: ArrayList<Item>? = city?.let { lhApplication.itemDataSource?.getItems(key, it) }
        return Category(id, key,value, items)
    }
}