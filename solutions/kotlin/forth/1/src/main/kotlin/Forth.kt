class Forth {
    private val words = mutableListOf<String>()
    private val stack = mutableListOf<Int>()
    private val procedureTitles = mutableListOf<String>()
    private val procedureOps = mutableListOf<String>()
    private var recursiveCall = false
    private var recursiveIndex = 0
    fun evaluate(vararg line: String): List<Int> {
        var newWordFound = false
        var titleFound = false
        var stringToAdd = ""
        //In our case, we receive a series of strings and we should parse it.
        line.forEach {
            val wordLine = it.split(' ')
            val newLine = wordLine.map {x ->
                x.lowercase()
            }
            words.addAll(newLine)
        }
        //Now we have a List with every command. Let's search:
        for (command in words) {
            //If we have a number we save it on the stack:
            if (command.all { it.isDigit() } && !newWordFound) {
                stack.add(command.toInt())
            }
            //If we don't have a number, we either have a ':' or words or operands:
            else {
                //If we have an operator: '+', "-", "/", "*"
                if (command.length == 1 && command != ":" && command != ";"
                    && !command.all { it.isDigit() } && !newWordFound) {
                    operation(command)
                }
                else if (command == ":" && !newWordFound){
                    newWordFound = true
                }
                else if (newWordFound){
                    if (!titleFound){
                        if (command.all { it.isDigit() }){
                            throw Exception("illegal operation")
                        }
                        else {
                            procedureTitles.add(command)
                            titleFound = true
                        }
                    }
                    else{
                        if (command != ";")
                           stringToAdd += " $command"
                        else{
                            procedureOps.add(stringToAdd.removePrefix(" "))
                            stringToAdd = ""
                            titleFound = false
                            newWordFound = false
                        }
                    }
                }
                //Last case, we have a stack operation: dup, drop, swap, over
                else if (command.length > 1) {
                    stackOperation(command)
                }
            }
        }
        return stack
    }
    //Function used for arithmetic operations:
    private fun operation (action: String) {
        if (procedureTitles.contains(action))
            otherOpFound(action)
        else {
            when (action) {
                "+" -> {
                    if (stack.size >= 2) {
                        val newValue = stack[0] + stack[1]
                        stack.removeAt(0)
                        stack.removeAt(0)
                        stack.add(0, newValue)
                    } else {
                        if (stack.size == 0)
                            throw Exception("empty stack")
                        else
                            throw Exception("only one value on the stack")
                    }
                }
                "-" -> {
                    if (stack.size >= 2) {
                        val newValue = stack[0] - stack[1]
                        stack.removeAt(0)
                        stack.removeAt(0)
                        stack.add(0, newValue)
                    } else {
                        if (stack.size == 0)
                            throw Exception("empty stack")
                        else
                            throw Exception("only one value on the stack")
                    }
                }
                "*" -> {
                    if (stack.size >= 2) {
                        val newValue = stack[0] * stack[1]
                        stack.removeAt(0)
                        stack.removeAt(0)
                        stack.add(0, newValue)
                    } else {
                        if (stack.size == 0)
                            throw Exception("empty stack")
                        else
                            throw Exception("only one value on the stack")
                    }
                }
                "/" -> {
                    if (stack.size >= 2) {
                        if (stack[1] != 0) {
                            val newValue = stack[0] / stack[1]
                            stack.removeAt(0)
                            stack.removeAt(0)
                            stack.add(0, newValue)
                        } else
                            throw Exception("divide by zero")
                    } else {
                        if (stack.size == 0)
                            throw Exception("empty stack")
                        else
                            throw Exception("only one value on the stack")
                    }
                }
                else -> println("Error! No operation sent!")
            }
        }
    }
    //Function used for stack operations:
    private fun stackOperation (action: String) {
        if (procedureTitles.contains(action))
            otherOpFound(action)
        else{
        when (action) {
            "dup" -> {
                if (stack.size >= 1) {
                    stack.add(stack.last())
                } else
                    throw Exception("empty stack")
            }
            "drop" -> {
                if (stack.size >= 1) {
                    stack.removeAt(stack.lastIndex)
                } else
                    throw Exception("empty stack")
            }
            "swap" -> {
                if (stack.size >= 2) {
                    val newValues = mutableListOf(stack.removeAt(stack.lastIndex))
                    newValues.add(stack.removeAt(stack.lastIndex))
                    stack.addAll(newValues)
                } else {
                    if (stack.size == 0)
                        throw Exception("empty stack")
                    else
                        throw Exception("only one value on the stack")
                }
            }
            "over" -> {
                if (stack.size >= 2) {
                    stack.add(stack[stack.lastIndex - 1])
                } else {
                    if (stack.size == 0)
                        throw Exception("empty stack")
                    else
                        throw Exception("only one value on the stack")
                }
            }
            else -> {
                if (procedureTitles.contains(action))
                    otherOpFound(action)
                else
                    throw Exception("undefined operation")
            }
        }
        }
    }
    //Function for the new word found:
    private fun otherOpFound (word: String) {
        val indexToUse: Int
        //We have found a word, search for it in the procedure list:
        if (!recursiveCall) {
            if (procedureTitles.contains(word))
                indexToUse = procedureTitles.lastIndexOf(word)
            else {
                throw Exception(
                    "Procedure not found!!! word is $word"
                )
            }
        }
        else{
            if (procedureTitles.slice(0..recursiveIndex).contains(word)) {
                recursiveCall = false
                indexToUse = procedureTitles.slice(0..recursiveIndex).lastIndexOf(word)
            }
            else {
                throw Exception(
                    "Procedure not found!!! Word $word has not been defined before ${procedureTitles[recursiveIndex]}"
                )
            }
        }
        val procedureWords = procedureOps[indexToUse].split(" ")
        for (command in procedureWords){
            if (command.all { it.isDigit() }) {
                    stack.add(command.toInt())
                }
            //If we don't have a number, we either have a ':' or words or operands:
            else {
                //If we have an operator: '+', "-", "/", "*"
                if (command.length == 1 && command != "!") {
                    operation(command)
                }
                else if (procedureTitles.contains(command)){
                    recursiveCall = true
                    stackOperation(command)
                }
                //Last case, we have a stack operation: dup, drop, swap, over
                else {
                    stackOperation(command)
                }
            }
        }
    }
}