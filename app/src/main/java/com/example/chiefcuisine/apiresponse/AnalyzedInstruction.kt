package com.example.chiefcuisine.apiresponse

data class AnalyzedInstruction(
    val name: String,
    val steps: List<Step>
)