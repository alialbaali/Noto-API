import com.noto.database.model.Libraries
import com.noto.database.model.Users
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

private const val DB_NAME = "noto"
private const val URL = "jdbc:postgresql://localhost:5432/${DB_NAME}"
private const val DRIVER = "org.postgresql.Driver"
private const val USER = "postgres"
private const val PASSWORD = "135792468"


fun connectDatabase() {
    Database.connect(URL, DRIVER, USER, PASSWORD)
    transaction {
        SchemaUtils.create(Users, Libraries)
    }
}