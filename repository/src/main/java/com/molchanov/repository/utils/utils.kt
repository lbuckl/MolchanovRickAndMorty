package com.molchanov.repository.utils

/**
 * Функция ищет слово в строке возвращает true, если найдено
 * @param text - текст для поиска
 * @param find - слово для поиска
 */
fun findWordInText(text: String, find: String): Boolean {
    return Regex(find, RegexOption.IGNORE_CASE).findAll(text).toList().isNotEmpty()
}