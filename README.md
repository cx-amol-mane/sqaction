# SonarQube Plugin Compatibility Test

This repository contains a GitHub Actions workflow for testing a Checkmarx SonarQube plugin across multiple SonarQube versions to ensure compatibility.

## Project Structure

```
.
├── .github
│   └── actions
│       ├── setup-sonarqube
│       │   └── action.yml
│       ├── install-plugin
│       │   └── action.yml
│       └── cleanup-sonarqube
│           └── action.yml
├── .github
│   └── workflows
│       └── sonarqube-compatibility.yml
├── com.checkmarx.sonar.cxplugin-2024.3.3.jar
└── README.md
```

## Purpose

The workflow automatically tests the Checkmarx SonarQube plugin (version 2024.3.3) against multiple versions of SonarQube to verify compatibility and proper functioning of the plugin.

## Custom Actions

The workflow uses three custom GitHub Actions to modularize and simplify the testing process:

1. **setup-sonarqube**: Installs Podman and starts a SonarQube container with the specified version
2. **install-plugin**: Installs the Checkmarx plugin to the running SonarQube instance and verifies installation
3. **cleanup-sonarqube**: Stops and removes the SonarQube container

## Supported SonarQube Versions

The workflow tests compatibility with the following SonarQube versions:

- 8.9 (Community Edition)
- 9.6 (Community Edition)
- 9.7 (Community Edition)
- 9.8 (Community Edition)
- 10.0.0 (Community Edition)
- 10.1.0 (Community Edition)
- 10.2.0 (Community Edition)
- 10.3.0 (Community Edition)
- 10.4.0 (Community Edition)
- 10.5.0 (Community Edition)
- 10.6.0 (Community Edition)
- 10.7.0 (Community Edition)

## Requirements

- The Checkmarx SonarQube plugin JAR file (`com.checkmarx.sonar.cxplugin-{version}.jar`) must be present in the repository root
- GitHub Actions runner with Ubuntu environment

## Usage

The workflow is configured to run automatically when changes are pushed to the `main` branch. You can also manually trigger the workflow from the GitHub Actions tab.

## Troubleshooting

If any compatibility issues are found, the workflow will fail for the specific SonarQube version. The logs will include detailed information about the failure, including:

- SonarQube startup logs
- Plugin installation verification
- Specific plugin loading errors (if any)

## Future Enhancements

The workflow includes a commented-out Maven test step that could be uncommented to run additional tests against the plugin.

