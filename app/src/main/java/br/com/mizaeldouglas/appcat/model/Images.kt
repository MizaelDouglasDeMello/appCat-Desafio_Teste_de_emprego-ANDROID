package br.com.mizaeldouglas.appcat.model
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Images(
    val id: String,
    val title: String?,
    val link: String,
): Parcelable
