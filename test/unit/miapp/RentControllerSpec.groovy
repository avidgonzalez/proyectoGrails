package miapp

import domain.Book
import grails.test.mixin.Mock
import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(RentController)
@Mock([RentServicesService])
class RentControllerSpec extends Specification {

    def setup() {
        params.l
    }

    def cleanup() {
    }

    /*def populateValidParams(params) {
        assert params != null
        params["idBook"] = '1'
    }*/

    void "Validacion controllador searchBook"() {
        /*give:
        Book book = new Book(title:"The Stand",ISBN: 123456789)

        when:"The index action is executed"
        def result = controller.searchBook()

        then:"The model is correct"
        result.get*/

    }
}
