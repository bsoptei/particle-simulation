package com.github.bsoptei.particlesimulation.controllers

import javax.inject._

import com.github.bsoptei.particlesimulation.service.SimulationService
import play.api.mvc._

@Singleton
class ApplicationController @Inject()(cc: ControllerComponents,
                                      simulationService: SimulationService) extends AbstractController(cc) {

  def index  = Action { Ok(views.html.index()) }

  def simulate(param1: Int, param2: Int, param3: Int, param4: Int) = Action {
    Ok(
      simulationService.runSimulation(
        param1, param2, param3, param4
      )
    )
  }

}
