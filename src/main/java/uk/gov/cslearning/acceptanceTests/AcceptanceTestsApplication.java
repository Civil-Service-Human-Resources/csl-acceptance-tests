package uk.gov.cslearning.acceptanceTests;

import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;
import org.junit.platform.launcher.listeners.TestExecutionSummary;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.platform.engine.discovery.DiscoverySelectors.selectPackage;

@SpringBootApplication
@SpringBootTest
public class AcceptanceTestsApplication {

	private static final String testPackage = "uk.gov.cslearning.acceptanceTests.test";

	public static void main(String[] args) {

		final LauncherDiscoveryRequest request = LauncherDiscoveryRequestBuilder.request()
				.selectors(
						selectPackage(testPackage)
				)
				.build();

		final Launcher launcher = LauncherFactory.create();

		final boolean pathContainsTests = launcher.discover(request).containsTests();
		if (!pathContainsTests) {
			System.out.println("This path is invalid or folder doesn't consist tests");
		}

		final SummaryGeneratingListener listener = new SummaryGeneratingListener();

		launcher.execute(request, listener);

		final TestExecutionSummary summary = listener.getSummary();

		final long containersFoundCount = summary.getContainersFoundCount();
		System.out.println("containers Found Count  " + containersFoundCount);

		final long containersSkippedCount = summary.getContainersSkippedCount();
		System.out.println("containers Skipped Count  " + containersSkippedCount);

		final long testsFoundCount = summary.getTestsFoundCount();
		System.out.println("tests Found Count  " + testsFoundCount);

		final long testsSkippedCount = summary.getTestsSkippedCount();
		System.out.println("tests Skipped Count  " + testsSkippedCount);
	}

}
