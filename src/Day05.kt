import java.util.*

fun main() {
    fun part1(input: List<String>): Int {
        var answer = 0
        val map = mutableMapOf<Int, MutableList<Int>>()
        input.takeWhile { it.contains("|") }
            .forEach {
                val left = it.split("|")[0].toInt()
                val right = it.split("|")[1].toInt()
                val values = map.getOrDefault(left, mutableListOf())
                values.add(right)
                map[left] = values
            }
        input.filter { it.contains(",") }
            .filter { isRightOrder(map, it.split(",").map { it.toInt() }) }
            .forEach {
                val nums = it.split(",").map { it.toInt() }
                val halfIdx = (nums.size - 1) / 2
                answer += nums[halfIdx]
            }
        return answer
    }

    fun part2(input: List<String>): Int {
        var answer = 0
        val map = mutableMapOf<Int, MutableList<Int>>()
        input.takeWhile { it.contains("|") }
            .forEach {
                val left = it.split("|")[0].toInt()
                val right = it.split("|")[1].toInt()
                val values = map.getOrDefault(left, mutableListOf())
                values.add(right)
                map[left] = values
            }
        input.filter { line -> line.contains(",") }
            .filterNot { isRightOrder(map, it.split(",").map { num -> num.toInt() }) }
            .forEach {
                val correctList = it.split(",").map { num -> num.toInt() }
                val halfIdx = (correctList.size - 1) / 2
                while (!isRightOrder(map, correctList)) {
                    for ((index, num) in correctList.withIndex()) {
                        for (nextIdx in index + 1..<correctList.size) {
                            if (map[num].isNullOrEmpty() || (!map[num]!!.contains(correctList[nextIdx]))) {
                                Collections.swap(correctList, index, nextIdx)
                            }
                        }
                    }
                }
                answer += correctList[halfIdx]
            }
        return answer
    }

    val input = readInput("Day05")
    part1(input).println() // 4766
    part2(input).println() // 6257
}

fun isRightOrder(map: Map<Int, List<Int>>, ints: List<Int>): Boolean {
    for ((index, num) in ints.withIndex()) {
        for (nextIdx in index + 1..<ints.size) {
            if (map[num].isNullOrEmpty() || (!map[num]!!.contains(ints[nextIdx]))) {
                return false
            }
        }
    }
    return true
}
