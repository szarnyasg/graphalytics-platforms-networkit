/*
 * Copyright 2015 Delft University of Technology
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package science.atlarge.graphalytics.networkit.algorithms.pr;

import science.atlarge.graphalytics.domain.graph.Graph;
import science.atlarge.graphalytics.networkit.NetworkitConfiguration;
import science.atlarge.graphalytics.networkit.NetworkitJob;
import science.atlarge.graphalytics.domain.algorithms.AlgorithmParameters;
import science.atlarge.graphalytics.domain.algorithms.PageRankParameters;
import science.atlarge.graphalytics.execution.RunSpecification;

/**
 * Page Rank job implementation for NetworKit. This class is responsible for formatting PR-specific
 * arguments to be passed to the platform executable, and does not include the implementation of the algorithm.
 *
 * @author Balint Hegyi
 */
public final class PageRankJob extends NetworkitJob {

	private final long iteration;
	private final float dampingFactor;

	/**
	 * Creates a new BreadthFirstSearchJob object with all mandatory parameters specified.
	 *
	 * @param platformConfig the platform configuration.
	 * @param inputPath the path to the loaded graph.
	 */
	public PageRankJob(RunSpecification runSpecification, NetworkitConfiguration platformConfig,
					   String inputPath, String outputPath, Graph benchmarkGraph) {
		super(runSpecification, platformConfig, inputPath, outputPath, benchmarkGraph);

		AlgorithmParameters parameters = runSpecification.getBenchmarkRun().getAlgorithmParameters();
		this.iteration = ((PageRankParameters)parameters).getNumberOfIterations();
		this.dampingFactor = ((PageRankParameters)parameters).getDampingFactor();
	}

	@Override
	protected void appendAlgorithmParameters() {

		commandLine.addArgument("--algorithm");
		commandLine.addArgument("pr");

		commandLine.addArgument("--damping-factor", false);
		commandLine.addArgument(String.valueOf(dampingFactor), false);

		commandLine.addArgument("--max-iteration", false);
		commandLine.addArgument(String.valueOf(iteration), false);

	}
}
