
import java.io.FileReader
import kotlin.math.absoluteValue

const val PATH = "C:\\Users\\timpo\\OneDrive\\Namizje\\KotlinTutorial\\AdventOfCode\\src\\"

fun main() {
    crossedWires()
}

fun readFromFileAllLines(file: String): List<String> {
    return try{
        FileReader("${PATH}${file}").readLines()
    }catch(ex:Exception) {
        print(ex.message)
        emptyList()
    }
}

fun readFromFile(file: String, delimiter: String): List<String> {
    return try{
        FileReader("${PATH}${file}").readText().split(delimiter)
    }catch(ex:Exception) {
        print(ex.message)
        emptyList()
    }
}


//1.
fun fuelModules(){
    var data = readFromFileAllLines("Nal1.txt")
    //var data = listOf<String>("100756")
    var result = 0
    var finalResult = 0
    for(element in data) {
        result = (element.toInt() / 3) - 2
        finalResult += result
        while(result > 0) {
            result = (result / 3) - 2
            if(result < 0 ) result = 0
            finalResult += result
        }

    }
    println(finalResult)
}

//2. opcode
fun opCode(data1: String, data2:String): String {
    var data = readFromFile("Nal2.txt", ",").toMutableList()
    data[1] = data1
    data[2] = data2
    //var data = listOf<String>("1","1","1","4","99","5", "6", "0", "99").toMutableList()
    var x = 0
    while(x < data.size){
        var pos1 = data[x+1].toInt()
        var pos2 = data[x+2].toInt()
        var finishDest = data[x+3].toInt()

        if(data[x].toInt() == 1 ){
             var result = data[pos1].toInt() + data[pos2].toInt()
             data[finishDest] = result.toString()
             x += 4
        }
        else if(data[x].toInt() == 2 ){
            var result = data[pos1].toInt() * data[pos2].toInt()
            data[finishDest] = result.toString()
            x += 4
        }
        else if(data[x].toInt() == 99) {
            break
        }
        else{
            x++
        }
    }
    return data[0]
}

//part 2
fun opCodePart2(){
    for(noun in 0..99) {
        for (verb in 0..99) {
            var res = opCode(noun.toString(), verb.toString())
            if(res == "19690720"){
                var output = 100 * noun + verb
                println(output)
            }
            }
        }
    }

//3.
fun crossedWires(){
    var data1 = readFromFile("Nal3_1.txt", ",").toMutableList()
    var data2 = readFromFile("Nal3_2.txt", ",").toMutableList()
    //var data1 = listOf("R75","D30","R83","U83","L12","D49","R71","U7","L72")
    //var data2 = listOf("U62","R66","U55","R34","D71","R55","D58","R83")
    val list = listOf<Int>().toMutableList()

    var res1 = crossedAllPoints(data1)
    var res2 = crossedAllPoints(data2)
    var index1 = 0
    var index2 = 0
    for(el in res1){
        index1++
        for(el2 in res2){
            index2++
            if(el2 == el) {
                var steps = index1+index2
                println(steps) //secondpart
                list.add(el.first.absoluteValue + el.second.absoluteValue)
            }
            }
        index2 = 0
        }
    list.removeAt(0)
    println(list.min())
    }




fun crossedAllPoints(data1: List<String>): MutableList<Pair<Int,Int>>{

    var pair1 = Pair(0,0)
    val list1 = listOf<Pair<Int,Int>>().toMutableList()

    for(el in data1){
        var mov = el.substring(1).toInt()
        when(el[0].toString()){
            "R" -> {
                for(i in  1..mov){
                    list1.add(Pair(pair1.first + i,pair1.second))
                }
                pair1 = pair1.copy(first = list1.last().first, second = list1.last().second)
            }
            "L" -> {
                for(i in  1..mov){
                    list1.add(Pair(pair1.first - i, pair1.second))
                }
                pair1 = pair1.copy(first = list1.last().first, second = list1.last().second)
            }
            "U" -> {
                for(i in  1..mov){
                    list1.add(Pair(pair1.first,pair1.second + i))
                }
                pair1 = pair1.copy(first = list1.last().first, second = list1.last().second)
            }
            "D" -> {
                for(i in  1..mov){
                    list1.add(Pair(pair1.first,pair1.second- i))
                }
                pair1 = pair1.copy(first = list1.last().first, second = list1.last().second)
            }
        }
    }
    return list1
}

