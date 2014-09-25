package cs590.week4

import scala.virtualization.lms.common._

trait Power1 extends ScalaOpsPkg with LiftNumeric {

  def power(b: Rep[Double], x: Int): Rep[Double] = 
    if (x == 0) 1.0 else b * power(b, x - 1)

}

trait Power2 extends ScalaOpsPkg with LiftNumeric {

  def power(b: Rep[Double], x: Int): Rep[Double] = ???

  // TODO: implement recursive algorithm that takes only
  // log x multiplications (hint: repeated squaring!)

}


trait FFT extends ScalaOpsPkg with LiftNumeric with Trig {
  
  def omega(k: Int, N: Int): Complex = {
    val kth = -2.0 * k * math.Pi / N
    Complex(cos(kth), sin(kth))
  }

  case class Complex(re: Rep[Double], im: Rep[Double]) {
    def +(that: Complex) = Complex(this.re + that.re, this.im + that.im)
    def -(that: Complex) = Complex(this.re - that.re, this.im - that.im)
    def *(that: Complex) = Complex(this.re * that.re - this.im * that.im, this.re * that.im + this.im * that.re)
  }

  def splitEvenOdd[T](xs: List[T]): (List[T], List[T]) = (xs: @unchecked) match {
    case e :: o :: xt =>
      val (es, os) = splitEvenOdd(xt)
      ((e :: es), (o :: os))
    case Nil => (Nil, Nil)
    // cases?
  }

  def mergeEvenOdd[T](even: List[T], odd: List[T]): List[T] = ((even, odd): @unchecked) match {
    case (Nil, Nil) =>
      Nil
    case ((e :: es), (o :: os)) =>
      e :: (o :: mergeEvenOdd(es, os))
    // cases?
  }

  def fft(xs: List[Complex]): List[Complex] = xs match {
    case (x :: Nil) => xs
    case _ =>
      val N = xs.length // assume it's a power of two
      val (even0, odd0) = splitEvenOdd(xs)
      val (even1, odd1) = (fft(even0), fft(odd0))
      val (even2, odd2) = (even1 zip odd1 zipWithIndex) map {
        case ((x, y), k) =>
          val z = omega(k, N) * y
          (x + z, x - z)
      } unzip;
      even2 ::: odd2
  }

}


trait Trig extends Base {

  def sin(x: Rep[Double]): Rep[Double]
  def cos(x: Rep[Double]): Rep[Double]

}

trait TrigExp extends Trig with BaseExp {

  case class Sin(x: Exp[Double]) extends Def[Double]
  case class Cos(x: Exp[Double]) extends Def[Double]

  def sin(x: Exp[Double]) = Sin(x)
  def cos(x: Exp[Double]) = Cos(x)
}

trait TrigExpOpt extends TrigExp {

  override def sin(x: Exp[Double]) = x match {
    case Const(x) => unit(math.sin(x))
    case _ => super.sin(x)
  }
  
  override def cos(x: Exp[Double]) = x match {
    case Const(x) => unit(math.cos(x))
    case _ => super.cos(x)
  }

}