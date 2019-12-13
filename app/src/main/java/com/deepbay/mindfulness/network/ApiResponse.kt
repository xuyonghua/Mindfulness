package com.deepbay.mindfulness.network

data class ApiResponse<T>(val code: Int, val msg: String, val data: T)