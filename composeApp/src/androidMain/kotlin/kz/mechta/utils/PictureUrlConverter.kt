package kz.mechta.utils

object PictureUrlConverter {

    enum class Size {
        SIZE_90, SIZE_123, SIZE_286, SIZE_480
    }

    fun getCompressed(url: String?, size: Size): String? {
        if (url.isNullOrBlank()) return null
        val px = when (size) {
            Size.SIZE_90 -> {
                "90"
            }
            Size.SIZE_123 -> {
                "123"
            }
            Size.SIZE_286 -> {
                "286"
            }
            Size.SIZE_480 -> {
                "480"
            }
        }
        return url.replace(Regex(".[^.]*\$"), "-$px.webp")
    }
}