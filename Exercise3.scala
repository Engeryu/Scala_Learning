// In this exercise you will be using maps
object Exercise3 {

  // Find the richest client of this bank
  // ("Paul" -> 10.0, "Antoine" -> 20.0, "Charles" -> 5.0) returns Antoine
  def findTheRichest(bank: Map[String, Double]): String = {
    // Find the client with the maximum value
    bank.maxBy(_._2)._1
  }

  // Merge two banks together
  def mergeBanks(firstCurrency: String,
                 firstBank: Map[String, Double],
                 secondCurrency: String,
                 secondBank: Map[String, Double]): Map[String, Map[String, Double]] = {
    (firstBank.keySet ++ secondBank.keySet).map { client =>
      client -> Map(
        firstCurrency -> firstBank.getOrElse(client, 0.0),
        secondCurrency -> secondBank.getOrElse(client, 0.0)
      )
    }.toMap
  }

  def main(args: Array[String]): Unit = {
    val bankInEuros = Map("Paul" -> 10.0, "Antoine" -> 20.0, "Charles" -> 5.0)
    val bankInDollars = Map("Paul" -> 100.0, "Antoine" -> 5.0, "Charles" -> 50.0)

    println(findTheRichest(bankInEuros)) // Output: Antoine
    println(findTheRichest(bankInDollars)) // Output: Paul

    println(mergeBanks("$", bankInDollars, "€", bankInEuros))
    // Output: Map(
    //   Paul -> Map($ -> 100.0, € -> 10.0),
    //   Antoine -> Map($ -> 5.0, € -> 20.0),
    //   Charles -> Map($ -> 50.0, € -> 5.0)
    // )
  }
}