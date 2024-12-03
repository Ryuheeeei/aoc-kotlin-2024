fun main() {
    fun part1(input: List<String>): Int {
        val line = input.joinToString(separator = "").trim('\n')
        val regex = "mul\\((\\d{1,3}),(\\d{1,3})\\)".toRegex()
        val match = regex.findAll(line)
        var answer = 0
        match.forEach {
            val (a, b) = it.destructured
            answer += a.toInt() * b.toInt()
        }
        return answer
    }

    fun part2(input: List<String>): Int {
        val line = input.joinToString(separator = "").trim('\n')
        val regex = "mul\\((\\d{1,3}),(\\d{1,3})\\)".toRegex()
        var answer = 0
        line.split("do()").forEach {
            val s = it.split("don't()")[0]
            val match = regex.findAll(s)
            match.forEach {
                val (a, b) = it.destructured
                answer += a.toInt() * b.toInt()
            }
        }
        return answer
    }

    val input = readInput("Day03")
    part1(input).println()
    part2(input).println()
}
