package perf

import org.openjdk.jmh.annotations.{Param, Benchmark, Scope, State}

@State(Scope.Benchmark)
class OptionFold {

  @Param(Array("Some", "None"))
  var v: String = _

  def opt: Option[Int] = {
    if (v == "Some")
      Option(1)
    else
      None
  }

  @Benchmark
  def patternMatch(): Int = {
    opt match {
      case Some(a) => a + 1
      case None => 0
    }
  }

  @Benchmark
  def mapGetOrElse(): Int = {
    opt.map(_ + 1).getOrElse(0)
  }

  @Benchmark
  def fold(): Int = {
    opt.fold(0)(_ + 1)
  }
}

/**
  * > jmh:run -i 10 -wi 10 -f1 -t1 perf.OptionFold
  *
  * Benchmark                  (v)   Mode  Cnt          Score         Error  Units
  * OptionFold.fold          Some  thrpt   10  152559799.220 ± 1386628.210  ops/s
  * OptionFold.fold          None  thrpt   10  180814243.316 ± 2285680.696  ops/s
  * OptionFold.mapGetOrElse  Some  thrpt   10  151768469.700 ± 1494107.282  ops/s
  * OptionFold.mapGetOrElse  None  thrpt   10  180040929.432 ± 4340486.052  ops/s
  * OptionFold.patternMatch  Some  thrpt   10  152501886.255 ± 1982556.447  ops/s
  * OptionFold.patternMatch  None  thrpt   10  181239949.041 ± 2531135.043  ops/s
  *
  * Conclusion : equals
  */