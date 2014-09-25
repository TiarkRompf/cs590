package cs590.week4

import scala.virtualization.lms.common._
import org.scalatest.FunSuite
import java.io.PrintWriter

class StagingTest extends FunSuite {

    test("power1") {
      val o = new Power1 with ScalaOpsPkgExp with CompileScala { self => 
        val codegen = new ScalaCodeGenPkg { val IR: self.type = self }
      }
      import o._

      val power4 = (x:Rep[Double]) => power(x,4)
      codegen.emitSource(power4, "Power4", new PrintWriter(System.out))

      val power4c = compile(power4)
      println(power4c(2))
    }

}