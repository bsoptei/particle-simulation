package com.github.bsoptei.particlesimulation.stats

import com.github.bsoptei.particlesimulation.shared.util.implicits._

object Stats {

  def stats[T](xs: Iterable[T])(f: T => Double): String = {
    val size = xs.size
    val wonderfulness = xs.map(f)
    val mean = wonderfulness.sum / size

    Map[String, String](
      "numOfItems" -> size,
      "mean"       -> mean,
      "max"        -> wonderfulness.max,
      "min"        -> wonderfulness.min
    ).toString
  }
}
