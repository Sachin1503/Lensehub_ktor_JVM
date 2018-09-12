package db.datasource

import db.DBHelper
import models.User
import utils.QueryUtils
import java.sql.Connection
import java.sql.ResultSet

class UserDataSource(val connection: Connection?) {

    fun create(user: User) {
        val sql = QueryUtils.createQuery(DBHelper.TABLE_USER, getColumnForCreateOrUpdate())
        val preparedStatement = connection?.prepareStatement(sql)
        preparedStatement?.setString(1, user.name)
        preparedStatement?.setString(2, user.mobile)
        preparedStatement?.setString(3, user.email)
        preparedStatement?.setString(4, user.address)
        preparedStatement?.setString(5, user.city)
        preparedStatement?.setString(6, user.country)
        preparedStatement?.setString(7, user.zip)
        preparedStatement?.execute()
    }

    fun getUser(id: Long): User? {
        val sql = QueryUtils.selectQuery(DBHelper.TABLE_USER, DBHelper.USER_ID + " = " + id)
        val preparedStatement = connection?.prepareStatement(sql)
        val resultSet: ResultSet? = preparedStatement?.executeQuery()
        if (resultSet != null) {
            return moveToResultSet(resultSet)
        }
        return null
    }

    fun getAllUser(): ArrayList<User> {
        val users = ArrayList<User>()
        val sql = QueryUtils.selectQuery(DBHelper.TABLE_USER, null)
        val preparedStatement = connection?.prepareStatement(sql)
        val resultSet: ResultSet? = preparedStatement?.executeQuery()

        if (resultSet != null) {
            while (resultSet.next()) {
                users.add(moveToResultSet(resultSet))
            }

        }
        return users
    }

    fun update(user: User) {
        val sql = QueryUtils.updateQuery(DBHelper.TABLE_USER, getColumnForCreateOrUpdate(), DBHelper.USER_ID + " = " + user.id)
        val preparedStatement = connection?.prepareStatement(sql)
        preparedStatement?.setString(1, user.name)
        preparedStatement?.setString(2, user.mobile)
        preparedStatement?.setString(3, user.email)
        preparedStatement?.setString(4, user.address)
        preparedStatement?.setString(5, user.city)
        preparedStatement?.setString(6, user.country)
        preparedStatement?.setString(7, user.zip)
        preparedStatement?.execute()
    }

    fun delete(id: Long) {
        val sql = QueryUtils.deleteQuery(DBHelper.TABLE_USER, DBHelper.USER_ID + " = " + id)
        val preparedStatement = connection?.prepareStatement(sql)
        preparedStatement?.execute()
    }

    private fun getColumnForCreateOrUpdate(): Array<String> {
        return arrayOf(
                DBHelper.USER_NAME,
                DBHelper.USER_MOBILE_NUMBER,
                DBHelper.USER_EMAIL,
                DBHelper.USER_ADDRESS,
                DBHelper.USER_CITY,
                DBHelper.USER_COUNTRY,
                DBHelper.USER_ZIP)
    }

    private fun moveToResultSet(resultSet: ResultSet): User {
        val id = resultSet.getLong(DBHelper.USER_ID)
        val name = resultSet.getString(DBHelper.USER_NAME)
        val mobile = resultSet.getString(DBHelper.USER_MOBILE_NUMBER)
        val email = resultSet.getString(DBHelper.USER_EMAIL)
        val address = resultSet.getString(DBHelper.USER_ADDRESS)
        val city = resultSet.getString(DBHelper.USER_CITY)
        val country = resultSet.getString(DBHelper.USER_COUNTRY)
        val zip = resultSet.getString(DBHelper.USER_ZIP)
        return User(id, name, mobile, email, address, city, country, zip)
    }
}