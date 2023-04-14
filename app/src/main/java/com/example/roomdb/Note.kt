package com.example.roomdb

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "notesTable")

class Note (

    @ColumnInfo(name = "title")
    val noteTitle:String,

    @ColumnInfo(name = "description")
    val  notedescription : String ,

    @ColumnInfo(name = "timestamp")
    val timestamp: String)

{
    @PrimaryKey(autoGenerate = true)
    var id = 0

}

