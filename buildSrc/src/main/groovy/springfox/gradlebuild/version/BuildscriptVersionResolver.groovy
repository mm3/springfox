/*
 *
 *  Copyright 2015 the original author or authors.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 *
 */

package springfox.gradlebuild.version

import org.gradle.api.Project

class BuildscriptVersionResolver {

  /**
   *
   * @param project
   * @param currentVersion
   * @return the project version, bumps the version if the project is in the process of being released.
   */
  static SoftwareVersion projectVersion(Project project, SoftwareVersion currentVersion) {
    if (project.gradle.startParameter.taskNames.contains('release')) {
      return currentVersion.next(ReleaseType.valueOf(project.property('releaseType').toUpperCase()))
    } else {
      return currentVersion
    }
  }

  static boolean isSnapshot(Project project){
    return project.version.toString().endsWith('-SNAPSHOT')
  }
}
