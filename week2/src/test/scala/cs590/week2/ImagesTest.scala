package cs590.week2

import org.scalatest.FunSuite

class ImagesTest extends FunSuite with Images with Codegen {

  test("allred") {

    def allRed(p: Point): Color = p match { case (x,y) => (x, Times(Const(0.4), y), Const(0), Const(1)) }

    // NOTE: we can write this shorter as
    //
    //     def allRed(p: Point): Color = p match { case (x,y) => (x, 0.5 * y, 0, 1) }
    //
    // thanks to operator overloading (def *(that: DoubleE) = ...) and implicit conversion
    // from Double to DoubleE (implicit def unit(x: Double) = ...).

    generateImage("allred.html", allRed)
  }

  ignore("checkers") {

    def checkers(p: Point): Color = {
      ??? // draw a checkerboard
    }

    generateImage("checkers.html", checkers)
  }

}
