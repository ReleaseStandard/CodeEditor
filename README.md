# CodeEditor
[![JitPack](https://jitpack.io/v/Rosemoe/CodeEditor.svg)](https://jitpack.io/#Rosemoe/CodeEditor)
[![Telegram](https://img.shields.io/badge/Join-Telegram-blue)](https://t.me/rosemoe_code_editor)
[![QQ](https://img.shields.io/badge/Join-QQ_Group-ff69b4)](https://jq.qq.com/?_wv=1027&k=n68uxQws)   
A cool and optimized code editor on Android platform with good performance and nice features.

***Work In Progress*** This project is still developing slowly. Bugs may be inside.
It is **not** recommended to use this project for production use.   
***Note:***
Any method or field can be changed, moved or even deleted at current period.     
If you find any bug or require any enhancement, please send it to me by issues or other ways.
Download newest sources from [Releases](https://github.com/Rosemoe/CodeEditor/releases) instead of cloning this repository directly.

**Issues and pull requests are welcome.**   
Note: Language issues may not be handled.   
## Features
- [x] Smooth syntax highlight
- [x] Auto completion
- [x] Auto indent
- [x] Code block lines
- [x] Scale text
- [x] Select text
- [x] Scroll, Scrollbars, EdgeEffect, OverScroll
- [x] Undo/redo
- [x] Search and replace
- [x] Shortcuts
- [x] Auto wordwrap
- [x] Show non-printable characters
- [ ] Incremental highlight Analysis
- [ ] Plugin System
## How to use this editor  
* Step 1.Add the JitPack repository to your build file   
Add it in your root build.gradle at the end of repositories:
```Gradle
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' }
  }
}
 ```
* Step 2. Add the dependency to your app (app level build.gradle)
```Gradle
dependencies {
  #implementation 'com.github.Rosemoe.CodeEditor:<moduleName>:<versionName>'
  # temp stuff
  implementation 'com.github.ReleaseStandard.CodeEditor:CodeEditor-editor:latest'
  implementation 'com.github.ReleaseStandard.CodeEditor:CodeEditor-language-java:latest'
  implementation 'com.github.ReleaseStandard.CodeEditor:CodeEditor-language-python:latest'
}
```
To see for more language avaliable please browse the repo.<br/>

### Some more information
Go to [Wiki](https://github.com/Rosemoe/CodeEditor/wiki)
temp stuff : [wiki](https://github.com/ReleaseStandard/CodeEditor/wiki)
### Discuss
* Official QQ Group:[216632648](https://jq.qq.com/?_wv=1027&k=n68uxQws)
* [Official Telegram Group](https://t.me/rosemoe_code_editor)
