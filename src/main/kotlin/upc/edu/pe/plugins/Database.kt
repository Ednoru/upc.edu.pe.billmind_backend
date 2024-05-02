package upc.edu.pe.plugins

import io.ktor.server.application.*
import org.jetbrains.exposed.sql.Database
import java.io.File

fun loadEnvironmentVariables(): Map<String, String> {
    val file = File("src/main/kotlin/upc/edu/pe/environments.env")
    if (!file.exists()) {
        throw IllegalArgumentException("The .env file does not exist")
    }
    return file.readLines().map { it.split("=") }.associate { it[0] to it[1] }
}

fun Application.configureDatabase() {
    val env = loadEnvironmentVariables()
    val database = env["MYSQLUSER"]?.let {
        env["MYSQLPASSWORD"]?.let { it1 ->
            Database.connect(
                url = "jdbc:mysql://${env["MYSQLHOST"]}:${env["MYSQLPORT"]}/${env["MYSQLDATABASE"]}",
                user = it,
                password = it1,
                driver = "com.mysql.cj.jdbc.Driver"
            )
        }
    }
}