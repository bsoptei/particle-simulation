import com.google.inject.AbstractModule
import com.github.bsoptei.particlesimulation.impl.SimulationServiceImpl
import com.github.bsoptei.particlesimulation.service.SimulationService

class Module extends AbstractModule {

  override def configure(): Unit = {
    bind(classOf[SimulationService]).to(classOf[SimulationServiceImpl])
  }

}