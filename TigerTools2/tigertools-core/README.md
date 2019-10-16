TigerTools2 Core
================

This is the core module for the TigerTools2 package. This contains the model classes and various utilities, as well
as the API for the Importer and Exporter modules.

To import this, add the following to your Maven POM:

```xml
        <dependency>
            <groupId>org.jason.mapmaker</groupId>
            <artifactId>tigertools-core</artifactId>
            <version>${tigertools.version}</version>
        </dependency>
```

If you are not using a property in your POM for the library, replace the ${tigertools.version} entry with the
actual version you are using.