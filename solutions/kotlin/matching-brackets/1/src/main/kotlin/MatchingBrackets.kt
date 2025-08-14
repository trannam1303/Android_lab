object MatchingBrackets {

    fun isValid(input: String): Boolean {
        var stack = mutableListOf<Char>()
        for (letter in input){
            when{
                letter == '(' -> stack.add('(')
                letter == ')' -> if(stack.isNotEmpty() && stack.last() == '(') {
                    stack.removeLast()
                } else {
                    return false
                }
                letter == '{' -> stack.add('{')
                letter == '}' -> if(stack.isNotEmpty() && stack.last() == '{') {
                    stack.removeLast()
                } else {
                    return false
                }
                letter == '[' -> stack.add('[')
                letter == ']' -> if(stack.isNotEmpty() && stack.last() == '[') {
                    stack.removeLast()
                } else {
                    return false
                }
            }
        }
        return stack.isEmpty()
    }
}
