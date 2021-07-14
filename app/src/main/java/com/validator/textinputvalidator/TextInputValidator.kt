package com.validator.textinputvalidator

import androidx.core.widget.doOnTextChanged
import com.google.android.material.textfield.TextInputLayout


/**
 * This function validates the input of a [TextInputLayout]'s EditText against a list of validators
 * if the input violates any validator the 'error' message is shown
 * otherwise the onValid callback is triggered
 */

fun TextInputLayout.validate(
    default: String = "",
    validators: Collection<(String) -> Boolean> = emptyList(),
    error: String = "",
    onValid: (String) -> Unit
) {
    if (editText == null) throw IllegalArgumentException("Validation requires at least one TextInputEditText as a child")
    val validationObj = TextInputValidationObj(default, validators, error)
    setTag(R.id.validationTag, validationObj)
    editText?.setText(default)
    editText?.doOnTextChanged { text, _, _, _ ->
        val valid = validators.all { condition -> condition(text?.toString() ?: "") }
        setError(if (valid) null else error)
        onValid(if (valid) text?.toString() ?: "" else "")
    }
}

fun TextInputLayout.validate(
    default: String = "",
    validators: Collection<(String) -> Pair<Boolean,String>> = emptyList(),
    onValid: (String) -> Unit
) {
    if (editText == null) throw IllegalArgumentException("Validation requires at least one TextInputEditText as a child")
    val validationObj = TextInputValidator(default, "", validators)
    setTag(R.id.validationTag, validationObj)
    editText?.setText(default)
    editText?.doOnTextChanged { text, _, _, _ ->
        for ( isValidAndError in validators ){
            val validError = with(isValidAndError(text?.toString() ?: "")){
                error = if (first) null else second
                onValid( if (first) text?.toString() ?: "" else "" )
                this
            }
            if (!validError.first) break
        }
    }
}

fun TextInputLayout.validate(validationObj: TextInputValidationObj, onValid: (String) -> Unit) {
    setTag(R.id.validationTag, validationObj)
    with(validationObj) {
        validate(default, validators, error, onValid)
    }
}

fun TextInputLayout.validate(validationObj: TextInputValidator, onValid: (String) -> Unit) {
    setTag(R.id.validationTag, validationObj)
    with(validationObj) {
        validate(defaultString,validators,onValid)
    }
}

/**
 * @return [TextInputValidationObj] else null
 */
fun TextInputLayout.getValidator() =
    getTag(R.id.validationTag)?.let { it as TextInputValidationObj } ?: null

/**
 * @return [Boolean]
 *  true if all validation conditions are met otherwise returns false
 *  @throws [IllegalArgumentException] when validation object is not set
 *  @See TextInputLayout.validate to set the validation object
 */
fun TextInputLayout.valid(): Boolean {
    val validationObj = getValidator()
    if (validationObj != null && editText != null) {
        return validationObj.validators.all { condition ->
            condition(
                editText?.text?.toString() ?: ""
            )
        }.also { valid ->
            error = if (valid) null else validationObj.error
        }
    } else {
        throw IllegalArgumentException("Call TextInputLayout#validate(args) first $id")
    }
}
