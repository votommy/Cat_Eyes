<img src="logo.png" width="150px">

# Cat Eyes
This is an installable modification for the videogame, Minecraft Java Edition.  
Utilizing Java and some JSON, I created an addition to the game to make it easier for players to navigate poorly lit environments.

View the mod and download link: [(CurseForge)](https://www.curseforge.com/minecraft/mc-mods/cat-eyes-night-vision-toggle-mod)

### One user wrote:
*"Thankyou for such a great mod, I have low vision and mods like this are actually a great accessibility aid because I cannot rely on the monitor gamma..."*


## Developer Guide

### To update
- Update the properties from *gradle.properties*. Follow the [Fabric Develop guide](https://fabricmc.net/develop/).  
- Update the fabric-loom version from *build.gradle*.  
- If it's a new Minecraft version, change the version number in */src/main/resources/fabric.mod.json*

### To build
Enter ```./gradlew build``` into the terminal.<br>
The build will appear in the *build/libs* folder
