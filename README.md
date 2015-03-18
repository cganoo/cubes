cubes
========
[![Build Status](https://travis-ci.org/cganoo/cubes.svg?branch=master)](https://travis-ci.org/cganoo/cubes)

Some experiments with numbers that are cubes

## How to use this?

### Satisfy Dependencies

Following are essential:

* [Git](http://git-scm.com/downloads)
* [Gradle](https://gradle.org/)
* [Scala](http://www.scala-lang.org/download/2.10.4.html)
* [Java SE 7](http://www.oracle.com/technetwork/java/javase/downloads/jdk7-downloads-1880260.html)

It is recommended to also use the following for experimenting with the source code:

* [IntelliJ](https://www.jetbrains.com/idea/)

### Get the source and artifacts from Github

1. `git clone https://github.com/cganoo/cubes.git`
2. `cd cubes/`

### Run Unit Tests
1. `gradle check`
2. `gradle test -i`
3. A nice html page showing unit test results will be available at <b>./build/reports/tests/index.html</b>

### Highlights

* <b>Application description</b>: A stand-alone scala app
* <b>Build and Dependency management</b>: Gradle is used for this
* <b>Algorithms used</b>: See below
* <b>Unit Tests</b>: A few representative Junit4 unit tests run with Spring are included.

### Notes on algorithms used

#### Find smallest cube for which exactly k permutations of its digits are cubes
* Finding cuberoots is more difficult than finding cubes
* So we can memoize and start calculating cubes of all numbers from 1 to some upper limit
* For each such cube that we calculate, the digits that constitute it can be used for identifying permutations
* 2 strategies are provided:
** CodePoint: Compute histogram for digits 0-9 and the resulting number is the codepoint. For example: codePoint(1252) = 0120010000
** FingerPrint: Sort the digits. For example: fingerprint(1225L) = 1225
* Leverage the cache used for memoizing to maintain cubes that yield the same codePoint/fingerPrint
* Scan the cache to find desired length groups of cubes and return smallest amongst them

### License

cubes is licensed under the MIT license. It is primarily intended for fun and instructive purposes.
Use this at your own risk.

## Author

Chaitanya Ganoo
www.linkedin.com/in/cganoo
