# This is alump's version for Vaadin Rich Text Editor for Flow

See the webcomponent project at address:
[github.com/alump/alump-vaadin-rich-text-editor](https://github.com/alump/alump-vaadin-rich-text-editor)

**Notice:** This is for NPM projects only, not for Legacy Bower based projects.

## Run flow side
- Run `mvn clean install`
- Run `mvn jetty:run-exploded -Pwar`

If issues remove node_modules, package*.json and webpack.*.js under alump-vaadin-rich-text-editor-flow-demo
folder and later command again.

## Update flow side
- See the NpmPackage annotation on GeneratedVaadinRichTextEditor class, update npm dependency number there
- Update your flow versions with `mvn versions:set`
- Build a zip for directory `mvn clean install -Pdirectory`
- Go to Vaadin Directory and release the zip there

This project is a fork of Vaadin Rich Text Editor for Flow. The following README is from original project.

# Vaadin Rich Text Editor for Flow

Vaadin Rich Text Editor for Flow is a UI component add-on for Vaadin which provides rich text editor functionality.

## License & Author

This Add-on is distributed under [Commercial Vaadin Add-on License version 3](http://vaadin.com/license/cval-3) (CVALv3). For license terms, see LICENSE.txt.

Vaadin Rich Text Editor is written by Vaadin Ltd.

To purchase a license, visit http://vaadin.com/pricing

### Installing
Add Rich Text Editor to your project
```xml
<dependencies>
  <dependency>
    <groupId>com.vaadin</groupId>
    <artifactId>vaadin-rich-text-editor-flow</artifactId>
    <version>1.0.1</version>
  </dependency>
</dependencies>
```

### Using Vaadin Rich Text Editor

[<img src="https://raw.githubusercontent.com/vaadin/vaadin-rich-text-editor-flow/master/screenshot.gif" width="700" alt="Screenshot of Vaadin Rich Text Editor">](https://vaadin.com/components/vaadin-rich-text-editor)

#### Basic use
In the most basic use case, Vaadin Rich Text Editor should be initialised and its value can be handled using <code>setValue</code> and <code>getValue</code> methods.
<code>htmlValue</code> can be accessed using <code>getHtmlValue</code> method.

```java
RichTextEditor rte = new RichTextEditor();

// Example of handling value with buttons.

Button getHtmlValueBtn = new Button("Get html value", e -> htmlValue = rte.getHtmlValue());
Button getValueBtn = new Button("Get value", e -> value = rte.getValue());
Button setValueBtn = new Button("Set value", e -> rte.setValue(value));
```

#### Mirroring the content
The value of the Vaadin Rich Text Editor can be simultaneously mirrored to other Vaadin Rich Text Editor and to any other component using value change listener.

```java
RichTextEditor rte = new RichTextEditor();
RichTextEditor mirroredRte = new RichTextEditor();
Div block = new Div();

// Mirroring the content using listener.

rte.addValueChangeListener(e -> {
    mirroredRte.setValue(rte.getValue());
    block.getElement().setProperty("innerHTML", rte.getHtmlValue());
});
```

## Setting up for development:

Clone the project in GitHub (or fork it if you plan on contributing)

```
git clone git@github.com:vaadin/vaadin-rich-text-editor-flow.git
```

To build and install the project into the local repository run

```mvn install -DskipITs```

in the root directory. `-DskipITs` will skip the integration tests, which require a TestBench license. If you want to run all tests as part of the build, run

```mvn install```
