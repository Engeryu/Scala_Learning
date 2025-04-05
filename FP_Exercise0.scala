// Implement a reverse string function using recursion.
//
// Tip:
// val s:String
// v.tail gives you every char of the string without the first element
// v.head give you the first char of a string
object Exercise0 {

  // Recursive function to reverse a string
  def reverse(s: String): String = {
    if (s.isEmpty) "" // Base case: If the string is empty, return an empty string
    else reverse(s.tail) + s.head // Recursive case: Reverse the tail and append the head
  }

  def main(args: Array[String]): Unit = {
    println(reverse("jedha"))     // Output: ahdej
    println(reverse("bootcamp"))  // Output: pmactoob
  }
}
