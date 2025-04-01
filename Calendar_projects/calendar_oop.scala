/*
Building the calendar app without the use of classes leads to a code difficult to maintain.

Try to refactor the code from yesterday to keep the same functionalities but using Objects and Design Patterns

The basic refactoring:

The calendar should become a class
Use a singleton object to access the calendar everywhere is your project
More advanced refactoring:

Modularize the Command Line Interface
Handle Exceptions with Either
*/

import java.time.{LocalDate, LocalTime}
import java.time.format.DateTimeFormatter
import scala.io.Source
import scala.io.StdIn.readLine
import java.io.PrintWriter

// Singleton Object for CalendarApp
object CalendarApp {
  val dateFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
  val timeFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm")
  private val filename: String = "calendar_entries.csv"

  // Singleton calendar instance
  object Calendar {
    private var entries: List[CalendarEntry] = loadEntriesFromFile()

    def getEntries: List[CalendarEntry] = entries

    def addEntry(entry: CalendarEntry): Unit = {
      entries :+= entry
      saveEntriesToFile()
    }

    def updateEntry(index: Int, newEntry: CalendarEntry): Unit = {
      entries = entries.updated(index, newEntry)
      saveEntriesToFile()
    }

    def deleteEntry(index: Int): Unit = {
      entries = entries.patch(index, Nil, 1)
      saveEntriesToFile()
    }

    private def loadEntriesFromFile(): List[CalendarEntry] = {
      val file = new java.io.File(filename)
      if (!file.exists()) {
        file.createNewFile()
        List()
      } else {
        Source.fromFile(file).getLines().drop(1).flatMap { line =>
          line.split(",") match {
            case Array(event, date, time, duration) =>
              try Some(CalendarEntry(event, LocalDate.parse(date, dateFormatter),
                LocalTime.parse(time, timeFormatter), duration.toInt))
              catch { case _: Exception => None }
            case _ => None
          }
        }.toList
      }
    }

    private def saveEntriesToFile(): Unit = {
      val writer = new PrintWriter(filename)
      writer.println("Event,Date,Time,Duration") // Headers
      entries.foreach { e =>
        writer.println(s"${e.event},${e.date.format(dateFormatter)},${e.time.format(timeFormatter)},${e.duration}")
      }
      writer.close()
    }
  }

  // Case Class to represent a calendar entry
  case class CalendarEntry(event: String, date: LocalDate, time: LocalTime, duration: Int)

  // Helper function to validate user input
  def getValidInput[T](prompt: String, parse: String => Either[String, T]): T = {
    Iterator.continually {
      println(prompt)
      readLine()
    }.map(input => parse(input) match {
      case Right(value) => Some(value)
      case Left(error) => println(error); None
    }).collectFirst { case Some(value) => value }.get
  }

  // Command Line Interface
  object CommandLineInterface {
    def addEntry(): Unit = {
      val entry = CalendarEntry(
        event = getValidInput("Nom de l'événement :", input => Right(input).filterOrElse(_.nonEmpty, "Nom invalide.")),
        date = getValidInput("Date (yyyy-MM-dd) :", input =>
          Either.catchOnly[Exception](LocalDate.parse(input, dateFormatter)).left.map(_ => "Date invalide.")),
        time = getValidInput("Heure (HH:mm) :", input =>
          Either.catchOnly[Exception](LocalTime.parse(input, timeFormatter)).left.map(_ => "Heure invalide.")),
        duration = getValidInput("Durée (minutes) :", input =>
          Either.catchOnly[Exception](input.toInt).left.map(_ => "Durée invalide."))
      )
      Calendar.addEntry(entry)
      println(s"Entrée ajoutée : $entry")
    }

    def displayEntries(): Unit = {
      if (Calendar.getEntries.isEmpty) {
        println("Aucune entrée dans le calendrier.")
      } else {
        println("| %-20s | %-10s | %-5s | %-8s |".format("Event", "Date", "Time", "Duration"))
        println("|" + "-" * 22 + "|" + "-" * 12 + "|" + "-" * 7 + "|" + "-" * 10 + "|")
        Calendar.getEntries.zipWithIndex.foreach { case (e, i) =>
          println(f"| ${e.event.take(20)}%-20s | ${e.date.format(dateFormatter)}%-10s | ${e.time.format(timeFormatter)}%-5s | ${e.duration}%-8d |")
        }
      }
    }

    def deleteEntry(): Unit = {
      displayEntries()
      val index = getValidInput("Numéro de l'entrée à supprimer :", input =>
        Either.catchOnly[Exception](input.toInt - 1).left.map(_ => "Numéro invalide."))
      if (index >= 0 && index < Calendar.getEntries.length) {
        Calendar.deleteEntry(index)
        println("Entrée supprimée.")
      } else {
        println("Numéro invalide.")
      }
    }

    def modifyEntry(): Unit = {
      displayEntries()
      val index = getValidInput("Numéro de l'entrée à modifier :", input =>
        Either.catchOnly[Exception](input.toInt - 1).left.map(_ => "Numéro invalide."))
      if (index >= 0 && index < Calendar.getEntries.length) {
        val old = Calendar.getEntries(index)
        val updated = CalendarEntry(
          event = getValidInput(s"Nom (${old.event}) :", input => Right(if (input.isEmpty) old.event else input)),
          date = getValidInput(s"Date (${old.date}) :", input =>
            Either.catchOnly[Exception](if (input.isEmpty) old.date else LocalDate.parse(input, dateFormatter)).left.map(_ => "")),
          time = getValidInput(s"Heure (${old.time}) :", input =>
            Either.catchOnly[Exception](if (input.isEmpty) old.time else LocalTime.parse(input, timeFormatter)).left.map(_ => "")),
          duration = getValidInput(s"Durée (${old.duration}) :", input =>
            Either.catchOnly[Exception](if (input.isEmpty) old.duration else input.toInt).left.map(_ => ""))
        )
        Calendar.updateEntry(index, updated)
        println("Entrée modifiée.")
      } else println("Numéro invalide.")
    }

    def mainMenu(): Unit = {
      Iterator.continually {
        println("\nMenu principal :\n1. Ajouter une entrée\n2. Afficher les entrées\n3. Supprimer une entrée\n4. Modifier une entrée\n5. Quitter")
        readLine()
      }.takeWhile(_ != "5").foreach {
        case "1" => addEntry()
        case "2" => displayEntries()
        case "3" => deleteEntry()
        case "4" => modifyEntry()
        case _   => println("Option invalide.")
      }
      println("Au revoir !")
    }
  }
}

object Main extends App {
  CalendarApp.CommandLineInterface.mainMenu()
}
