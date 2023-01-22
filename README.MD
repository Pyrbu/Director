# ExtendedBukkitCommands
A lightweight command library that aims to not stray too far from the 
classic Bukkit style commands but still provide a lot of useful features.

## Installation
### Step 1 
Add the dependency to your project

#### Gradle
```groovy
repositories {
    maven {
        url "https://repo.pyr.lol/releases/"
    }
}

dependencies {
    implementation "lol.pyr:extendedcommands:VERSION"
}
```

#### Maven
```xml
<repository>
    <id>pyr</id>
    <url>https://repo.pyr.lol/releases</url>
</repository>
```
```xml
<dependency>
    <groupId>lol.pyr</groupId>
    <artifactId>extendedcommands</artifactId>
    <version>VERSION</version>
</dependency>
```

### Step 2
Shade and relocate the dependency into your jar

#### Gradle
```groovy
plugins {
    id "com.github.johnrengelman.shadow" version "7.1.2"
}

shadowJar {
    relocate "lol.pyr.extendedcommands", "your.package.here.lib.command"
    minimize() // Not required but recommended
}
```

#### Maven
(Something like this, I don't use maven and every time i try to do this it is very buggy. I recommend switching to Gradle)
```xml
<build>
  <plugins>
    <plugin>
      <groupId>org.apache.maven.plugins</groupId>
      <artifactId>maven-shade-plugin</artifactId>
      <version>3.3.0</version>
      <executions>
        <execution>
          <phase>package</phase>
          <goals>
            <goal>shade</goal>
          </goals>
          <configuration>
            <!-- Not required but recommended -->
            <minimizeJar>true</minimizeJar>
            <relocations>
              <relocation>
                <pattern>lol.pyr.extendedcommands</pattern>
                <shadedPattern>your.package.here.lib.command</shadedPattern>
              </relocation>
            </relocations>
          </configuration>
        </execution>
      </executions>
    </plugin>
  </plugins>
</build>
```