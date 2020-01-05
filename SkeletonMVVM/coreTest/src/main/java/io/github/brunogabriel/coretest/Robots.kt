package io.github.brunogabriel.coretest

import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

/**
 * Created by brunogabriel on 2020-01-04
 */
interface Robots {
    fun setup()
}

class RobotsRule<T : Robots>(private val robot: T) : TestRule {
    override fun apply(base: Statement, description: Description?): Statement {
        return object : Statement() {
            override fun evaluate() {
                base.evaluate()
            }
        }
    }

    fun setup() = robot.setup()
    operator fun invoke(func: T.() -> Unit) = createRobots(func, robot)
}

fun <T> createRobots(func: T.() -> Unit, robot: T) = robot.apply { func() }
