package upc.edu.pe.repositories

import upc.edu.pe.models.Subscription
import upc.edu.pe.repositories.SubscriptionRepository.Subscription.clientId
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

object SubscriptionRepository {
    private object Subscription : Table() {
        val id = integer("id").autoIncrement()
        val clientId = integer("Client_id")
        val type = varchar("type", 100)
        val initialDate =  varchar("initial_date", 100)
        val endDate = varchar("end_date", 100)
    }

    private fun resultRowToSubscription(row: ResultRow): upc.edu.pe.models.Subscription {
        return Subscription(
            id = row[Subscription.id],
            idClient = row[clientId],
            type = row[Subscription.type],
            initialDate = row[Subscription.initialDate],
            endDate = row[Subscription.endDate]
        )
    }

    // Funcion que obtiene todas las suscripciones
    fun getAllSubscriptions(): List<upc.edu.pe.models.Subscription> {
        return transaction {
            Subscription.selectAll().map { resultRowToSubscription(it) }
        }
    }

    // Funcion que obtiene una suscripcion por su id
    fun getSubscriptionById(subscriptionId: Int): upc.edu.pe.models.Subscription? {
        return transaction {
            Subscription.select { Subscription.id eq subscriptionId }.map { resultRowToSubscription(it) }.firstOrNull()
        }
    }

    // Funcion que agrega una suscripcion
    fun addSubscription(subscription: upc.edu.pe.models.Subscription) {
        transaction {
            Subscription.insert {
                it[clientId] = subscription.idClient
                it[type] = subscription.type
                it[initialDate] = subscription.initialDate
                it[endDate] = subscription.endDate
            }
        }
    }

    // Funcion que actualiza una suscripcion
    fun updateSubscription(subscription: upc.edu.pe.models.Subscription) {
        transaction {
            Subscription.update({ Subscription.id eq subscription.id }) {
                it[clientId] = subscription.idClient
                it[type] = subscription.type
                it[initialDate] = subscription.initialDate
                it[endDate] = subscription.endDate
            }
        }
    }

    // Funcion que obtiene la suscripcion de un cliente
    fun getSubscriptionByClientId(clientId: Int): List<upc.edu.pe.models.Subscription> {
        return transaction {
            Subscription.select { Subscription.clientId eq clientId }.map { resultRowToSubscription(it) }
        }
    }
}