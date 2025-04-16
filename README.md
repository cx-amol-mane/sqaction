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
├── src
│   └── test
│       └── java
│           └── SonarQubeLoginTest.java
├── com.checkmarx.sonar.cxplugin-2024.3.3.jar
└── README.md
```

## Purpose

The workflow automatically tests the Checkmarx SonarQube plugin against multiple versions of SonarQube to verify compatibility and proper functioning of the plugin.

## Custom Actions

The workflow uses three custom GitHub Actions to modularize and simplify the testing process:

1. **setup-sonarqube**: Installs Podman and starts a SonarQube container with the specified version
2. **install-plugin**: Installs the Checkmarx plugin to the running SonarQube instance and verifies installation
3. **cleanup-sonarqube**: Stops and removes the SonarQube container

## Supported SonarQube Versions

The workflow tests compatibility with the following SonarQube versions:

- 8.9 - 10.7 (Community Edition)

## Requirements

- The Checkmarx SonarQube plugin JAR file (`com.checkmarx.sonar.cxplugin-{version}.jar`) must be present in the repository root
- GitHub Actions runner with Ubuntu environment

## How to Use This Repository

This repository can be used to test any SonarQube plugin against multiple SonarQube versions. Here's how to set it up for your own plugin:

### Fork and Clone
1. Fork this repository to your GitHub account
2. Clone your forked repository:
   ```bash
   git clone https://github.com/yourusername/sonarqube-plugin-compatibility-test.git
   cd sonarqube-plugin-compatibility-test
   ```

### Test Your Custom Plugin
1. Create a new branch for your plugin:
   ```bash
   git checkout -b my-plugin-test
   ```

2. Remove the existing Checkmarx plugin:
   ```bash
   rm com.checkmarx.sonar.cxplugin-2024.3.3.jar
   ```

3. Add your SonarQube plugin JAR file to the repository root:
   ```bash
   cp /path/to/your-plugin.jar ./
   ```

4. Update the workflow file to use your plugin:
   ```bash
   # Edit .github/workflows/sonarqube-compatibility.yml
   # Change the plugin-path parameter in the Install and verify plugin step
   ```

5. If using custom actions, update the install-plugin action:
   ```bash
   # Edit .github/actions/install-plugin/action.yml
   # Update any plugin-specific verification steps (like grep patterns)
   ```

6. Push your branch to GitHub:
   ```bash
   git add .
   git commit -m "Test compatibility for my-plugin"
   git push origin my-plugin-test
   ```

7. Update the workflow trigger in the main branch:
   ```yaml
   on:
     push:
       branches: [main, my-plugin-test]  # Add your branch name here
   ```

8. The workflow will automatically run on your branch, testing your plugin against all configured SonarQube versions.

### Review Results
1. Go to the Actions tab in your GitHub repository
2. Find the workflow run for your branch
3. Check the matrix jobs to see which SonarQube versions are compatible with your plugin
4. Review any failure logs for troubleshooting information

### Customize Tests
1. Add or modify test classes in the `src/test/java` directory to test specific functionality of your plugin
2. Update the pom.xml file with any additional dependencies your tests may require
3. Uncomment and customize the Maven test step in the workflow file

## Troubleshooting

If any compatibility issues are found, the workflow will fail for the specific SonarQube version. The logs will include detailed information about the failure, including:

- SonarQube startup logs
- Plugin installation verification
- Specific plugin loading errors (if any)

## Test Cases

The repository includes Selenium-based test cases to verify the functionality of SonarQube with the installed plugin.

## Test Case Details

### Test Case 1: Successful Login

1. Open the SonarQube login page
2. Enter valid credentials
3. Click the login button
4. Verify successful login

### Test Case 2: Invalid Credentials

1. Open the SonarQube login page
2. Enter invalid credentials
3. Click the login button
4. Verify login failure

## Future Enhancements

Implement additional test cases to cover all plugin functionality
Add performance benchmarks for the plugin across different SonarQube versions