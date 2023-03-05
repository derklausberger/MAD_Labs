// generate random number with 4 digits without dublicates
fun generateRandomNumber() : String {
    var str: String = ""
    var rand: Int

    while (str.length < 4) {
        rand = (0 until 10).random()

        if (!str.contains(rand.toString())) {
            str += rand
        }
    }

    return str
}

// check if user input is a 4 digit number
fun checkInput(input: String?): Boolean {
    if (input != null && input?.length != 4) {
        return false
    }
    return input?.toIntOrNull() != null
}

// start the game
fun playGame() {
    val gen: String = generateRandomNumber()
    println("Generated number: $gen")

    // get user inputs
    var input: String?
    while (true) {
        print("User input: ")
        input = readLine()!!

        if (checkInput(input)) {
            // compare the 2 numbers

            // correct guessed numbers
            var n = 0
            // correct positions
            var m = 0

            for (i in 0..3) {
                if (input.contains(gen.get(i)) == true) {
                    n++
                }
                if (gen.get(i) == input.get(i)) {
                    m++
                }
            }
            println("Output: $n:$m")

            if (n == 4 && m == 4) {
                println("Game over - you guessed the correct number!")
                break
            }
        } else {
            println("invalid user input")
        }
    }
}

fun main() {
    playGame()
}
