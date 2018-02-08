package com.github.bsoptei.particlesimulation.model

import com.github.bsoptei.particlesimulation.shared.model.{Particle, Point3D, SimulationInput}

import com.github.bsoptei.particlesimulation.shared.util._
import com.github.bsoptei.particlesimulation.shared.util.implicits._

import scala.annotation.tailrec

class SimulationBox(simulationInput: SimulationInput) {
  import SimulationBox._
  import simulationInput._
  import simulationInput.env._

  val initialParticleStates: List[List[Particle]] = initialParticles(initialNumberOfParticles)

  def simulate: List[List[Particle]] = {
    @tailrec
    def loop(acc: List[List[Particle]], counter: Int): List[List[Particle]] =
      if (counter == 0) acc
      else loop(collide(moveAll(acc.head)).toList :: acc, counter - 1)

    loop(initialParticleStates, numberOfSteps)
  }

  private def moveAll(particles: Iterable[Particle]) = particles.map{ particle =>
    particle.move(
      Point3D(
        calcNew(particle.pos.x, movFromTemp(), constraints.x),
        calcNew(particle.pos.y, movFromTemp() + gravEffect, constraints.y),
        calcNew(particle.pos.z, movFromTemp(), constraints.z)
      )
    )
  }

  private def merge(particles: Iterable[Particle]): Particle =
    particles.head.copy(
      radius = particles.map(_.radius).sum
    )

  private def collide(particles: Iterable[Particle]): Iterable[Particle] =
    findColliding(particles).values.flatMap { ps =>
      ps.size match {
        case 0 => Nil
        case 1 => ps
        case _ => Seq(merge(ps))
      }
    }.toList

  private def findColliding(particles: Iterable[Particle]): Map[Point3D, Iterable[Particle]] = particles.groupBy{ _.pos }

  private def motion: Int = if (temperature <= 0) 0 else rni(10) + temperature

  private def calcNew(a: Double, b: Double, max: Double): Double = if (a + b <= max && a + b >= 0) a + b else a

  private def movFromTemp(): Int = motion * plusMinusOne

  private def gravEffect: Int = if (temperature == 0) 0 else temperature * specificGravity

}

object SimulationBox {

  import scala.util.Random

  private val cw, ch = 500

  private val rand = new Random()

  def initialParticles(n: Int): List[List[Particle]] =
    List(
      List.fill(n)(
        Particle(
          Point3D(
            rni(constraints.x),
            rni(constraints.y),
            rni(constraints.z) + 1
          )
          ,
          rni(25) + 10
        )
      )
    )

  private def rni(i: Int): Int = rand.nextInt(i)

  private def plusMinusOne: Int = if (Math.random() < 0.5) 1 else -1

}


