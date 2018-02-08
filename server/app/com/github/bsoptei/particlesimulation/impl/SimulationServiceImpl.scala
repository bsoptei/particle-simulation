package com.github.bsoptei.particlesimulation.impl

import com.github.bsoptei.particlesimulation.model.SimulationBox
import com.github.bsoptei.particlesimulation.service.SimulationService
import com.github.bsoptei.particlesimulation.shared.model.{NaturalEnvironment, SimulationInput}
import prickle._

class SimulationServiceImpl extends SimulationService {

  override def runSimulation(temp: Int, grav: Int, inp: Int, steps: Int): String =
    Pickle.intoString(
      new SimulationBox(
        SimulationInput(
          NaturalEnvironment(temp, grav),
          inp,
          steps
        )
      ).simulate
    )

}
