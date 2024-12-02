fun main() {
    fun part1(input: List<String>): Int {
        val lists = input.map { it.split(" ").map { it.toInt() } }
        var answer = 0
        answer += lists.filter { ints -> ints == ints.sorted() }.count { isSafeSequence(it, isDescending = false) }
        answer += lists.filter { ints -> ints == ints.sortedDescending() }.count { isSafeSequence(it, isDescending = true) }
        return answer
    }

    fun part2(input: List<String>): Int {
        val lists = input.map { it.split(" ").map { it.toInt() } }
        var answer = 0
        for ((_, list) in lists.withIndex()) {
            for ((idx, _) in list.withIndex()) {
                if (isSafeSequence(list.filterIndexed { index, _ -> index != idx } , isDescending = true) || (isSafeSequence(list.filterIndexed { index, i -> index != idx }, isDescending = false))) {
                    answer += 1
                    break
                }
            }
        }
        return answer
    }

    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()

}

fun isSafeSequence(ints: List<Int>, isDescending: Boolean): Boolean {
    for ((idx, value) in ints.withIndex()) {
        if (idx == ints.size - 1) { break }
        val nextValue = ints[idx + 1]
        val diff = if (isDescending) value - nextValue else nextValue - value
        if (diff < 1 || diff > 3) {
            return false
        }
    }
    return true
}
