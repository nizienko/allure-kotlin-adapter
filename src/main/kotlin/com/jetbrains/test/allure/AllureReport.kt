package com.jetbrains.test.allure

import io.qameta.allure.FileSystemResultsWriter
import io.qameta.allure.model.Label
import io.qameta.allure.model.StepResult
import io.qameta.allure.model.TestResult
import java.nio.file.Paths

class AllureReport(path: String) {
    private val testResults = mutableListOf<TestResult>()
    private val writer = FileSystemResultsWriter(Paths.get(path))

    fun suite(suiteName: String, suite: Suite.() -> Unit) {
        val suiteResult = Suite(this, suiteName)
        suiteResult.suite()
    }

    fun addTestResult(testResult: TestResult) {
        testResults.add(testResult)
    }

    fun write() {
        testResults.forEach { writer.write(it) }
    }
}

class Suite(private val allure: AllureReport, private val suiteName: String) {
    fun test(teatName: String, test: TestResult.() -> Unit) {
        val testResult = TestResult()
                .withLabels(Label().withName("suite").withValue(suiteName))
                .withLabels(Label().withName("package").withValue(suiteName))
                .withName(teatName)
        testResult.test()
        allure.addTestResult(testResult)
    }
}

fun TestResult.step(stepName: String, step: StepResult.() -> Unit) {
    val stepResult = StepResult()
            .withName(stepName)
    stepResult.step()
    this.withSteps(stepResult)
}
fun StepResult.step(stepName: String, step: StepResult.() -> Unit) {
    val stepResult = StepResult()
            .withName(stepName)
    stepResult.step()
    this.withSteps(stepResult)
}

fun allure(path: String, report: AllureReport.() -> Unit) {
    val allure = AllureReport(path)
    allure.report()
    allure.write()
}