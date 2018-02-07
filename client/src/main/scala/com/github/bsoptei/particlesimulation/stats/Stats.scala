package com.github.bsoptei.particlesimulation.stats

object Stats {

  def stats[T](xs: Iterable[T])(f: T => Double): String = {
    val size = xs.size
    val wonderfulness = xs.map(f)
    val mean = wonderfulness.sum / size

    s"Number of items: $size| Mean: $mean| Largest: ${wonderfulness.max} Smallest: ${wonderfulness.min}"
  }
}
