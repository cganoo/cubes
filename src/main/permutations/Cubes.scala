package permutations

/**
 * Created by cganoo on 17/03/15.
 */

/**
 * Companion object for the Cubes class
 */
object Cubes {

  /* Publicly available APIs */

  /**
   * Computes the smallest cube for which exactly three permutations of its digits are cubes
   * Note: Uses the {@link codePoint} strategy
   * @return a Long number
   */
  def findSmallestPermutedCube3(): Long = {
    findSmallestPermutedCube(3, 1000, codePoint)
  }

  /**
   * Computes the smallest cube for which exactly three permutations of its digits are cubes
   * Note: Uses the {@link codePoint} strategy
   * @return a Long number
   */
  def findSmallestPermutedCube5(): Long = {
    findSmallestPermutedCube(5, 10000, codePoint)
  }

  /**
   * Computes via the specified strategy,
   * the smallest cube for which exactly n permutations of its digits are cubes
   * @param n integer specifying how many permutations of digits should be cubes
   * @param limit integer specifying for how many numbers, cubes should be computed
   * @return a Long number
   */
  def findSmallestPermutedCube(n: Int, limit: Int, strategy: Long => String): Long = {

    /* Using a mutable map datastructure here */
    val cache = scala.collection.mutable.Map[String, List[Long]]()

    /* For the specified limit, fill the map by computing interesting cubes via specified strategy */
    for {i <- 1 to limit; cp = strategy(cube(i))} {
      val valueOpt = cache get (cp)
      val value: List[Long] = valueOpt match {
        case Some(x) => x :+ cube(i)
        case None => List(cube(i))
      }
      cache((cp)) = value
    }
    /*
     * From our populated map, filter out values (which are List of cubes),
     * whose size matches n and return the smallest of them
     */
    cache.filter(_._2.size == n).values.flatten.min
  }

   /**
   * Computes the cube of the specified Int input
   * @param n an Int
   * @return cube of n
   */
   def cube(n: Int): Long = cube(n.toLong)

  /**
   * Computes the cube of each element in the specified list
   * @param n List of Long numbers
   * @return List of cubes computed per element in n
   */
  def cube(n: List[Int]): List[Long] = n map (x => cube(x))

  /* Available Strategies */

  /**
   * Computes the string representation of specified input by counting occurrences of digits 0-9
   * For example:
   * codePoint(1L)    = 0100000000
   * codePoint(1000L) = 3100000000
   * codePoint(1252L) = 0120010000
   * codePoint(1225L) = 0120010000
   *
   * @param n a Long number
   * @return a string representing codePoint of n
   */
  def codePoint(n: Long): String = codePointList(n).mkString("")

  /**
   * Sorts the digits in the specified input and returns a string
   * For example:
   * fingerprint(100L)  = 100
   * fingerprint(1225L) = 1225
   * fingerprint(1252L) = 1225
   *
   * @param n a Long number
   * @return a string representing the fingerprint of n
   */
  def fingerPrint(n: Long): String = n.toString.map(_.asDigit).sorted.mkString("")

  /* Private helper methods */

  /**
   * Computes the sorted-by-values version of complete frequency map of the specified input
   * @param n a Long number
   * @return List of integer values
   */
  private def codePointList(n: Long): List[Int] = completeFrequencyMap(n).toList.sorted.map(_._2)

  /**
   * Computes the complete frequency count of individual digits in the specified input
   * For digits not present in the input, keys are created with value as 0
   * @param n a Long number
   * @return Map of Char to Int
   */
  private def completeFrequencyMap(n: Long): Map[Char, Int] = frequencyMap(n) ++ (for ((k, v) <- baseMap()) yield k -> (v + frequencyMap(n).getOrElse(k, 0)))

  /**
   * Computes the frequency count of individual digits in the specified input
   * @param n a Long number
   * @return Map of Char to Int
   */
  private def frequencyMap(n: Long): Map[Char, Int] = n.toString.groupBy(_.toChar).mapValues(_.size)

  /**
   * Assigns as a mapping the integer count of 0 for chars '0' to '9'
   * @return Map of Char to Int
   */
  private def baseMap(): Map[Char, Int] = (for {
    i <- '0' to '9'
    j = 0
  } yield (i -> j)) toMap

  /**
   * Computes the cube of the specified Long input
   * @param n a Long
   * @return cube of n
   */
  private def cube(n: Long): Long = n * n * n

  def main(args : Array[String]): Unit = {
    println(codePoint(1L))
  }
}
