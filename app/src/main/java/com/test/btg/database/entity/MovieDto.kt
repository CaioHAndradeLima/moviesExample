package com.test.btg.database.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
data class MovieDto(
    @PrimaryKey
    @SerializedName("id")
    var id: Long,
    @SerializedName("title")
    var title: String?,
    @SerializedName("overview")
    var description: String?,
    @SerializedName("release_date")
    var releaseDate: String?,
    @SerializedName("vote_average")
    var voteAverage: Float,
    @SerializedName("poster_path")
    var posterPath: String,
    @SerializedName("backdrop_path")
    var coverPath: String
) : Parcelable {

    private fun buildPath(width: String, path: String?): String {
        return StringBuilder(2)
            .append("http://image.tmdb.org/t/p/")
            .append(width)
            .append(path)
            .toString()
    }

    fun getPosterPathToDownload() = buildPath("w500", posterPath)
    fun getCoverPathToDownload() = buildPath("w780", coverPath)
}
