import com.github.polomarcus.utils.ClimateService
import com.github.polomarcus.model.CO2Record
import org.scalatest.funsuite.AnyFunSuite

//@See https://www.scalatest.org/scaladoc/3.1.2/org/scalatest/funsuite/AnyFunSuite.html
class ClimateServiceTest extends AnyFunSuite {
  test("containsWordGlobalWarming - non climate related words should return false") {
    assert( ClimateService.isClimateRelated("pizza") == false)
  }

  test("isClimateRelated - climate related words should return true") {
    assert(ClimateService.isClimateRelated("climate change") == true)
    assert(ClimateService.isClimateRelated("IPCC") == true)
    assert(ClimateService.isClimateRelated("global warming") == true)
  }




  //@TODO
  test("parseRawData") {
    // our inputs
    val firstRecord = (2003, 1, 355.2)     //help: to acces 2003 of this tuple, you can do firstRecord._1
    val secondRecord = (2004, 1, 375.2)
    val list1 = List(firstRecord, secondRecord)

    // our output of our method "parseRawData"
    val co2RecordWithType = CO2Record(firstRecord._1, firstRecord._2, firstRecord._3)
    val co2RecordWithType2 = CO2Record(secondRecord._1, secondRecord._2, secondRecord._3)
    val output = List(Some(co2RecordWithType), Some(co2RecordWithType2))

    // we call our function here to test our input and output
    assert(ClimateService.parseRawData(list1) == output)
  }

  //@TODO
  test("filterDecemberData") {
    assert(true == false)
  }

  test("getMinMax") {
    val records = List(
      CO2Record(2003, 1, 355.2),
      CO2Record(2003, 2, 365.2),
      CO2Record(2004, 1, 375.2),
      CO2Record(2004, 2, 385.2)
    )

    val (min, max) = ClimateService.getMinMax(records)
    assert(min == 355.2)
    assert(max == 385.2)
  }

  test("getMinMaxByYear") {
    val records = List(
      CO2Record(2003, 1, 355.2),
      CO2Record(2003, 2, 365.2),
      CO2Record(2004, 1, 375.2),
      CO2Record(2004, 2, 385.2)
    )

    val (min2003, max2003) = ClimateService.getMinMaxByYear(records, 2003)
    assert(min2003 == 355.2)
    assert(max2003 == 365.2)

    val (min2004, max2004) = ClimateService.getMinMaxByYear(records, 2004)
    assert(min2004 == 375.2)
    assert(max2004 == 385.2)

    val (minEmpty, maxEmpty) = ClimateService.getMinMaxByYear(records, 2005)
    assert(minEmpty.isNaN)
    assert(maxEmpty.isNaN)
  }
  test("getDifferenceMinMax") {
    val values = List(10.5, 20.3, 15.7, 18.2, 25.6)
    val result = ClimateService.getDifferenceMinMax(values)
    assert(result.exists(diff => math.abs(diff - 15.1) < 0.0001))
  }
}
