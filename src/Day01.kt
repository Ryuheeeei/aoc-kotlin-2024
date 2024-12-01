import kotlin.math.abs

fun main() {
    fun part1(input: List<String>): Int {
        val left = input.map { it.split(" ")[0].toInt() }.sorted()
        val right = input.map { it.split(" ")[3].toInt() }.sorted()
        var sum = 0
        left.forEachIndexed { index, i -> sum += abs(i - right[index]) }
        return sum
    }

    fun part2(input: List<String>): Int {
        val left = input.map { it.split(" ")[0].toInt() }
        val right = input.map { it.split(" ")[3].toInt() }
        var sum = 0
        left.forEachIndexed { index, i -> sum += i * right.count { it == i } }
        return sum
    }

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
