import kotlin.system.measureTimeMillis

// Bubble Sort
fun bubbleSort(arr: IntArray): IntArray {
    val n = arr.size
    for (i in 0 until n - 1) {
        for (j in 0 until n - i - 1) {
            if (arr[j] > arr[j + 1]) {
                val temp = arr[j]
                arr[j] = arr[j + 1]
                arr[j + 1] = temp
            }
        }
    }
    return arr
}

// Quick Sort
fun quickSort(arr: IntArray, low: Int, high: Int): IntArray {
    if (low < high) {
        val pi = partition(arr, low, high)
        quickSort(arr, low, pi - 1)
        quickSort(arr, pi + 1, high)
    }
    return arr
}

fun partition(arr: IntArray, low: Int, high: Int): Int {
    val pivot = arr[high]
    var i = low - 1
    for (j in low until high) {
        if (arr[j] < pivot) {
            i++
            val temp = arr[i]
            arr[i] = arr[j]
            arr[j] = temp
        }
    }
    val temp = arr[i + 1]
    arr[i + 1] = arr[high]
    arr[high] = temp
    return i + 1
}

fun main() {
    // Генеруємо масив з 1000 випадкових елементів
    val arr1 = IntArray(1000) { (0..1000).random() }
    val arr2 = arr1.copyOf()

    // Порівнюємо час виконання для Bubble Sort
    val bubbleSortTime = measureTimeMillis {
        bubbleSort(arr1)
    }
    println("Bubble Sort Time: $bubbleSortTime ms")

    // Порівнюємо час виконання для Quick Sort
    val quickSortTime = measureTimeMillis {
        quickSort(arr2, 0, arr2.size - 1)
    }
    println("Quick Sort Time: $quickSortTime ms")
}
