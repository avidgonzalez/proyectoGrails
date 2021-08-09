import domain.Book
import domain.Person

class BootStrap {

    def init = { servletContext ->
        def p = new Book(title:"The Stand",ISBN: 123456789).save()
        new Book(title:"The Shining",ISBN:789465132).save()
        new Person(name: "Fred", age: 40, lastVisit: new Date()).save()

        new Person(name: "David", age: 15, lastVisit: new Date()).save()
        new Person(name: "Daniel", age: 12, lastVisit: new Date()).save()

    }
    def destroy = {
    }
}
