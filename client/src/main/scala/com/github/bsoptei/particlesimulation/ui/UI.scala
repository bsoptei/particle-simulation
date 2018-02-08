package com.github.bsoptei.particlesimulation.ui

import com.github.bsoptei.particlesimulation.components._
import org.scalajs.dom.XMLHttpRequest
import org.scalajs.dom.ext.Ajax
import com.github.bsoptei.particlesimulation.shared.util.implicits._
import com.github.bsoptei.particlesimulation.wiring.UIModule

import scala.concurrent.Future

class UI() extends UIModule {

  import natEnv._

  def init(): Unit = theDomManager.init()

  def update(response: String): Unit = theDomManager.update(response)
  def simulationargs = s"$temperature/$specificGravity/$inp/$steps"

  def sendSimulationRequest(): Future[XMLHttpRequest] = {
    val req = Ajax.get(s"http://localhost:9000/simulate/$simulationargs")
    theDomManager.setIndicator("status", "Waiting for results...")
    req
  }

  def manageControl(uct: UserControlTarget)(i: Int) = {

    uct match {
      case Temp =>
        theInputManager.incTemp(i)
        theDomManager.setIndicator("temperature", temperature)
      case Grav =>
        theInputManager.incGrav(i)
        theDomManager.setIndicator("gravity", specificGravity)
      case Inps =>
        theInputManager.incInp(i)
        theDomManager.setIndicator("inp", inp)
      case Step =>
        theInputManager.incSteps(i)
        theDomManager.setIndicator("steps", steps)

    }
  }

}
