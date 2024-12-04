fun main() {
    fun part1(input: List<String>): Int {
        var answer = 0
        val regex = "XMAS".toRegex()
        val reverseRegex = "SAMX".toRegex()
        // horizontal
        input.forEach {
            answer += regex.findAll(it).count() + reverseRegex.findAll(it).count()
        }
        // vertical
        for ((index, _) in input[0].withIndex()) {
            var column = ""
            input.forEach { column += it[index] }
            answer += regex.findAll(column).count() + reverseRegex.findAll(column).count()
        }
        // diagonal
        for (rowId in 0..input.size - 4) {
            for (colId in 0..input[rowId].length - 4) {
                if (input[rowId][colId] == 'X' &&
                    input[rowId + 1][colId + 1] == 'M' &&
                    input[rowId + 2][colId + 2] == 'A' &&
                    input[rowId + 3][colId + 3] == 'S'
                ) {
                    answer += 1
                }
                if (input[rowId][colId] == 'S' &&
                    input[rowId + 1][colId + 1] == 'A' &&
                    input[rowId + 2][colId + 2] == 'M' &&
                    input[rowId + 3][colId + 3] == 'X'
                ) {
                    answer += 1
                }
                if (input[rowId + 3][colId] == 'X' &&
                    input[rowId + 2][colId + 1] == 'M' &&
                    input[rowId + 1][colId + 2] == 'A' &&
                    input[rowId][colId + 3] == 'S'
                ) {
                    answer += 1
                }
                if (input[rowId + 3][colId] == 'S' &&
                    input[rowId + 2][colId + 1] == 'A' &&
                    input[rowId + 1][colId + 2] == 'M' &&
                    input[rowId][colId + 3] == 'X'
                ) {
                    answer += 1
                }
            }
        }
        return answer
    }

    fun part2(input: List<String>): Int {
        var answer = 0
        for (rowId in 0..input.size - 3) {
            for (colId in 0..input[rowId].length - 3) {
//                    M.M
//                    .A.
//                    S.S
                if (input[rowId][colId] == 'M' &&
                    input[rowId + 1][colId + 1] == 'A' &&
                    input[rowId + 2][colId + 2] == 'S' &&
                    input[rowId][colId + 2] == 'M' &&
                    input[rowId + 2][colId] == 'S'
                ) {
                    answer += 1
                }
//                    M.S
//                    .A.
//                    M.S
                if (input[rowId][colId] == 'M' &&
                    input[rowId + 1][colId + 1] == 'A' &&
                    input[rowId + 2][colId + 2] == 'S' &&
                    input[rowId][colId + 2] == 'S' &&
                    input[rowId + 2][colId] == 'M'
                ) {
                    answer += 1
                }
//                    S.S
//                    .A.
//                    M.M
                if (input[rowId][colId] == 'S' &&
                    input[rowId + 1][colId + 1] == 'A' &&
                    input[rowId + 2][colId + 2] == 'M' &&
                    input[rowId][colId + 2] == 'S' &&
                    input[rowId + 2][colId] == 'M'
                ) {
                    answer += 1
                }
//                    S.M
//                    .A.
//                    S.M
                if (input[rowId][colId] == 'S' &&
                    input[rowId + 1][colId + 1] == 'A' &&
                    input[rowId + 2][colId + 2] == 'M' &&
                    input[rowId][colId + 2] == 'M' &&
                    input[rowId + 2][colId] == 'S'
                ) {
                    answer += 1
                }
            }
        }
        return answer
    }

    val input = readInput("Day04")
    part1(input).println()
    part2(input).println()
}
