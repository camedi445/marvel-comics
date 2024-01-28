package co.cmedina.marvelcomics.data.model

data class Thumbnail(
    val path: String,
    val extension: String
) {
    fun getURL() = "${path.replace("http", "https")}.$extension"
}
