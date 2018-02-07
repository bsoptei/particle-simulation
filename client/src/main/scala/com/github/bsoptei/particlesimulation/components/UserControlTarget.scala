package com.github.bsoptei.particlesimulation.components

sealed trait UserControlTarget
case object Temp extends UserControlTarget
case object Grav extends UserControlTarget
case object Inps extends UserControlTarget
case object Step extends UserControlTarget