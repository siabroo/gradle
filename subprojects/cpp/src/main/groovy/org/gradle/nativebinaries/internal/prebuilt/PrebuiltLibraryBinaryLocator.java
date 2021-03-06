/*
 * Copyright 2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.gradle.nativebinaries.internal.prebuilt;

import org.gradle.api.DomainObjectSet;
import org.gradle.nativebinaries.NativeBinary;
import org.gradle.nativebinaries.NativeLibraryRequirement;
import org.gradle.nativebinaries.PrebuiltLibraries;
import org.gradle.nativebinaries.internal.resolve.LibraryBinaryLocator;

public class PrebuiltLibraryBinaryLocator implements LibraryBinaryLocator {
    private final PrebuiltLibraries prebuiltLibraries;

    public PrebuiltLibraryBinaryLocator(PrebuiltLibraries prebuiltLibraries) {
        this.prebuiltLibraries = prebuiltLibraries;
    }

    public DomainObjectSet<NativeBinary> getBinaries(NativeLibraryRequirement requirement) {
        if (requirement.getProjectPath() != null) {
            throw new PrebuiltLibraryResolveException("Cannot resolve prebuilt library in another project.");
        }
        return prebuiltLibraries.getByName(requirement.getLibraryName()).getBinaries();
    }
}
