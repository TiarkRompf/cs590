package cs590.week2

import org.scalatest.FunSuite

class ImagesTest extends FunSuite with Images with Codegen {

  test("allred") {

    def allRed(p: Point) = (Const(1.0),Const(0.0), Const(0.0))

    generateImage("allred.html", allRed)
  }

  ignore("checkers") {

    def checkers(p: Point): Color = {
      ??? // draw a checkerboard
    }

    generateImage("checkers.html", checkers)
  }

}
