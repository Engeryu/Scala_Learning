// Create a class that represents a train
// The train is defined by a name and a list of wagons
// Each wagon is defined by a list of seats
// The train should have a method to add passengers to any free seat
// The train should have a toString method
import scala.collection.mutable.Map

class Train(val wagon: String) {
  val trainName: String = "YOUPI"
  var listWagons: List[String] = List(wagon)
  var mapSeat: Map[Int, String] = Map((1 to 30).map(i => (i, ""))*)

    def addPassenger(nomPassager: String): Unit = {
    if (mapSeat.exists(_._2.isEmpty)) {
      val siegeLibre = mapSeat.find(_._2.isEmpty).get
      mapSeat(siegeLibre._1) = nomPassager
      println(f"Le passager ${nomPassager} a le siège ${siegeLibre._1} " +
        f"de réservé dans la wagon ${wagon} du train ${trainName}.")
    } else {
      println("Désolé, tous les sièges sont déjà réservés.")
    }
  }
  override def toString: String = {
    s"Train $trainName avec wagons ${listWagons.mkString(", ")} et " +
      s"places disponibles: ${mapSeat.count(_._2.isEmpty)}."
  }
}

object Exercise1 {
  def main(args: Array[String]): Unit = {
    val maResa = new Train("TOTO")
    maResa.addPassenger("Georges")
    println(maResa) // Print train details
  }
}