package com.github.bsoptei.particlesimulation.ui

import com.github.bsoptei.particlesimulation.shared.model.Particle
import org.scalajs.dom.html.Canvas
import org.scalajs.dom.{CanvasRenderingContext2D, document}
import org.scalajs.jquery._
import prickle._
import com.github.bsoptei.particlesimulation.shared.util.implicits._
import com.github.bsoptei.particlesimulation.shared.util.implicits.ANY._
import com.github.bsoptei.particlesimulation.stats.Stats

import com.github.bsoptei.particlesimulation.exportedfunctions.ExportedFunctions._

import natEnv._

import com.github.bsoptei.particlesimulation.helpers._

class DOMManager() {

  private lazy val canvas: Canvas = $("canvas").get(0).as[Canvas]
  private lazy val ctx2D: CanvasRenderingContext2D = canvas.getContext("2d").as[CanvasRenderingContext2D]

  def init(): Unit = $(document).ready(
    {
      createUIElements()
      addTextAndEventListeners()
      initIndicators()
      setCanvasDimensions()
    }
  )

  def update(response: String): Unit = {
    val particleStates: Seq[Seq[Particle]] =
      Unpickle[Seq[Seq[Particle]]]
        .fromString(response)
        .toOption.getOrElse(Nil)

    val numberOfCollisions: Int           = particleStates.last.size - particleStates.head.size
    val latestStuff:        Seq[Particle] = particleStates.headOption.getOrElse(Nil)
    val latestStats:        String        = Stats.stats(latestStuff)(_.radius)

    setIndicator("status", s"Results received.")
    setIndicator("stats" , s"$latestStats , number of  collisions: $numberOfCollisions")
    drawParticles(latestStuff)
  }

  private def drawParticles(particles: Seq[Particle]): Unit = {

    ctx2D.fillStyle = "#000"
    ctx2D.fillRect(0, 0, canvas.width, canvas.height)
    particles.sortBy(_.pos.z).foreach{ particle =>
      ctx2D.fillStyle = determineColor(particle.radius.toInt)

      val pos = particle.pos
      val apparentSize = particle.radius * (pos.z / 50)
      ctx2D.beginPath()
      ctx2D.arc(pos.x, pos.y, apparentSize, 0, 360, anticlockwise = false)
      ctx2D.fill()
      ctx2D.closePath()
    }
  }

  private def setCanvasDimensions(): Unit = {
    canvas.width = 500
    canvas.height = 500
  }

  private def initIndicators(): Unit = {
    setIndicator("temperature", temperature)
    setIndicator("gravity", specificGravity)
    setIndicator("inp", inp)
    setIndicator("steps", steps)
    setIndicator("status", "Ready")
    setIndicator("stats", "")
  }

  private val $: JQueryStatic = jQuery

  private def $append(objName: String)(withObj: String) = $(objName).append(withObj)

  // TODO refactor
  private def addTextAndEventListeners(): Unit = {
    $("#tempPlus").text("T +").click(modTemperature(1))
    $("#tempMinus").text("T -").click(modTemperature(-1))
    $("#gravPlus").text("G +").click(modGravity(1))
    $("#gravMinus").text("G -").click(modGravity(-1))
    $("#inpPlus").text("INP +").click(modInp(1))
    $("#inpMinus").text("INP -").click(modInp(-1))
    $("#stepsPlus").text("S +").click(modSteps(1))
    $("#stepsMinus").text("S -").click(modSteps(-1))
    $("#simulate").text("Simulate").click(reqSimulationResults())
  }

  private def createUIElements(): Unit = {
    appendBody("<main></main>")
    itemsToAppend.foreach(appendMain)
  }

  def setIndicator(indicatorId: String, numberToShow: String): JQuery =
    $(s"#$indicatorId").text(s"${indicatorId.capitalize}: $numberToShow")


  private val appendMain = $append("main")(_)
  private val appendBody = $append("body")(_)

  private val itemsToAppend =
    Seq(
      "<p id=\"temperature\">",
      "<p id=\"gravity\">",
      "<p id=\"inp\">",
      "<p id=\"steps\">",
      "<p id=\"status\">",
      "<p id=\"stats\">",
      "<button id=\"tempPlus\">",
      "<button id=\"tempMinus\">",
      "<button id=\"gravPlus\">",
      "<button id=\"gravMinus\">",
      "<button id=\"inpPlus\">",
      "<button id=\"inpMinus\">",
      "<button id=\"stepsPlus\">",
      "<button id=\"stepsMinus\">",
      "<button id=\"simulate\">",
      "<canvas>"
    )

}
