package miapp

import domain.Person

class HelloController {

    def index(){
        render("prueba")
    }

    def create(Person person) {
        def p = new Person(name: "Fred", age: 40, lastVisit: new Date())
        person.save()
        render "Name: ${person?.name}, Age: ${person?.age}"
    }

    def createEasy() {
        def p = new Person(name: "Fred", age: 40, lastVisit: new Date())
        p.save()
        //person.save()
        //render "Name: ${person?.name}, Age: ${person?.age}"
        render ("Create")
    }

    def readperson(){
        //def map = [person: Person.get(1)]
        def id = params.list('id')
        def p = Person.get(id)
        render(contentType: "application/json") {
                   p
        }
        //render p as JSON
    }

    def update(){
        render("update")

        def p = Person.get(1)
        p.name = "Bob"
        p.save()
    }

    def deletePerson(){
        render("delete")

        def p = Person.get(1)
        p.delete()
    }

}
