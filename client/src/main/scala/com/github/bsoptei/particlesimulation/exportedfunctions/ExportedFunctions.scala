package com.github.bsoptei.particlesimulation.exportedfunctions

import com.github.bsoptei.particlesimulation.components._
import com.github.bsoptei.particlesimulation.ui.{DomAction, DomActionUnit}
import com.github.bsoptei.particlesimulation.wiring.TopLevelModule

import scala.concurrent.ExecutionContext.Implicits.global
import scala.scalajs.js.annotation.JSExportTopLevel
import scala.util.{Failure, Success}

object ExportedFunctions extends TopLevelModule {

  @JSExportTopLevel("modTemperature")
  def modTemperature(i : Int): DomAction = (e) => ui.manageControl(Temp)(i)

  @JSExportTopLevel("modGravity")
  def modGravity(i : Int):     DomAction = (e) => ui.manageControl(Grav)(i)

  @JSExportTopLevel("modInp")
  def modInp(i : Int):         DomAction = (e) => ui.manageControl(Inps)(i)

  @JSExportTopLevel("modSteps")
  def modSteps(i : Int):       DomAction = (e) => ui.manageControl(Step)(i)

  @JSExportTopLevel("reqSimulationResults")
  def reqSimulationResults():  DomActionUnit = (e) => {
    ui.sendSimulationRequest().onComplete {
      case Success(xhr) => ui.update(xhr.responseText)
      case Failure(err) => println(err.toString)
    }
  }

}
