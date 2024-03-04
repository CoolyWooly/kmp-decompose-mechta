package core.domain

sealed interface Result<out R> {
    class Success<out R>(val value: R) : Result<R>
    class Error(val text: String) : Result<Nothing>
}