package cs590.week1

import org.scalatest.FunSuite


class FunSetTest extends FunSuite with FunSets {

  /**
   * Tests are written using the "test" operator and the "assert" method.
   */

  test("string take") {
    val message = "hello, world"
    assert(message.take(5) == "hello")
  }


  /**
   * For ScalaTest tests, there exists a special equality operator "===" that
   * can be used inside "assert". If the assertion fails, the two values will
   * be printed in the error message. Otherwise, when using "==", the test
   * error message will only say "assertion failed", without showing the values.
   *
   * Try it out! Change the values so that the assertion fails, and look at the
   * error message.
   */
  test("adding ints") {
    assert(1 + 2 === 3)
  }


  test("set contains") {
    assert(contains((x: Int) => x == 1, 1))
  }

  /**
   * These tests are set to 'ignore' because the implemention of 'set' is
   * missing. Change to 'test' to run them.
   */

  ignore("set(1) contains 1") {
    assert(contains(set(1), 1))
  }

  ignore("set(1) does not contain 2") {
    assert(!contains(set(1), 2))
  }

  ignore("union contains all elements") {
    val s = union(set(1), set(2))
    assert(contains(s, 1), "Union 1")
    assert(contains(s, 2), "Union 2")
    assert(!contains(s, 3), "Union 3")
  }


  /**
   * TODO: add tests for other methods
   */
}


class IntSetTest extends FunSuite with IntSets {

  val s1 = new NonEmpty(2, new Empty, new Empty)
  val s2 = s1.incl(1)
  val s3 = s2.incl(4)
  
  test("s3 contains all elements") {
    assert(s3.contains(2), "Contains 1")
    assert(s3.contains(1), "Contains 2")
    assert(s3.contains(4), "Contains 3")
  }

  /**
   * TODO: add tests
   *
   * for s = {−10, 5, 21, −1, 0, 3},
   * s.filter(x => x > 0)
   * should return {5, 21, 3}
   */
}
