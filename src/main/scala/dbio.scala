package scalaz

import slick.dbio._
import scala.concurrent.{ ExecutionContext, Future }
import scala.util.{ Success => S, Failure => F }

trait DBIOInstances {
  implicit def dbioInstance(implicit ec: ExecutionContext): MonadError[λ[(α, β) => DBIO[β]], Throwable] =
    new DBIOInstance
}

private class DBIOInstance(implicit ec: ExecutionContext) extends MonadError[λ[(α, β) => DBIO[β]], Throwable] with Catchable[DBIO] {

  def point[A](a: => A): DBIO[A] = DBIO.successful(a)

  def bind[A, B](fa: DBIO[A])(f: A => DBIO[B]): DBIO[B] = fa flatMap f

  override def map[A, B](fa: DBIO[A])(f: A => B): DBIO[B] = fa map f

  def attempt[A](f: DBIO[A]): DBIO[Throwable \/ A] =
    f.asTry.map {
      case F(e) => -\/(e)
      case S(a) => \/-(a)
    }

  def fail[A](e: Throwable): DBIO[A] = DBIO.failed(e)

  def raiseError[A](e: Throwable): DBIO[A] = fail(e)

  def handleError[A](fa: DBIO[A])(f: Throwable => DBIO[A]): DBIO[A] =
    fa.asTry.flatMap {
      case F(e) => f(e)
      case S(a) => point(a)
    }

}
