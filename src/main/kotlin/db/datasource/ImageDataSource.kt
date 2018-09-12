package db.datasource

import application.LHApplication
import db.DBHelper
import models.LHImage
import utils.QueryUtils
import java.sql.Connection
import java.sql.ResultSet

class ImageDataSource(val connection: Connection?, val lhApplication: LHApplication) {

    private fun getColumnForCreateOrUpdate(): Array<String> {
        return arrayOf(
                DBHelper.IMAGE_ITEM_ID,
                DBHelper.IMAGE_PATH,
                DBHelper.IMAGE_TYPE)
    }

    fun create(lhImage: LHImage) {
        val sql = QueryUtils.createQuery(DBHelper.TABLE_IMAGE, getColumnForCreateOrUpdate())
        val preparedStatement = connection?.prepareStatement(sql)
        preparedStatement?.setLong(1, lhImage.itemId)
        preparedStatement?.setString(2, lhImage.path)
        preparedStatement?.setInt(3, lhImage.type)
        preparedStatement?.execute()
    }

    fun getLHImage(id: Long): LHImage? {
        val sql = QueryUtils.selectQuery(DBHelper.TABLE_IMAGE, DBHelper.IMAGE_ID + " = " + id)
        val preparedStatement = connection?.prepareStatement(sql)
        val resultSet: ResultSet? = preparedStatement?.executeQuery()
        if (resultSet != null) {
            return moveToResultSet(resultSet)
        }
        return null
    }

    fun getAllLHImage(): ArrayList<LHImage> {
        val lhImages = ArrayList<LHImage>()
        val sql = QueryUtils.selectQuery(DBHelper.TABLE_IMAGE, null)
        val preparedStatement = connection?.prepareStatement(sql)
        val resultSet: ResultSet? = preparedStatement?.executeQuery()

        if (resultSet != null) {
            while (resultSet.next()) {
                lhImages.add(moveToResultSet(resultSet))
            }

        }
        return lhImages
    }

    fun getAllLHImageByItem(itemId: Long): ArrayList<LHImage> {
        val lhImages = ArrayList<LHImage>()
        val sql = QueryUtils.selectQuery(DBHelper.TABLE_IMAGE, DBHelper.IMAGE_ID + " = " + itemId)
        val preparedStatement = connection?.prepareStatement(sql)
        val resultSet: ResultSet? = preparedStatement?.executeQuery()

        if (resultSet != null) {
            while (resultSet.next()) {
                lhImages.add(moveToResultSet(resultSet))
            }

        }
        return lhImages
    }

    fun update(lhImage: LHImage) {
        val sql = QueryUtils.updateQuery(DBHelper.TABLE_IMAGE, getColumnForCreateOrUpdate(), DBHelper.IMAGE_ID + " = " + lhImage.id)
        val preparedStatement = connection?.prepareStatement(sql)
        preparedStatement?.setLong(1, lhImage.itemId)
        preparedStatement?.setString(2, lhImage.path)
        preparedStatement?.setInt(3, lhImage.type)
        preparedStatement?.execute()
    }

    fun delete(id: Long) {
        val sql = QueryUtils.deleteQuery(DBHelper.TABLE_IMAGE, DBHelper.IMAGE_ID + " = " + id)
        val preparedStatement = connection?.prepareStatement(sql)
        preparedStatement?.execute()
    }

    private fun moveToResultSet(resultSet: ResultSet): LHImage {
        val id = resultSet.getLong(DBHelper.IMAGE_ID)
        val itemId = resultSet.getLong(DBHelper.IMAGE_ITEM_ID)
        val path = resultSet.getString(DBHelper.IMAGE_PATH)
        val type = resultSet.getInt(DBHelper.IMAGE_TYPE)
        return LHImage(id, itemId, path, type)
    }
}