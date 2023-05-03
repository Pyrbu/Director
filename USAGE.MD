## Installation
### Step 1
Add the dependency to your project

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

### Step 2
Shade and relocate the dependency into your jar

```groovy
plugins {
    id "com.github.johnrengelman.shadow" version "8.1.1"
}

shadowJar {
    relocate "lol.pyr.extendedcommands", "your.package.here.lib.command"
    minimize() // Not required but recommended
}
```

