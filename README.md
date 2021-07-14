# TextInputLayoutValidator
This project provides a simple and streamlined way to validate TextInputLayoutEditText

## Installation
1. Add the JitPack repository to your build file
```
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
2. Add the dependency
```
    dependencies {
	        implementation 'com.github.BrianHardyR:TextInputLayoutValidator:1.0.0'
	}
```

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
### Error message for each validation condition
```kotlin
val phoneNumberValidator = TextInputValidator(
    defaultString = "My default text",
    validators = listOf(
        { text -> (text.length > 12) to "Length must be greater than 12"},
        { text -> text.startsWith('+') to "Input must start with '+'"},
        ...
    )
)
```

### Single error message
```kotlin
val phoneNumberValidator = TextInputValidationObj(
    default = "My default text",
    validators = listOf(
        { text -> text.length > 12},
        { text -> text.startsWith('+')},
        ...
    )
)

myinput.validate(phoneNumberValidator){ validText ->
    // do something with the valid text
}
```

<b>Validate input</b>
After setting the validation object on the TextInputLayout you can validate it anywhere on your code
```kotlin
myinput.valid() // Return a Boolean.
```


