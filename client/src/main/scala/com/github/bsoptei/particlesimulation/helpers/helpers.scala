package com.github.bsoptei.particlesimulation

package object helpers {

  def determineColor(x: Int): String = {
    x match {
      case n if 0  until 5  contains n => "#500"
      case n if 5  until 10 contains n => "#600"
      case n if 10 until 15 contains n => "#700"
      case n if 15 until 20 contains n => "#800"
      case n if 20 until 25 contains n => "#900"
      case n if 25 until 30 contains n => "#A00"
      case n if 30 until 35 contains n => "#B00"
      case n if 35 until 40 contains n => "#C00"
      case n if 45 until 50 contains n => "#D00"
      case n if 50 until 55 contains n => "#E00"
      case _ => "#F00"
    }
  }

}
