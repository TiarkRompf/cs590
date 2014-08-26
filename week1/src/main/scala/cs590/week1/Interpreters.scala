package cs590.week1

/**
 * Problem 2: Definitional Interpreters
 */


/**
 * Interpreter I from Reynolds' paper
 */

trait Interpreter1 {

  type Val = Any // Int, Boolean, Val => Val
  type Fun = Val => Val

  type Ident = String

  type Env = Ident => Val

  abstract class Exp
  case class Const(x: Val) extends Exp
  case class Var(s: Ident) extends Exp
  case class App(opr: Exp, opnd: Exp) extends Exp
  case class Lam(fp: Ident, body: Exp) extends Exp

  def eval(e: Exp, env: Env): Val = e match {
    case Const(x) => x
    case Var(x)   => env(x)
    case App(f,x) => eval(f,env).asInstanceOf[Fun](eval(x,env))
    case Lam(x,e) => evlambda(x,e,env)
  }

  def evlambda(x: Ident, e: Exp, env: Env) = (a: Val) => eval(e,ext(x,a,env))
  def ext(z: Ident, a: Val, env: Env): Env = ???

  /**
   * TODO: what is missing? add it in!
   */

}


/**
 * Interpreter II from Reynolds' paper
 */

trait Interpreter2 {

  type Ident = String

  type Val = Any // Int, Boolean, Val => Val

  abstract class Fun
  case class Clos(lam: Lam, env: Env) extends Fun
  case class SC() extends Fun
  case class EQ1() extends Fun
  case class EQ2(arg1: Val) extends Fun

  abstract class Env
  case class Init() extends Env
  case class Simp(bvar: Ident, bval: Val, old: Env) extends Env

  abstract class Exp
  case class Const(x: Val) extends Exp
  case class Var(s: Ident) extends Exp
  case class App(opr: Exp, opnd: Exp) extends Exp
  case class Lam(fp: Ident, body: Exp) extends Exp

  /**
   * TODO: implement second interpreter from paper
   */

  def interpret(r: Exp) = eval(r, Init())

  def eval(r: Exp, env: Env): Val = ???

  def apply(f: Fun, a: Val): Val = ???

  def get(env: Env, x: Ident): Val = ???


}


