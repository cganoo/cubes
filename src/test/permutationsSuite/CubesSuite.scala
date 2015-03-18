package permutationsSuite

import org.junit.Test
import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner

/**
 * Created by cganoo on 17/03/15.
 */
@RunWith(classOf[JUnitRunner])
class CubesSuite extends FunSuite {
  import permutations.Cubes._
  import Ordering.Implicits._

  trait TestData {
    val first5Integers = List(1, 2, 3, 4, 5)
    val first5Cubes = List(1L, 8L, 27L, 64L, 125L)
    val cube3 = 41063625L
    val cube5 = 127035954683L

  }

  test("Verify calculations of cubes for a few small positive integers") {
    new TestData {
      assert(first5Cubes == cube(first5Integers))
    }
  }

  test("Verify that 41063625 is the smallest cube for which exactly three permutations of its digits are cubes") {
    new TestData {
      assert(cube3 == findSmallestPermutedCube3())
    }
  }

  test("Verify that 127035954683 is the smallest cube for which exactly five permutations of its digits are cubes") {
    new TestData {
      assert(cube5 == findSmallestPermutedCube5())
    }
  }
}
