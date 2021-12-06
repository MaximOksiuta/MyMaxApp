package com.example.myapplication.utils

import com.google.android.material.textfield.TextInputLayout

class CredentialsCheck {
    companion object {
        fun overlappingPasswordsCheck(password1: String, password2: String, til: TextInputLayout): Boolean {
            return if (password1 == password2){
                true
            } else {
                til.error = "Пароли должны совпадать"
                false
            }
        }

        fun checkPersonalName(name: String, til: TextInputLayout): Boolean {
            return if (name.length > 1){
                true
            } else {
                til.error= "Имя не может состоять менее чем из 2 букв"
                false
            }
        }

        fun checkEmail(email: String, til: TextInputLayout): Boolean {
            return if (email.count { it == '@' } == 1 &&
                email.substringAfter('@').count { it == '.' } == 1 &&
                email.substringBefore('@').substringBefore('+').replace(".", "").length >= 2 &&
                email.substringAfter('@').substringBefore('.').isNotBlank() &&
                email.substringAfterLast('.').isNotBlank()) {
                true
            } else {
                til.error = "Введите корректный email"
                false
            }
        }

        fun checkPassword(password: String, til: TextInputLayout): Boolean {
            if (password.length >= 8) {
                if (password.indexOfAny(charArrayOf('!', '@', '#', '$', '%', '^', '&', '*')) >= 0) {
                    if (password.indexOfAny(
                            charArrayOf(
                                '1',
                                '2',
                                '3',
                                '4',
                                '5',
                                '6',
                                '7',
                                '8',
                                '9',
                                '0'
                            )
                        ) >= 0
                    ) {
                        var lines = mutableSetOf<Int>()
                        for (item in password) {
                            when (item) {
                                in charArrayOf(
                                    'q',
                                    'w',
                                    'e',
                                    'r',
                                    't',
                                    'y',
                                    'u',
                                    'i',
                                    'o',
                                    'p'
                                ) -> {
                                    lines.add(1)
                                }
                                in charArrayOf(
                                    'a',
                                    's',
                                    'd',
                                    'f',
                                    'g',
                                    'h',
                                    'j',
                                    'k',
                                    'l'
                                ) -> {
                                    lines.add(2)
                                }
                                in charArrayOf(
                                    'z',
                                    'x',
                                    'c',
                                    'v',
                                    'b',
                                    'n',
                                    'm'
                                ) -> {
                                    lines.add(3)
                                }
                            }
                            if (lines.size>1){
                                break
                            }
                        }
                        if (lines.size != 0) {
                            if (lines.size > 1) {
                                return true
                            } else {
                                til.error = "Все буквы пароля не могут находиться в одной строке"
                            }
                        } else {
                            til.error = "Пароль должен содрежать хотя бы одну букву"
                        }
                    } else {
                        til.error = "Пароль должен содержать хотя бы одну цифру"
                    }
                } else {
                    til.error = "Пароль должен содержать хотя бы один спецсимвол (!@?#$%^&*)"
                }
            } else {
                til.error = "Пароль должен быть не короче 8 символов"
            }
            return false
        }
    }
}