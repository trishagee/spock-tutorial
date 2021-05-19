package com.mechanitis.demo.spock;

import spock.lang.Specification;

class BarTest extends Specification {
    def foo() {
        expect: true
    }

    def bar() {
        expect: true
    }
}