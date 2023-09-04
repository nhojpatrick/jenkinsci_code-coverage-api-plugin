package io.jenkins.plugins.coverage.metrics.steps;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import io.jenkins.plugins.coverage.metrics.AbstractModifiedFilesCoverageTest;
import io.jenkins.plugins.coverage.metrics.model.FileWithModifiedLines;
import io.jenkins.plugins.coverage.metrics.model.LineCoverageType;
import io.jenkins.plugins.coverage.metrics.model.ModifiedLinesBlock;

import static org.assertj.core.api.Assertions.*;

/**
 * Tests the {@link CoverageApiUtil} class.
 */
class CoverageApiUtilTest extends AbstractModifiedFilesCoverageTest {
    /**
     * Test to assert that all modified lines and their respective coverage types are correctly extracted from the
     * coverage tree created by the {@link #createCoverageTree()} method.
     */
    @Test
    void verifyCoverageForAllModifiedLines() {
        var node = createCoverageTree();
        var filesWithChangedLines = CoverageApiUtil.getFilesWithModifiedLines(node);

        var fileOneLinesList = createListOfModifiedLines(LineCoverageType.COVERED, 15, 16, 21, 22);
        fileOneLinesList.addAll(createListOfModifiedLines(LineCoverageType.MISSED, 35, 36));
        fileOneLinesList.addAll(createListOfModifiedLines(LineCoverageType.PARTIALLY_COVERED, 20, 20));

        var fileOne = new FileWithModifiedLines("test/example/Test1.java", fileOneLinesList);

        assertThat(filesWithChangedLines).contains(fileOne);
    }


}
