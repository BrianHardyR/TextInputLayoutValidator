package com.validator.textinputvalidator

/**
 * @param default the string shown on the [TextInputEditText] before data is entered
 * @param error the string shown as error text
 * @param validators collection of validators
 *          @example listOf( { text ->  text.isNullOrEmpty() } ... )
 */
@Deprecated( message = "", ReplaceWith("TextInputValidator","com.validator.textinputvalidator.TextInputValidator"),level = DeprecationLevel.WARNING )
class TextInputValidationObj(
    val default: String = "",
    val validators: Collection<(String) -> Boolean> = emptyList(),
    val error: String = "",
)
class TextInputValidator(
    val defaultString : String = "",
    val defaultError : String = "",
    val validators : Collection<(String) -> Pair<Boolean,String>> = emptyList()
)
