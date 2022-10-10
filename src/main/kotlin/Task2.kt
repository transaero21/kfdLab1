import java.util.Scanner

/**
 * Реализуйте бинарное дерево поиска для целых чисел.
 *
 * Программа получает на вход последовательность целых чисел и строит из них дерево:
 * 1. Элементы в деревья добавляются в соответствии с результатом поиска их места.
 * 2. Если элемент уже существует в дереве, добавлять его не надо.
 * 3. Балансировка дерева не производится.
 *
 * На вход программа получает последовательность натуральных чисел.
 * Последовательность завершается числом 0, которое означает конец ввода, и добавлять его в дерево не надо.
 * Для полученного дерева выведите список всех листьев (вершин, не имеющих потомков) в порядке возрастания
 */

fun main() {
    val scanner = Scanner(System.`in`)
    val tree = Tree<Int>()
    while (true) {
        val value = scanner.nextInt()
        if (value == 0) break
        tree.add(value)
    }
    print(tree)
}

class Tree<T: Comparable<T>> {

    private var root: Node<T>? = null

    fun add(new: T) {
        if (root == null) {
            root = Node(new)
        } else {
            root?.add(new)
        }
    }

    override fun toString(): String {
        return root.toString()
    }

    private class Node<T: Comparable<T>>(val value: T) {

        var leftChild: Node<T>? = null
        var rightChild: Node<T>? = null

        fun add(new: T) {
            when {
                value > new -> {
                    leftChild?.add(new) ?: run { leftChild = Node(new) }
                }
                value < new -> {
                    rightChild?.add(new) ?: run { rightChild = Node(new) }
                }
                value == new -> return
            }
        }

        override fun toString(): String {
            var ret = ""
            rightChild?.toString()?.lines()?.let {
                ret += "$value ── ${it[0]}"
                if (leftChild != null) {
                    for (i in 1 until it.size) {
                        ret += "\n│${" ".repeat("$value ── ".length - 1)}${it[i]}"
                    }
                }
            } ?: run {
                ret += "$value"
            }
            leftChild?.toString()?.lines()?.let {
                ret += "\n└── ${it[0]}"
                for (i in 1 until it.size) {
                    ret += "\n${" ".repeat("└── ".length)}${it[i]}"
                }
            }
            return ret
        }
    }
}
