package com.github.bsoptei.particlesimulation.ui

object InputManager {
    import natEnv._

  private var inp   = 100
  private var steps = 100

  def getInp:   Int = inp
  def getSteps: Int = steps

  def simulationargs = s"$temperature/$specificGravity/$inp/$steps"

  def incTemp(i : Int) = {
    if ((i >= 0) || (i < 0 && temperature + i >= 0)) {
      temperature += i
    }
  }

  def incGrav(i : Int) = {
      specificGravity += i
  }

  def incInp(i: Int) = {
    if ((i >= 0) || (i < 0 && inp + i > 0)) {
      inp += i * 100
    }
  }

  def incSteps(i: Int) = {
    if ((i >= 0) || (i < 0 && steps + i > 0)) {
      steps += i * 10
    }
  }

}
