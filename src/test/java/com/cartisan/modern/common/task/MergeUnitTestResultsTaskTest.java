package com.cartisan.modern.common.task;

import org.gradle.testkit.runner.BuildResult;
import org.gradle.testkit.runner.GradleRunner;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

import static java.io.File.separator;
import static java.lang.String.format;
import static javax.xml.parsers.DocumentBuilderFactory.newInstance;
import static org.assertj.core.api.Assertions.assertThat;
import static org.apache.commons.io.FileUtils.copyFileToDirectory;
import static org.apache.commons.io.FileUtils.readFileToString;
import static org.apache.commons.io.FileUtils.writeStringToFile;
import static org.gradle.testkit.runner.TaskOutcome.SUCCESS;
import static org.junit.Assert.assertEquals;

public class MergeUnitTestResultsTaskTest {
    @Rule
    public final TemporaryFolder tmpFolder = new TemporaryFolder();
    private final String taskName = "mergeUnitTestResults";
    private final String testResultFileNamePattern = "TEST-com.cartisan.modern.transaction.controller.%s.xml";
    private BuildResult result;
    private String sourceTestResultsFolder;
    private final String defaultXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
            "<testsuite name=\"com.cartisan.modern.RequireMergeTest$SubClass\" tests=\"1\" skipped=\"0\" failures=\"0\" errors=\"0\" timestamp=\"2016-11-06T10:32:48\" hostname=\"zhangjinhuadeMacBook-Pro\" time=\"0.41\">\n" +
            "   <properties/>\n" +
            "   <testcase name=\"should_not_add_account\" classname=\"com.cartisan.modern.account.controller.AccountAddControllerTest\" time=\"0.041\"/>\n" +
            "   <system-out><![CDATA[]]></system-out>\n" +
            "   <system-err><![CDATA[]]></system-err>\n" +
            "</testsuite>";
    private String defaultClassName = "com.cartisan.modern.RequireMergeTest$SubClass";

    @Before
    public void createTask() throws IOException {
        copyFileToRootFolder("build.gradle");
        copyFileToGradleFolder("sonar.gradle", "ci.gradle");
    }

    @Before
    public void initSourceTestResultFolder() {
        sourceTestResultsFolder = tmpFolder.getRoot() + separator + "build" + separator + "test-results";
    }

    @Test
    public void should_not_change_test_result_file_not_matching() throws IOException {
        createTestResultFile("RequireNoChangeTest", defaultClassName);
        runTask();
        assertTaskRunSuccess();
        assertTestResultFileContentUnchanged(defaultXml, "RequireNoChangeTest");
    }

    private void assertTestResultFileContentUnchanged(String defaultXml, String shortName) throws IOException {
        assertThat(readFileToString(testResultFile(shortName))).isEqualTo(defaultXml);
    }

    @Test
    public void should_create_a_new_test_result_file_with_same_content_of_nested_runner_result() throws IOException, ParserConfigurationException, SAXException {
        createTestResultFile("RequireMergeTest$SubClass", "com.cartisan.modern.RequireMergeTest$SubClass");

        runTask();

        assertTaskRunSuccess();
        assertXmlFileAttributeChanged("com.cartisan.modern.RequireMergeTest", "name", "RequireMergeTest$SubClass");
    }

    private void assertXmlFileAttributeChanged(String expected, String attributeName, String shortName) throws ParserConfigurationException, IOException, SAXException {
        assertThat(document(shortName).getDocumentElement().getAttribute(attributeName)).isEqualTo(expected);
    }

    private Document document(String shortName) throws ParserConfigurationException, IOException, SAXException {
        return newInstance().newDocumentBuilder().parse(testResultFile(shortName));
    }

    private File testResultFile(String shortName) {
        return new File(sourceTestResultsFolder + separator + format(testResultFileNamePattern, shortName));
    }

    private void createTestResultFile(String shortName, String className) throws IOException {
        writeStringToFile(testResultFile(shortName), defaultXml.replace(defaultClassName, className));
    }

    private void assertTaskRunSuccess() {
        assertEquals(result.task(":" + taskName).getOutcome(), SUCCESS);
    }

    private void runTask() {
        result = GradleRunner.create()
                .withProjectDir(tmpFolder.getRoot())
                .withArguments(taskName)
                .build();
    }

    private void copyFileToRootFolder(String fileName) throws IOException {
        copyFileToDirectory(new File(System.getProperty("user.dir") + separator + fileName), tmpFolder.getRoot());
    }

    private void copyFileToGradleFolder(String... fileNames) throws IOException {
        File gradleFolder = tmpFolder.newFolder("gradle");
        for (String fileName : fileNames) {
            copyFileToDirectory(new File(System.getProperty("user.dir") + separator + "gradle" + separator + fileName), gradleFolder);
        }
    }
}
