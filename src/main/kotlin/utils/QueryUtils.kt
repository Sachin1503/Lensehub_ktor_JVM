package utils

class QueryUtils() {

    companion object {

        fun createQuery(tableName: String, columnNames: Array<String>): String {
            val sqlBuffer: StringBuffer = StringBuffer()

            sqlBuffer.append(" INSERT INTO ")
            sqlBuffer.append(tableName)
            sqlBuffer.append(" ( ")
            var count = 0;
            for (column in columnNames) {
                count++
                sqlBuffer.append(column)
                if (count != columnNames.size) {
                    sqlBuffer.append(",")
                }
            }
            count = 0;
            sqlBuffer.append(" ) ")
            sqlBuffer.append("VALUES")
            sqlBuffer.append(" ( ")
            for (column in columnNames) {
                count++
                sqlBuffer.append("?")
                if (count != columnNames.size) {
                    sqlBuffer.append(",")
                }
            }
            sqlBuffer.append(" ) ")

            return sqlBuffer.toString()
        }

        // Need to check
        fun updateQuery(tableName: String, columnNames: Array<String>, whereExpression: String?): String {
            val sqlBuffer: StringBuffer = StringBuffer()

            sqlBuffer.append(" UPDATE ")
            sqlBuffer.append(tableName)
            sqlBuffer.append(" SET ")
            var count = 0;
            for (column in columnNames) {
                count++
                sqlBuffer.append(column)
                sqlBuffer.append(" = ? ")
                if (count != columnNames.size) {
                    sqlBuffer.append(",")
                }
            }
            sqlBuffer.append(" WHERE ")
            sqlBuffer.append(whereExpression)
            return sqlBuffer.toString()
        }

        fun deleteQuery(tableName: String, whereExpression: String): String {
            val sqlBuffer: StringBuffer = StringBuffer()

            sqlBuffer.append(" DELETE FROM ")
            sqlBuffer.append(tableName)
            sqlBuffer.append(" WHERE ")
            sqlBuffer.append(whereExpression)

            return sqlBuffer.toString()
        }

        fun selectQuery(tableName: String, whereExpression: String?): String {
            val sqlBuffer: StringBuffer = StringBuffer()

            sqlBuffer.append(" SELECT * FROM ")
            sqlBuffer.append(tableName)
            if (whereExpression != null) {
                sqlBuffer.append(" WHERE ")
                sqlBuffer.append(whereExpression)
            }
            return sqlBuffer.toString()
        }
    }
}