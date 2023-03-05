fun randomNumber() : String {
    //var array: IntArray = intArrayOf();
    var str: String = ""
    var rand: Int

    while (str.length < 4) {
        rand = (0 until 10).random()

        //if (!(randval in array)) {
        if (!str.contains(rand.toString())) {
            //array += rand;
            str += rand
        }
    }

    return str
}

fun checkInput(input: String?): Boolean {
    if (input != null && input?.length != 4) {
        return false
    }
    return input?.toIntOrNull() != null
}

fun main() {
    val gen: String = randomNumber()
    println("Generated number: $gen")

    var input: String?
    while (true) {
        print("User input: ")
        input = readLine()

        if (checkInput(input)) {
            // correct guessed numbers
            var n = 0
            // correct position
            var m = 0

            for (i in 0..3) {
                if (input?.contains(gen.get(i)) == true) {
                    n++
                }
                if (gen.get(i) == input?.get(i)) {
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