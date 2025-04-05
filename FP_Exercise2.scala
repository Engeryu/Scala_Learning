import scala.io.StdIn.readLine

// Write the code that can translate the variable in into Either[String, Int]
// Use the specific case matching to process the result differently in the 2 cases
import scala.io.StdIn.readLine

object Exercise2 {

  // Helper function to check if a string is an integer
  def isInt(str: String): Boolean = str.forall(_.isDigit)

  def main(args: Array[String]): Unit = {
    val in = readLine("Enter either a string or an Int: ")

    // Translate the input into Either[String, Int]
    val result: Either[String, Int] = if (isInt(in)) {
      Right(in.toInt) // If the input is an integer, wrap it in Right
    } else {
      Left(in) // Otherwise, wrap it in Left as a string
    }

    // Process the result differently based on the case
    result match {
      case Right(number) => println(s"You entered a valid integer: $number")
      case Left(text)    => println(s"You entered a string: $text")
    }
  }
}