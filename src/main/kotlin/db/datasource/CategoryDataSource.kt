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
                DBHelper.CATEGORY_NAME)
    }

    fun create(category: Category) {
        val sql = QueryUtils.createQuery(DBHelper.TABLE_CATEGORY, getColumnForCreateOrUpdate())
        val preparedStatement = connection?.prepareStatement(sql)
        preparedStatement?.setString(1, category.categoryName)
        preparedStatement?.execute()
    }

    fun getCategory(id: Long, city: String?): Category? {
        val sql = QueryUtils.selectQuery(DBHelper.TABLE_CATEGORY, DBHelper.CATEGORY_ID + " = " + id)
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
                categorys.add(resultSet.getString(DBHelper.CATEGORY_NAME))
            }

        }
        return categorys
    }

    fun update(category: Category) {
        val sql = QueryUtils.updateQuery(DBHelper.TABLE_CATEGORY, getColumnForCreateOrUpdate(), DBHelper.CATEGORY_ID + " = " + category.id)
        val preparedStatement = connection?.prepareStatement(sql)
        preparedStatement?.setString(1, category.categoryName)
        preparedStatement?.execute()
    }

    fun delete(id: Long) {
        val sql = QueryUtils.deleteQuery(DBHelper.TABLE_CATEGORY, DBHelper.CATEGORY_ID + " = " + id)
        val preparedStatement = connection?.prepareStatement(sql)
        preparedStatement?.execute()
    }

    private fun moveToResultSet(resultSet: ResultSet,city:String?): Category {
        val id = resultSet.getLong(DBHelper.CATEGORY_ID)
        val name = resultSet.getString(DBHelper.CATEGORY_NAME)
        val items: ArrayList<Item>? = city?.let { lhApplication.itemDataSource?.getItems(id, it) }
        return Category(id, name, items)
    }
}