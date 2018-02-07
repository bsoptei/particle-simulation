package com.github.bsoptei.particlesimulation

import com.github.bsoptei.particlesimulation.shared.model.NaturalEnvironment
import org.scalajs.jquery.{JQuery, JQueryEventObject}

package object ui {

  type DomAction     = JQueryEventObject => JQuery
  type DomActionUnit = JQueryEventObject => Unit

  private[ui] val natEnv = NaturalEnvironment(1, 0)


}
