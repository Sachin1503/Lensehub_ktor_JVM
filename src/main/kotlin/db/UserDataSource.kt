package db

import models.User
import java.sql.Connection

class UserDataSource(val connection: Connection?){

    fun insert(user:String){
        val sql = "INSERT INTO "+DBHelper.TABLE_USER+ " ( "+
                DBHelper.USER_NAME+" ) " +"VALUES(?)"

        val preparedStatement = connection?.prepareStatement(sql)
        preparedStatement?.setString(1,user)
        preparedStatement?.execute()
    }
}