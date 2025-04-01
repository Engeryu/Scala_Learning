// Implement an Object RandomList that generates a list of n Int when constructed
// Create a function that will find the biggest prime number in the list. Remember to use container types when
// no prime number is defined in the list.
import scala.util.Random

class RandomList(n: Int) {

  // Generate a list of n random integers
  private val numbers: List[Int] = List.fill(n)(Random.nextInt(100))

  // Helper function to check if a number is prime
  private def isPrime(num: Int): Boolean = {
    if (num <= 1) false
    else !(2 to math.sqrt(num).toInt).exists(num % _ == 0)
  }

  // Function to find the biggest prime number in the list
  def findBiggestPrime(): Option[Int] = {
    numbers.filter(isPrime).sorted.reverse.headOption
  }

  override def toString: String = numbers.mkString(", ")
}

object Exercise1 {

  def main(args: Array[String]): Unit = {
    val randomNumbers = new RandomList(4)
    println(s"Generated numbers: $randomNumbers")
    println(s"Biggest prime: ${randomNumbers.findBiggestPrime().getOrElse("no prime number")}")
  }
}