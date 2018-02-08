package com.github.bsoptei.particlesimulation.shared

import com.github.bsoptei.particlesimulation.shared.model.Point3D

package object util {

  object implicits {
    implicit def d2i(d: Double): Int = d.toInt
    implicit def d2s(d: Double): String = d.toString
    implicit def i2s(d: Int): String = d.toString
  }

  def #=#[X](a: Any): X = a.asInstanceOf[X]

  def sgn(n: Int): Int = if (n < 0) - 1 else if (n == 0.0) 0  else  1

  def mean(xs: Iterable[Double]): Double = xs.size match {
    case 0 => Double.NaN
    case 1 => xs.head
    case _ => xs.sum / xs.size
  }

  val constraints = Point3D(500, 500, 50)

}
