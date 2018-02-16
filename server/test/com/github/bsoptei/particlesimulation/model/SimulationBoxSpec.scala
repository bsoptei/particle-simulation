package com.github.bsoptei.particlesimulation.model

import com.github.bsoptei.particlesimulation.shared.model.{NaturalEnvironment, SimulationInput}
import org.specs2.Specification

class SimulationBoxSpec extends Specification{
  override def is = s2"""

    testSimulationBox.simulate should

      return a collection with size of 10     $e1

    """

  private val testSimulationBox = new SimulationBox(SimulationInput(NaturalEnvironment(0, 0), 10, 10))

  def e1 = testSimulationBox.simulate must have size 10
}
