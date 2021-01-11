package com.example.mvvmdaggerroom.data.database

import com.example.mvvmdaggerroom.model.Data
import com.example.mvvmdaggerroom.model.FixedHeightSmallStill
import com.example.mvvmdaggerroom.model.Images


fun DataEntity.toData() = Data(
    Images(FixedHeightSmallStill("320", "420",this.images,"320")),
    this.title,
    this.type,
    this.username
)

fun List<DataEntity>.toDataList() = this.map { it.toData() }

fun Data.toDataEntity() = DataEntity(
    images = this.images.fixed_height_small_still.url,
    title = this.title,
    type = this.type,
    username = this.username
)

fun List<Data>.toDataEntityList() = this.map { it.toDataEntity() }