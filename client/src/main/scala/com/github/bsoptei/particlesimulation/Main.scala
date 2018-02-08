package com.github.bsoptei.particlesimulation

import com.github.bsoptei.particlesimulation.wiring.TopLevelModule

object Main extends TopLevelModule {

  def main(args: Array[String]): Unit = ui.init()

}
