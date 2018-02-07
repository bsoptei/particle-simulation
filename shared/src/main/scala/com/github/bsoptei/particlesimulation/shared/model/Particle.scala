package com.github.bsoptei.particlesimulation.shared.model

case class Particle(pos: Point3D, radius: Double = 0) {

  def move(newPos: Point3D): Particle = this.copy(pos = newPos)

}


