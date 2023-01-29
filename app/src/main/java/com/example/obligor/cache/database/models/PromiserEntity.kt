package com.example.obligor.cache.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.obligor.domain.core.Dto
import com.example.obligor.domain.models.Promiser

@Entity(tableName = PromiserEntity.TABLE_NAME)
class PromiserEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "credit")
    val credit: Double,
): Dto<Promiser> {

    override fun toDomain(): Promiser =
        Promiser(
            name = name,
            credit = credit,
        )

    companion object {
        const val TABLE_NAME = "promisers"
    }

}