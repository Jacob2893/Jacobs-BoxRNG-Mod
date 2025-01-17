# Jacob's BoxRNG Mod
### *Current Version: v1.0*
### *For Fabric | Minecraft 1.21.4*
## QOL mod for the BoxRNG mode on the [LuckyMC.GG](https://discord.gg/JMq3QcdnVc) minecraft server
## Current features:
### Boss spawning tracker
Tracks boss spawning progress and displays it on the player's screen.  
Based on the progress the different lines change color:  
 - 0-49% - Green
 - 50-74% - Yellow
 - 75-89% - Orange
 - 90%+ - Red (+Sound notification at 90%)
 - 10 or fewer kills remaining - Dark Red (+Sound notification at 10 kills remaining)

![](/assets/screenshots/progress-color.png)

If a hologram is out of player's range, it will have (OOR) next to it, to indicate that it's out of range.  
It will not update but save and display the last seen value  
(Cant really do much about it rn, since the holograms disappearing when you get too far away is a serverside feature)

![](/assets/screenshots/out-of-range.png)

Right now there is also a clientside command `/jacobsmod`, but i haven't added any functionality to it yet. In a future
version of this mod it will open a GUI where you can edit different aspects of the mod.