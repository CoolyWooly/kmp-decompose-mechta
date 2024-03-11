package kz.mechta.utils.extension

fun Int.spacedString(): String {
    return String.format("%,d", this).replace(",", " ")
}