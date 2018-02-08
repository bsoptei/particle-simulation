package com.github.bsoptei.particlesimulation.wiring

import com.github.bsoptei.particlesimulation.ui.{DOMManager, InputManager}

trait UIModule {
  import com.softwaremill.macwire._
  lazy val theInputManager = wire[InputManager]
  lazy val theDomManager   = wire[DOMManager]
}