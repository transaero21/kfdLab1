/**
 * Быстрая сортировка
 *
 * Метод основан на подходе "разделяй-и-властвуй". Общая схема такова:
 * 1. Из массива выбирается некоторый опорный элемент a.get(i)
 * 2. Запускается процедура разделения массива, которая перемещает все ключи, меньшие, либо равные a.get(i), влево от него, а все ключи, большие, либо равные a.get(i) - вправо
 * 3. Теперь массив состоит из двух подмножеств, причем левое меньше, либо равно правого
 * 4. Для обоих подмассивов: если в подмассиве более двух элементов, рекурсивно запускаем для него ту же процедуру
 *
 * В конце получится полностью отсортированная последовательность.
 */

fun main() {
//    val targetList = listOf(9, 9, 14, 5, 4, 17, 2, 2, 20, 4, 16, 1, 17, 14, 2, 10, 4, 15, 19, 15)
//    val targetList = listOf(30, 3, 16, 26, 20, 35, 25, 9, 13, 32, 31, 18, 12, 4, 8, 1, 6, 27, 39, 21, 17, 28, 7, 10, 14, 24, 19, 36, 38, 22, 40, 30, 32, 7, 28, 37, 1, 35, 33, 25, 14, 21, 27, 5, 18, 26, 31, 17, 6, 24, 12, 13, 16, 9, 34, 11, 38, 20, 10, 15, 30, 22)
    val targetList = listOf(15, 19, 48, 22, 43, 26, 24, 38, 32, 14, 42, 29, 36, 10, 15, 27, 15, 10, 48, 35, 14, 42, 32, 30, 19, 37, 23, 33, 31, 26, 17, 11)
    println("targetList=$targetList")
    val newList = fastSorting(targetList.toMutableList())
    println("newList=$newList")
}

fun fastSorting(list: MutableList<Int>): List<Int> {
    when {
        list.size < 2 -> return list
        list.size == 2 -> return if (list[0] <= list[1]) list else listOf(list[1], list[0])
        else -> {
            var swaps = 0; var i = 0
            while (i != list.lastIndex - swaps) {
                list[i].let {
                    if (it > list[list.lastIndex - swaps]) {
                        list.remove(it)
                        list.add(it)
                        swaps++
                    } else {
                        i++
                    }
                }
            }
            val pos = list.lastIndex - swaps
            return fastSorting(list.subList(0, pos)) + listOf(list[pos]) + fastSorting(list.subList(pos + 1, list.size))
        }
    }
}
