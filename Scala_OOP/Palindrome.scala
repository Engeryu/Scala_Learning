// As in python, in Scala you can import external libraries.
// "scala.io.StdIn.readLine" will allow the user to type text in the terminal.
import scala.io.StdIn.readLine;

object Palindrome {

  def reverseString(input: String): String = {
    input.reverse
  }

  def isPalindrome(input: String): Boolean = {
    reverseString(input) == input
  }

  def main(args: Array[String]): Unit = {
    println("Please enter a string and press enter.")
    var userInput = readLine()

    var reversedStr = reverseString(userInput)
    println(f"The reversed string is: $reversedStr")

    var isAPalindrome = isPalindrome(userInput)
    println(f"Is $userInput%s a palindrom? $isAPalindrome%s")
  }
}

// You feel stuck? This is ok, whe did not teach you anything yet!
// A cheatsheet to remember the syntaxt https://docs.scala-lang.org/cheatsheets/index.html
// Search for a function's usage: https://www.scala-lang.org/api/current/