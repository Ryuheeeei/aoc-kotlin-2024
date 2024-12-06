fun main() {
    fun part1(input: List<String>): Int {
        val list = MutableList(input.size) { MutableList(input[0].length) { ' ' } }
        input.forEachIndexed { row, s -> s.forEachIndexed { col, c -> list[row][col] = c } }

        val visited = MutableList(input.size) { MutableList(input[0].length) { false } }
        var presentRow = input.indexOfLast { it.contains('^') }
        var presentCol = input[presentRow].indexOf('^')
        var direction: Char = '^'

        visited[presentRow][presentCol] = true
        var answer = 1  // start
        while (true) {
            val nextPos = next(Pair(presentRow, presentCol), direction)
            if (nextPos.first < 0 || list.size <= nextPos.first || nextPos.second < 0 || list[0].size <= nextPos.second) {
                answer += 1 // end
                break
            }
            if (list[nextPos.first][nextPos.second] == '#') {
                direction = rotate(direction)
                continue
            }
            if (!visited[presentRow][presentCol]) {
                visited[presentRow][presentCol] = true
                answer++
            }
            presentRow = nextPos.first
            presentCol = nextPos.second
        }
        return answer
    }

    fun part2(input: List<String>): Int {
        var answer = 0
        val list = MutableList(input.size) { MutableList(input[0].length) { ' ' } }
        input.forEachIndexed { row, s -> s.forEachIndexed { col, c -> list[row][col] = c } }
        var visitedWithDirection = MutableList(input.size) { MutableList(input[0].length) { Pair(false, ' ') } }
        val startRow = input.indexOfLast { it.contains('^') }
        val startCol = input[startRow].indexOf('^')
        var presentRow = startRow
        var presentCol = startCol
        var direction: Char = '^'

        visitedWithDirection[presentRow][presentCol] = Pair(false, '^')
        input.forEachIndexed { row, s ->
            s.forEachIndexed { col, c ->
                list[row][col] = '#'
                presentRow = startRow
                presentCol = startCol
                direction = '^'

                visitedWithDirection = MutableList(input.size) { MutableList(input[0].length) { Pair(false, ' ') } }
                visitedWithDirection[row][col] = Pair(true, '^')

                while (true) {
                    val nextPos = next(Pair(presentRow, presentCol), direction)
                    if (nextPos.first < 0 || list.size <= nextPos.first || nextPos.second < 0 || list[0].size <= nextPos.second) {
                        break
                    }
                    if (list[nextPos.first][nextPos.second] == '#') {
                        direction = rotate(direction)
                        continue
                    }
                    presentRow = nextPos.first
                    presentCol = nextPos.second
                    if (visitedWithDirection[presentRow][presentCol].first && direction == visitedWithDirection[presentRow][presentCol].second) {
                        answer++
                        break
                    }
                    visitedWithDirection[presentRow][presentCol] = Pair(true, direction)
                }
                list[row][col] = input[row][col]
            }
        }
        return answer
    }

    val input = readInput("Day06")
    part1(input).println() // 5212
    part2(input).println() // 1767
}

fun rotate(now: Char): Char {
    return when (now) {
        '^' -> '>'
        '>' -> 'v'
        'v' -> '<'
        '<' -> '^'
        else -> throw IllegalStateException("direction must be <>^v")
    }
}

fun next(pos: Pair<Int, Int>, direction: Char): Pair<Int, Int> {
    return when (direction) {
        '^' -> Pair(pos.first - 1, pos.second)
        '>' -> Pair(pos.first, pos.second + 1)
        'v' -> Pair(pos.first + 1, pos.second)
        '<' -> Pair(pos.first, pos.second - 1)
        else -> {
            throw IllegalStateException("direction must be <>^v")
        }
    }
}
