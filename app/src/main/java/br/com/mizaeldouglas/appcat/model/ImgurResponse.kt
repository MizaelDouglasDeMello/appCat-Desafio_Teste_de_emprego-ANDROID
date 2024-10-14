package br.com.mizaeldouglas.appcat.model
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ImgurResponse(
    val data: List<ImageData>,
    val success: Boolean,
    val status: Int
): Parcelable
