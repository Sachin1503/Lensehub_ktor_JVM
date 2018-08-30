import db.DBConnection
import db.DBHelper
import db.UserDataSource

class Application{

    private val connection = DBConnection.getConnection()
    var userDataSource:UserDataSource? = null

    fun init(){

        userDataSource = UserDataSource(connection)

        createTables()
    }

    private fun createTables(){
        connection?.createStatement()?.execute(DBHelper.CREATE_TABLE_USER)
    }
}