// In this exercise, pay extra attention to the usage of val and var
// Use val when the variable is not supposed to change after its definition
// Use var when the variable will change after its definition
object Exercise0 {

  // Write a function that will mix two strings, taking the first char of the first string and then the first of the
  // second string, and so on...
  // For example mingleString("aaa", "bbb") -> "ababab"
  // To simplify the exercise you can assume the inputs are of same length
  def mingleString(u: String, v: String): String = {
    // TODO: implement the function here
    u.zip(v).map {case (charU, charV) => s"$charU$charV"}.mkString
  }

  def main(args: Array[String]): Unit = {
    println(mingleString("aaa", "bbb"))
    println(mingleString("jdabocm", "eh otap"))
  }

}
