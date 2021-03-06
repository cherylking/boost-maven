/*******************************************************************************
 * Copyright (c) 2018 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package io.openliberty.boost.liberty;

import static org.twdata.maven.mojoexecutor.MojoExecutor.configuration;
import static org.twdata.maven.mojoexecutor.MojoExecutor.element;
import static org.twdata.maven.mojoexecutor.MojoExecutor.executeMojo;
import static org.twdata.maven.mojoexecutor.MojoExecutor.goal;
import static org.twdata.maven.mojoexecutor.MojoExecutor.name;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

/**
 * Runs the executable archive application (in the console foreground) after a
 * debugger connects to debug port <b>7777</b>.
 *
 */
@Mojo(name = "debug")
public class LibertyDebugMojo extends AbstractLibertyMojo {

    /**
     * Clean all cached information on server start up.
     */
    @Parameter(property = "clean", defaultValue = "false")
    protected boolean clean;

    @Override
    public void execute() throws MojoExecutionException {
        super.execute();

        executeMojo(getPlugin(), goal("debug"),
                configuration(element(name("serverName"), libertyServerName),
                        element(name("clean"), String.valueOf(clean)), getRuntimeArtifactElement()),
                getExecutionEnvironment());
    }

}
