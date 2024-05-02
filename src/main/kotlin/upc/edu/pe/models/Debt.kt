package upc.edu.pe.models

import kotlinx.serialization.Serializable

@Serializable
data class Debt(
    val id: Int,
    val name: String,
    val expiration: String,
    val amount: String,
    val description: String,
    val relevance: String,
    val idClient: Int,
)



