// In this exercise you will be using for loops and if, else statements
object Exercise1 {

  // FizzBuzz is a classic interviewing question
  // Write a short program that prints each number from 1 to 100 on a new line.
  // For each multiple of 3, print "Fizz" instead of the number.
  // For each multiple of 5, print "Buzz" instead of the number.
  // For numbers which are multiples of both 3 and 5, print "FizzBuzz" instead of the number.
  def fizzBuzz(n: Int): Unit = {
    for (i <- 1 to n) {
      if (i % 3 == 0 && i % 5 == 0) {
        println("FizzBuzz")
      } else if (i % 3 == 0) {
        println("Fizz")
      } else if (i % 5 == 0) {
        println("Buzz")
      } else {
        println(i)
      }
    }
  }

  def main(args: Array[String]): Unit = {
    fizzBuzz(20)
  }
}
