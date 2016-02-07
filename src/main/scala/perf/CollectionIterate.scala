package perf

import org.openjdk.jmh.annotations._

@BenchmarkMode(Array(Mode.Throughput))
@State(Scope.Benchmark)
class CollectionIterate {

  @Param(Array("10", "100", "500", "1000", "5000", "10000", "50000", "100000", "500000", "1000000", "5000000"))
  var size: Int = _

  var list: List[Int] = _
  var vector: Vector[Int] = _

  @Setup
  def setup(): Unit = {
    list = List(1 to size: _*)
    vector = Vector(1 to size: _*)
  }

  @Benchmark
  def mapList(): List[Int] = {
    list.map(_ + 1)
  }

  @Benchmark
  def mapVector(): Vector[Int] = {
    vector.map(_ + 1)
  }

  @Benchmark
  def foldList(): Int = {
    list.foldLeft(0)(_ + _)
  }

  @Benchmark
  def foldVector(): Int = {
    vector.foldLeft(0)(_ + _)
  }
}

/**
  * jmh:run -i 10 -wi 10 -f1 -t1 perf.CollectionIterate
  *
  * Benchmark                      (size)   Mode  Cnt         Score        Error  Units
  * CollectionIterate.foldList         10  thrpt   10  25859481.483 ± 115274.504  ops/s
  * CollectionIterate.foldList        100  thrpt   10   2754798.762 ±  25432.351  ops/s
  * CollectionIterate.foldList        500  thrpt   10    554220.341 ±   9856.839  ops/s
  * CollectionIterate.foldList       1000  thrpt   10    239017.747 ±   7905.454  ops/s
  * CollectionIterate.foldList       5000  thrpt   10     41677.580 ±    664.373  ops/s
  * CollectionIterate.foldList      10000  thrpt   10     21612.170 ±    371.655  ops/s
  * CollectionIterate.foldList      50000  thrpt   10      4531.416 ±    124.348  ops/s
  * CollectionIterate.foldList     100000  thrpt   10      1972.587 ±     44.008  ops/s
  * CollectionIterate.foldList     500000  thrpt   10       382.095 ±     50.475  ops/s
  * CollectionIterate.foldList    1000000  thrpt   10       181.460 ±     56.167  ops/s
  * CollectionIterate.foldList    5000000  thrpt   10        25.532 ±      2.230  ops/s
  * CollectionIterate.foldVector       10  thrpt   10   7986061.982 ± 354523.499  ops/s
  * CollectionIterate.foldVector      100  thrpt   10    894108.599 ±  27327.203  ops/s
  * CollectionIterate.foldVector      500  thrpt   10    178516.357 ±  15234.874  ops/s
  * CollectionIterate.foldVector     1000  thrpt   10     89655.040 ±   5765.991  ops/s
  * CollectionIterate.foldVector     5000  thrpt   10     15269.648 ±   6172.737  ops/s
  * CollectionIterate.foldVector    10000  thrpt   10      9269.631 ±    170.727  ops/s
  * CollectionIterate.foldVector    50000  thrpt   10      2306.018 ±     71.641  ops/s
  * CollectionIterate.foldVector   100000  thrpt   10      1109.590 ±     23.300  ops/s
  * CollectionIterate.foldVector   500000  thrpt   10       262.931 ±     29.398  ops/s
  * CollectionIterate.foldVector  1000000  thrpt   10       134.958 ±     11.592  ops/s
  * CollectionIterate.foldVector  5000000  thrpt   10        21.083 ±      1.119  ops/s
  * CollectionIterate.mapList          10  thrpt   10  16790915.259 ± 837546.888  ops/s
  * CollectionIterate.mapList         100  thrpt   10   2006908.739 ±  19206.532  ops/s
  * CollectionIterate.mapList         500  thrpt   10    255637.484 ±  37896.798  ops/s
  * CollectionIterate.mapList        1000  thrpt   10    135239.698 ±  19934.734  ops/s
  * CollectionIterate.mapList        5000  thrpt   10     25274.248 ±    612.825  ops/s
  * CollectionIterate.mapList       10000  thrpt   10      9461.035 ±   1424.830  ops/s
  * CollectionIterate.mapList       50000  thrpt   10      2532.901 ±    177.573  ops/s
  * CollectionIterate.mapList      100000  thrpt   10      1101.572 ±    137.340  ops/s
  * CollectionIterate.mapList      500000  thrpt   10       190.327 ±     20.974  ops/s
  * CollectionIterate.mapList     1000000  thrpt   10        74.747 ±     21.175  ops/s
  * CollectionIterate.mapList     5000000  thrpt   10         3.835 ±      3.271  ops/s
  * CollectionIterate.mapVector        10  thrpt   10  10847468.521 ± 164813.526  ops/s
  * CollectionIterate.mapVector       100  thrpt   10   1618595.781 ±  55176.852  ops/s
  * CollectionIterate.mapVector       500  thrpt   10    218216.082 ±   5366.114  ops/s
  * CollectionIterate.mapVector      1000  thrpt   10    104068.165 ±   3711.481  ops/s
  * CollectionIterate.mapVector      5000  thrpt   10     20029.835 ±    348.403  ops/s
  * CollectionIterate.mapVector     10000  thrpt   10      9335.670 ±    600.481  ops/s
  * CollectionIterate.mapVector     50000  thrpt   10      1619.350 ±     48.968  ops/s
  * CollectionIterate.mapVector    100000  thrpt   10       777.471 ±     76.785  ops/s
  * CollectionIterate.mapVector    500000  thrpt   10       235.738 ±     17.057  ops/s
  * CollectionIterate.mapVector   1000000  thrpt   10       112.136 ±     18.915  ops/s
  * CollectionIterate.mapVector   5000000  thrpt   10        16.004 ±      2.576  ops/s
  *
  * Conclusion: List is faster for small collections ( < 10000)
  */