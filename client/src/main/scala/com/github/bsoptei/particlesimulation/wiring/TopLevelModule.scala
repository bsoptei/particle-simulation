package com.github.bsoptei.particlesimulation.wiring

import com.github.bsoptei.particlesimulation.ui.UI

trait TopLevelModule {
  import com.softwaremill.macwire._
  lazy val ui = wire[UI]
}
