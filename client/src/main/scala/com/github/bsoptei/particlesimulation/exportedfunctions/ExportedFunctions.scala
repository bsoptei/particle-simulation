package com.github.bsoptei.particlesimulation.exportedfunctions

import com.github.bsoptei.particlesimulation.components._
import com.github.bsoptei.particlesimulation.ui.{DomAction, DomActionUnit, UI}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.scalajs.js.annotation.JSExportTopLevel
import scala.util.{Failure, Success}

object ExportedFunctions {

  @JSExportTopLevel("modTemperature")
  def modTemperature(i : Int): DomAction = (e) => UI.manageControl(Temp)(i)

  @JSExportTopLevel("modGravity")
  def modGravity(i : Int):     DomAction = (e) => UI.manageControl(Grav)(i)

  @JSExportTopLevel("modInp")
  def modInp(i : Int):         DomAction = (e) => UI.manageControl(Inps)(i)

  @JSExportTopLevel("modSteps")
  def modSteps(i : Int):       DomAction = (e) => UI.manageControl(Step)(i)

  @JSExportTopLevel("reqSimulationResults")
  def reqSimulationResults():  DomActionUnit = (e) => {
    UI.sendSimulationRequest().onComplete {
      case Success(xhr) => UI.update(xhr.responseText)
      case Failure(err) => println(err.toString)
    }
  }

}
