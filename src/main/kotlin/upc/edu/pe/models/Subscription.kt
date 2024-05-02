package upc.edu.pe.models

import kotlinx.serialization.Serializable

@Serializable
data class Subscription(
    val id: Int,
    val type: String,
    val initialDate: String,
    val endDate: String,
    val idClient: Int,
)
