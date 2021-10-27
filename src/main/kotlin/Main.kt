fun main(args: Array<String>) {

    val bracketsString = "(({([}])[()])[])"
    println(checkoutBrackets(bracketsString))
    println(checkoutBracketsWithStack(bracketsString))

}

//doesn't work properly
fun checkoutBrackets(input: String): Boolean {
    var roundsCount = 0
    var squareCount = 0

    for (char in input) {
        when (char) {
            '(' -> roundsCount++
            ')' -> if(squareCount > 0) return false else roundsCount--
            '[' -> squareCount++
            ']' -> if(roundsCount > 0) return false else squareCount--
        }
        if(roundsCount < 0 || squareCount < 0) return false
    }

    return (roundsCount == 0 && squareCount == 0)
}

fun checkoutBracketsWithStack(input: String) : Boolean {
    var myStack = Stack<Char>()

    for (char in input) {
        if (char == '(' || char == '[' || char == '{') {
            myStack.push(char)
        } else {
            var top : Char? = myStack.pop()
            if ((char == ')') && (top != '(')) return false
            if ((char == ']') && (top != '[')) return false
            if ((char == '}') && (top != '{')) return false
        }
        //test output
        //println(myStack.toString())
    }

    return myStack.isEmpty()
}

//TODO: pass a type into class
class Stack<T> {
    private val stackElements = ArrayList<T>()

    //adds an element to the stack
    fun push(element: T) {
        stackElements.add(element)
    }

    //removes the most recently added element OR null if there's no elements in stack
    fun pop() : T? {
        return if (stackElements.isEmpty()) null else stackElements.last().also { stackElements.removeLast() }
    }

    fun isEmpty() : Boolean = stackElements.isEmpty()

    override fun toString() : String = stackElements.toString()

}