/**
 * @Author: Engeryu
 * @Date:   1970-01-01 01:00:00
 * @Last Modified by:   Engeryu
 * @Last Modified time: 2025-03-31 17:14:51
 */
/*
Let's start writing our first project. Find a partner and start coding a calendar app together! Your program should:

Be a command line interface application
Allow to create a calendar entry (Name, Datetime, Duration)
Display all entries in the calendar
Delete and modify entries
*/

import java.time.{LocalDate, LocalTime}
import java.time.format.DateTimeFormatter
import scala.io.Source
import scala.io.StdIn.readLine
import java.io.PrintWriter

val dateFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
val timeFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm")
val filename = "src/calendar_entries.csv"
var entries: List[(String, LocalDate, LocalTime, Int)] = loadEntriesFromFile()

def loadEntriesFromFile(): List[(String, LocalDate, LocalTime, Int)] = {
  val file = new java.io.File(filename)
  if (!file.exists()) {
    println(s"Fichier $filename non trouvé. Création d'un nouveau fichier.")
    file.createNewFile()
    List()
  } else {
    Source.fromFile(file, "UTF-8").getLines().toList.flatMap { line =>
      val parts = line.split(",")
      if (parts.length == 4) try {
        Some((parts(0), LocalDate.parse(parts(1), dateFormatter), 
              LocalTime.parse(parts(2), timeFormatter), parts(3).toInt))
      } catch {
        case _: Exception => None
      } else None
    }
  }
}

def saveEntriesToFile(): Unit = {
  val writer = new PrintWriter(filename, "UTF-8")
  writer.println("Event,Date,Time,Duration") // Inclut les en-têtes
  entries.foreach { case (name, date, time, duration) =>
    writer.println(s"$name,${date.format(dateFormatter)},${time.format(timeFormatter)},$duration")
  }
  writer.close()
}

def getValidInput[T](prompt: String, parser: String => T, errorMsg: String): T = {
  var valid = false
  var value: T = null.asInstanceOf[T]
  while (!valid) {
    println(prompt)
    val input = readLine()
    try {
      value = parser(input)
      valid = true
    } catch {
      case _: Exception => println(errorMsg)
    }
  }
  value
}

def addEntry(): Unit = {
  val name = getValidInput("Entrez le nom de l'événement :", identity, "Nom invalide.")
  val date = getValidInput("Entrez la date (format : dd-MM-yyyy) :", 
                           LocalDate.parse(_, dateFormatter), "Format invalide. Réessayez.")
  val time = getValidInput("Entrez l'heure (format : HH:mm) :", 
                           LocalTime.parse(_, timeFormatter), "Format invalide. Réessayez.")
  val duration = getValidInput("Entrez la durée (en minutes) :", 
                               _.toInt, "Durée invalide. Entrez un entier.")
  entries :+= (name, date, time, duration)
  println(s"Entrée ajoutée : $name, $date, $time, $duration minutes.")
  saveEntriesToFile()
}

def displayEntries(): Unit = {
  if (entries.isEmpty) {
    println("Aucune entrée dans le calendrier.")
  } else {
    println("| %-20s | %-10s | %-5s | %-8s |".format("Event", "Date", "Time", "Duration"))
    println("|" + "-" * 22 + "|" + "-" * 12 + "|" + "-" * 7 + "|" + "-" * 10 + "|")
    entries.foreach { case (name, date, time, duration) =>
      println("| %-20s | %-10s | %-5s | %-8d |".format(
        name.take(20), date.format(dateFormatter), time.format(timeFormatter), duration))
    }
  }
}

def deleteEntry(): Unit = {
  displayEntries()
  val index = getValidInput("Entrez le numéro de l'entrée à supprimer :", 
                            _.toInt, "Numéro invalide.") - 1
  if (index >= 0 && index < entries.length) {
    entries = entries.patch(index, Nil, 1)
    println("Entrée supprimée.")
    saveEntriesToFile()
  } else {
    println("Numéro invalide.")
  }
}

def modifyEntry(): Unit = {
  displayEntries()
  val index = getValidInput("Entrez le numéro de l'entrée à modifier :", 
                            _.toInt, "Numéro invalide.") - 1
  if (index >= 0 && index < entries.length) {
    val (oldName, oldDate, oldTime, oldDuration) = entries(index)
    val newName = getValidInput(s"Nom ($oldName) :", s => if (s.isEmpty) oldName else s, "")
    val newDate = getValidInput(s"Date ($oldDate) :", 
                                s => if (s.isEmpty) oldDate else LocalDate.parse(s, dateFormatter), "")
    val newTime = getValidInput(s"Heure ($oldTime) :", 
                                s => if (s.isEmpty) oldTime else LocalTime.parse(s, timeFormatter), "")
    val newDuration = getValidInput(s"Durée ($oldDuration) :", 
                                    s => if (s.isEmpty) oldDuration else s.toInt, "")
    entries = entries.updated(index, (newName, newDate, newTime, newDuration))
    println("Entrée modifiée.")
    saveEntriesToFile()
  } else {
    println("Numéro invalide.")
  }
}

def main(args: Array[String]): Unit = {
  var continueProgram = true
  while (continueProgram) {
    println("\nMenu principal :")
    println("1. Ajouter une entrée")
    println("2. Afficher toutes les entrées")
    println("3. Supprimer une entrée")
    println("4. Modifier une entrée")
    println("5. Quitter")
    readLine() match {
      case "1" => addEntry()
      case "2" => displayEntries()
      case "3" => deleteEntry()
      case "4" => modifyEntry()
      case "5" =>
        continueProgram = false
        println("Au revoir !")
      case _ => println("Option invalide. Réessayez.")
    }
  }
}
