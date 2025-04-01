object Exercise2 {

  // Filtrer une liste de chaînes en fonction des conditions spécifiées
  def filterList(inputList: List[String], beginsWith: String, maxLength: Int): List[String] = {
    // On filtre d'abord les chaînes qui commencent par 'beginsWith', puis on vérifie leur longueur
    inputList.filter(str => str.startsWith(beginsWith) && str.length <= maxLength)
  }

  def main(args: Array[String]): Unit = {
    val list = List("penguin", "pentagon", "shape", "pen", "shopping", "pent", "shining")

    println(filterList(list, "pen", 5))
    println(filterList(list, "pen", 10))
    println(filterList(list, "sh", 5))
  }

}
