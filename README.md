# CodeEditor main repository
<br />

| Piece    |  Status |
|----------|-------|
| application |  ![build](https://github.com/ReleaseStandard/CodeEditor-application/actions/workflows/build.yml/badge.svg) ![main](https://github.com/ReleaseStandard/CodeEditor-application/actions/workflows/main.yml/badge.svg) ![test](https://github.com/ReleaseStandard/CodeEditor-application/actions/workflows/test.yml/badge.svg)  |
| language-cobol85 |  ![build](https://github.com/ReleaseStandard/CodeEditor-language-cobol85/actions/workflows/build.yml/badge.svg) ![test](https://github.com/ReleaseStandard/CodeEditor-language-cobol85/actions/workflows/test.yml/badge.svg)  |
| language-java |  ![build](https://github.com/ReleaseStandard/CodeEditor-language-java/actions/workflows/build.yml/badge.svg) ![test](https://github.com/ReleaseStandard/CodeEditor-language-java/actions/workflows/test.yml/badge.svg)  |
| language-universal |  ![build](https://github.com/ReleaseStandard/CodeEditor-language-universal/actions/workflows/build.yml/badge.svg) ![test](https://github.com/ReleaseStandard/CodeEditor-language-universal/actions/workflows/test.yml/badge.svg)  |
| widget-symbolinput |  ![build](https://github.com/ReleaseStandard/CodeEditor-widget-symbolinput/actions/workflows/build.yml/badge.svg) ![test](https://github.com/ReleaseStandard/CodeEditor-widget-symbolinput/actions/workflows/test.yml/badge.svg)  |
| editor |  ![build](https://github.com/ReleaseStandard/CodeEditor-editor/actions/workflows/build.yml/badge.svg) ![test](https://github.com/ReleaseStandard/CodeEditor-editor/actions/workflows/test.yml/badge.svg)  |
| language-golang |  ![build](https://github.com/ReleaseStandard/CodeEditor-language-golang/actions/workflows/build.yml/badge.svg) ![test](https://github.com/ReleaseStandard/CodeEditor-language-golang/actions/workflows/test.yml/badge.svg)  |
| language-mksh |  ![build](https://github.com/ReleaseStandard/CodeEditor-language-mksh/actions/workflows/build.yml/badge.svg) ![test](https://github.com/ReleaseStandard/CodeEditor-language-mksh/actions/workflows/test.yml/badge.svg)  |
| logger-debug |  ![build](https://github.com/ReleaseStandard/CodeEditor-logger-debug/actions/workflows/build.yml/badge.svg) ![test](https://github.com/ReleaseStandard/CodeEditor-logger-debug/actions/workflows/test.yml/badge.svg)  |
| emptyPlugin |  ![build](https://github.com/ReleaseStandard/CodeEditor-emptyPlugin/actions/workflows/build.yml/badge.svg) ![test](https://github.com/ReleaseStandard/CodeEditor-emptyPlugin/actions/workflows/test.yml/badge.svg)  |
| language-html |  ![build](https://github.com/ReleaseStandard/CodeEditor-language-html/actions/workflows/build.yml/badge.svg) ![test](https://github.com/ReleaseStandard/CodeEditor-language-html/actions/workflows/test.yml/badge.svg)  |
| language-python |  ![build](https://github.com/ReleaseStandard/CodeEditor-language-python/actions/workflows/build.yml/badge.svg) ![test](https://github.com/ReleaseStandard/CodeEditor-language-python/actions/workflows/test.yml/badge.svg)  |
| widget-linenumber |  ![build](https://github.com/ReleaseStandard/CodeEditor-widget-linenumber/actions/workflows/build.yml/badge.svg) ![test](https://github.com/ReleaseStandard/CodeEditor-widget-linenumber/actions/workflows/test.yml/badge.svg)  |

### What you will find in this repository<br />
- CodeEditor production ready [application](https://github.com/ReleaseStandard/CodeEditor-application).
- Plugins for CodeEditor.
- CodeEditor core.
- [Wiki](https://github.com/ReleaseStandard/CodeEditor/wiki) sources.
<br />

### Where you should go<br />
- Interested in using code editor ?
    - production ready application [here](https://github.com/ReleaseStandard/CodeEditor-application)
<br />

- Interested in plugin developpement ?
    - keep going on this readme.
    - read the [wiki](https://github.com/ReleaseStandard/CodeEditor/wiki)
<br />
<br />
<br />

### How to use this repository

```bash
git clone https://github.com/ReleaseStandard/CodeEditor
cd CodeEditor
git submodule update --init --recursive
```
This will clone application, plugins, core of code editor.<br />
- scripts/updateSM.sh : solution to problem of submodule syncing<br />
