# TextInputLayoutValidator
This project provides a simple and streamlined way to validate TextInputLayoutEditText

## How to
<b>Simple Validation</b>
```kotlin
val myinput = findViewById<TextInputLayout>(R.id.my_input)

myinput.validate(
    default = "my default text",
    validators = listOf(
        { text -> t.isNotEmpty() },
        ...
    ),
    error = "my error text",
    onValid = { validText -> 
        // do something with the valid text
     }
)
```

<b>Using the Validation Object</b>

```kotlin
val phoneNumberValidator = TextInputValidationObj(
    default = "My default text",
    validators = listOf(
        { text -> text.length > 12},
        { text -> text.startsWith('+')},
        ...
    )
)

myinput.validate(phoneNumberValidator)
```

<b>Validate input</b>
After setting the validation object on the TextInputLayout you can validate it anywhere on your code
```kotlin
myinput.valid() // Return a Boolean.
```


