package com.github.bsoptei.particlesimulation.ui

import com.github.bsoptei.particlesimulation.components._
import org.scalajs.dom.XMLHttpRequest
import org.scalajs.dom.ext.Ajax

import com.github.bsoptei.particlesimulation.shared.util.implicits._

import scala.concurrent.Future

object UI {

  import natEnv._

  def init(): Unit = DOMManager.init()

  def update(response: String): Unit = DOMManager.update(response)

  def sendSimulationRequest(): Future[XMLHttpRequest] = {
    val req = Ajax.get(s"http://localhost:9000/simulate/${ InputManager.simulationargs }")
    DOMManager.setIndicator("status", "Waiting for results...")
    req
  }

  def manageControl(uct: UserControlTarget)(i: Int) = {

    uct match {
      case Temp =>
        InputManager.incTemp(i)
        DOMManager.setIndicator("temperature", temperature)
      case Grav =>
        InputManager.incGrav(i)
        DOMManager.setIndicator("gravity", specificGravity)
      case Inps =>
        InputManager.incInp(i)
        DOMManager.setIndicator("inp", InputManager.getInp)
      case Step => InputManager.incSteps(i)
        DOMManager.setIndicator("steps", InputManager.getSteps)

    }
  }

}
