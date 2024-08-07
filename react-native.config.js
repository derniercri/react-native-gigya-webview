const project = (() => {
  const fs = require('fs');
  const path = require('path');
  try {
    const {
      androidManifestPath,
      windowsProjectPath,
    } = require('react-native-test-app');
    return {
      android: {
        sourceDir: path.join('example', 'android'),
        manifestPath: androidManifestPath(
          path.join(__dirname, 'example', 'android'),
        ),
      },
      ios: {
        sourceDir: 'example/ios',
      },
      windows: fs.existsSync('example/windows/WebviewExample.sln') && {
        sourceDir: path.join('example', 'windows'),
        solutionFile: 'WebviewExample.sln',
        project: windowsProjectPath(path.join(__dirname, 'example', 'windows')),
      },
    };
  } catch (_) {
    return undefined;
  }
})();

module.exports = {
  dependencies: {
    // Help rn-cli find and autolink this library
    'react-native-gigya-webview': {
      root: __dirname,
    },
  },
  dependency: {
    platforms: {
      windows: {
        sourceDir: 'windows',
        solutionFile: 'ReactNativeWebView.sln',
        projects: [
          {
            projectFile: 'ReactNativeWebView/ReactNativeWebView.vcxproj',
            directDependency: true,
          },
        ],
      },
    },
  },
  ...(project ? { project } : undefined),
};
