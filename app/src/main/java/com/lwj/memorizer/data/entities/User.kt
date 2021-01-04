package com.lwj.memorizer.data.entities

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

data class User(
    @PrimaryKey(autoGenerate = true)
    val idx: Long,

    @ColumnInfo(name = "auth_token")
    val authToken: String,

    @ColumnInfo(name = "id")
    val id: String,

    @ColumnInfo(name = "password")
    val password: String,

    @ColumnInfo(name = "nickname")
    val nickname: String,

    @ColumnInfo(name = "create_date")
    val createDate: Long?,


)