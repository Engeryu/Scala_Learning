// Create a class that represents fractions and methods to
// - multiply
// - add
// - approximate the result
// - toString method
class Fraction(numerator: Int, denominator: Int) {

  // Method to multiply two fractions
  def multiply(other: Fraction): Fraction = {
    new Fraction(numerator * other.numerator, denominator * other.denominator)
  }

  // Method to add two fractions
  def add(other: Fraction): Fraction = {
    val commonDenominator = denominator * other.denominator
    val newNumerator = (numerator * other.denominator) + (other.numerator * denominator)
    new Fraction(newNumerator, commonDenominator)
  }

  // Method to approximate the fraction as a decimal
  def approximate: Double = {
    numerator.toDouble / denominator
  }

  // toString method for representing the fraction
  override def toString: String = {
    s"$numerator/$denominator"
  }
}

object Exercise0 {
  def main(args: Array[String]): Unit = {
    val fraction1 = new Fraction(1, 2)
    val fraction2 = new Fraction(3, 4)

    val multiplied = fraction1.multiply(fraction2)
    val added = fraction1.add(fraction2)
    val approximation = added.approximate

    println(s"Fraction 1: $fraction1")
    println(s"Fraction 2: $fraction2")
    println(s"Multiplied: $multiplied")
    println(s"Added: $added")
    println(s"Approximation of added fraction: $approximation")
  }
}