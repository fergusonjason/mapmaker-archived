TigerTools GeoJSON Exporter
=============================

This is the exporter for the GeoJSON format, which is actually just a String. Given how large the output is
it is NOT recommended to export an entire Tiger shapefile to a single String. (However, I have not yet implemented
selective import... give me a few days).

To support GeoJSON export, you must have the tigertools-core jar and tigertools-exporter-geojson jar specified in
your Maven POM:

```xml
<dependencies>
    <dependency>
        <groupId>org.jason.mapmaker</groupId>
        <artifactId>tigertools-core</artifactId>
        <version>${tigertools.version}</version>
    </dependency>
    <dependency>
        <groupId>org.jason.mapmaker</groupId>
        <artifactId>tigertools-exporter-shapefile</artifactId>
        <version>${tigertools.version}</version>
    </dependency>
</dependencies>
```
