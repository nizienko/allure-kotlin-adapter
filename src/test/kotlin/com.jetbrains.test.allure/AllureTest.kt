package com.jetbrains.test.allure

import io.qameta.allure.model.*
import org.junit.jupiter.api.Test

class AllureTest {

    @Test
    fun allureBuilder() {
        val startTime = System.currentTimeMillis()

        allure("allure-results") {
            suite("Suite 1") {
                test("Test1") {
                    step("Step1") {
                        start = startTime
                        stop = startTime + 20000
                        status = Status.PASSED
                    }
                    step("Step2") {
                        start = startTime + 20000
                        stop = startTime + 30000
                        status = Status.FAILED
                        descriptionHtml = "<b>fuck</b>"
                    }
                    status = Status.PASSED
                }
                test("Test2") {
                    step("Step1") {
                        start = startTime + 50000
                        stop = startTime + 60000
                        status = Status.PASSED
                    }
                    step("Step2") {
                        start = startTime + 70000
                        stop = startTime + 90000
                        status = Status.FAILED
                        descriptionHtml = "<b>fuck</b>"
                    }
                    status = Status.PASSED
                }
            }
            suite("Suite 2") {
                test("Test1") {
                    start = startTime
                    step("Step1") {
                        start = startTime
                        stop = startTime + 20000
                        status = Status.PASSED
                    }
                    step("Step2") {
                        start = startTime + 20000
                        stop = startTime + 30000
                        status = Status.FAILED
                        descriptionHtml = "<b>fuck</b>"
                        step("Sub-step 1") {
                            start = startTime + 20000
                            stop = startTime + 30000
                            status = Status.FAILED
                            withStatusDetails(StatusDetails().withMessage("Fuck").withTrace("...fuck fuck fuck"))
                        }
                    }
                    stop = startTime + 30000
                    status = Status.FAILED
                    withStatusDetails(StatusDetails().withMessage("Fuck").withTrace("...fuck fuck fuck"))
                }
                test("Test2") {
                    start = startTime + 50000
                    step("Step1") {
                        start = startTime + 50000
                        stop = startTime + 60000
                        status = Status.PASSED
                    }
                    step("Step2") {
                        start = startTime + 70000
                        stop = startTime + 90000
                        status = Status.FAILED
                        descriptionHtml = "<b>fuck</b>"
                    }
                    stop = startTime + 90000
                    status = Status.FAILED
                    links.add(Link().withUrl("https://jetbrains.com").withName("jetbrains"))
                    withFullName("Super test 2")
                }
            }
        }
    }
}