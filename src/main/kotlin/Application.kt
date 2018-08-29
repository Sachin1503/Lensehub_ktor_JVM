import db.DBConnection
import db.DBHelper
import db.UserDataSource

class Application{

    val connection = DBConnection.getConnection()
    var userDataSource:UserDataSource? = null

    fun init(){

        userDataSource = UserDataSource(connection)

        createTables()
    }

    private fun createTables(){
        connection?.createStatement()?.execute(DBHelper.CRETAE_TABLE_USER)
    }
}