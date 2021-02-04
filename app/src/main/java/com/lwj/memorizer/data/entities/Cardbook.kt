package com.lwj.memorizer.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

//@Entity(
//    tableName = "cardbook",
//    foreignKeys = [ForeignKey(
//        entity = UserModel::class,
//        parentColumns = arrayOf("idx"),
//        childColumns = arrayOf("userIdx")
//    )]
//)
@Entity(
    tableName = "cardbook"
)
data class Cardbook(

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "is_bookmarked")
    val isBookmarked: Boolean = false,

    @ColumnInfo(name = "cover_image_uri")
    val coverImageUri: String?
) {
    @PrimaryKey(autoGenerate = true)
    var idx: Long = 0L
}