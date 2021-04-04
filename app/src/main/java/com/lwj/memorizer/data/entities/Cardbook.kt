package com.lwj.memorizer.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "cardbook_list"
)
data class Cardbook(

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "is_bookmarked")
    var isBookmarked: Boolean = false,

    @ColumnInfo(name = "cover_image_uri")
    var coverImageUri: String?
) {
    @PrimaryKey(autoGenerate = true)
    var idx: Long = 0L

    constructor() : this("", false, "")
}