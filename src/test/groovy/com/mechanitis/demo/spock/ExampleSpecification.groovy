package com.mechanitis.demo.spock

import spock.lang.Specification
import spock.lang.Subject

class ExampleSpecification extends Specification {

    @Subject
    private Polygon polygon = new Polygon(5)

    def "should be a simple assertion"() {
        expect:
        1 == 1
    }

    def "should demonstrate given-when-then"() {
        given:
        def polygon = new Polygon(4)

        when:
        int sides = polygon.numberOfSides

        then:
        sides == 4
    }

    def "should expect Exceptions"() {
        when:
        new Polygon(0)

        then:
        def exception = thrown(TooFewSidesException)
        exception.numberOfSides == 0
    }

    def "should expect an Exception to be thrown for invalid input: #sides"() {
        when:
        new Polygon(sides)

        then:
        def exception = thrown(TooFewSidesException)
        exception.numberOfSides == sides

        where:
        sides << [-1, 0, 1, 2]
    }

    def "should be able to create a polygon with #sides sides"() {
        expect:
        new Polygon(sides).numberOfSides == sides

        where:
        sides << [3, 4, 5, 8, 14]
    }

    def "should use data tables for calculating max. Max of #a and #b is #max"() {
        expect:
        Math.max(a, b) == max

        where:
        a | b || max
        1 | 3 || 3
        7 | 4 || 7
        0 | 0 || 0
    }

    def "should be able to mock a concrete class"() {
        given:
        Renderer renderer = Mock()
        @Subject
        def polygon = new Polygon(4, renderer)

        when:
        polygon.draw()

        then:
        4 * renderer.drawLine()
    }

    def "should be able to create a stub"() {
        given: "a palette with red as the primary colour"
        Palette palette = Stub()
        palette.getPrimaryColour() >> Colour.Red

        and: "a renderer initialised with the red palette"
        def renderer = new Renderer(palette)

        expect: "the renderer to use the palette's primary colour as foreground"
        renderer.getForegroundColour() == Colour.Red
    }

    def "should use a helper method"() {
        given:
        Renderer renderer = Mock()
        def shapeFactory = new ShapeFactory(renderer)

        when:
        def polygon = shapeFactory.createDefaultPolygon()

        then:
        checkDefaultShape(polygon, renderer)
    }

    private static void checkDefaultShape(Polygon polygon, Renderer renderer) {
        assert polygon.numberOfSides == 4
        assert polygon.renderer == renderer
    }

    def "should demonstrate 'verifyAll'"() {
        given:
        Renderer renderer = Mock()
        def shapeFactory = new ShapeFactory(renderer)

        when:
        def polygon = shapeFactory.createDefaultPolygon()

        then:
        verifyAll(polygon) {
            numberOfSides == 4
            it.renderer == renderer
        }
    }

}
