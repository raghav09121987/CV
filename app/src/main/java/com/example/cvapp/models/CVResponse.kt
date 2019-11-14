package com.example.cvapp.models

data class CVResponse(
    val email: String?,
    val name: String?,
    val pastExperience: List<PastExperience>?,
    val phones: String?,
    val professionalSummary: String?
)