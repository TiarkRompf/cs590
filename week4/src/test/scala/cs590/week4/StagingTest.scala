package cs590.week4

import scala.virtualization.lms.common._
import org.scalatest.FunSuite
import java.io.PrintWriter

class StagingTest extends FunSuite {

  test("power1") {
    val driver = new Power1 with ScalaOpsPkgExp with CompileScala { self => 
      val codegen = new ScalaCodeGenPkg { val IR: self.type = self }
    }

    val power4c = {
      import driver._

      val power4 = (x:Rep[Double]) => power(x,4)
      
      codegen.emitSource(power4, "Power4", new PrintWriter(System.out))
      compile(power4)
    }

    println("3^4 = ")
    println(power4c(3))
  }

}