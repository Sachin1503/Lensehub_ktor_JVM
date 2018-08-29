package db

import java.sql.Connection
import java.sql.DriverManager

class DBConnection {

     companion object {

        private var INSTANCE:Connection? = null

        fun getConnection(): Connection? =
                INSTANCE ?: synchronized(this) {
                    INSTANCE ?: connect().also { INSTANCE = it }
                }


        private fun connect(): Connection? {

                //SQlite connection string
                val connectionURL = "jdbc:sqlite:dbFile/test.db"

                try {

                    //Register JDBC Driver
                    System.out.println("Registering JDBC driver...");
                    Class.forName("org.sqlite.JDBC");

                    System.out.println("Connecting to database...");
                    INSTANCE = DriverManager.getConnection(connectionURL)

                    System.out.println("The driver name is " + INSTANCE?.metaData?.driverName)
                    System.out.println("Database has been created")

                } catch (ex: Exception) {
                    System.out.println("Exception is" + ex.message)
                }
            return INSTANCE
        }
    }


}

