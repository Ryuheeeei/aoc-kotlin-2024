fun main() {
    fun part1(input: List<String>): Long {
        return input.filter {
            isTrueEq(
                it.split(":")[1].split(" ").drop(1).map { it.toLong() },
                it.split(" ")[0].removeSuffix(":").toLong()
            )
        }.sumOf { it.split(" ")[0].removeSuffix(":").toLong() }
    }

    fun part2(input: List<String>): Int {
        return 0
    }

    val input = readInput("Day07")
    part1(input).println() // 2941973819040
    part2(input).println() //

}

fun isTrueEq(nums: List<Long>, value: Long): Boolean {
    val first = nums[0]
    val rest = nums.takeLast(nums.size - 1)

    for (o in 0..1.shl(rest.size)) {
        var v = first
        for (idx in rest.indices) {
            if (v > value) {
                break
            }

            val op = o.and(1.shl(idx)) > 0
            if (op)
                v += rest[idx]
            else
                v *= rest[idx]

            if (v == value) {
                return true
            }
        }
    }
    return false
}
