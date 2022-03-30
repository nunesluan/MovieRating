package com.movie.movierating.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
class Movies(@PrimaryKey(autoGenerate = true) var id: Int?,

              @ColumnInfo(name = "name")
              var name: String,

              @ColumnInfo(name = "duration")
              var duration: String,

              @ColumnInfo(name = "category")
              var category: String,

              @ColumnInfo(name = "evaluation")
              var evaluation: String): Serializable {

    override fun toString(): String {
        return "$name\n $duration\n $category\n $evaluation"
    }

}