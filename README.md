# Axe Soup: Role Playing Game

"Axe Soup" is an RPG game based on a Russian/European folk tale - [Stone Soup](https://en.wikipedia.org/wiki/Stone_Soup) (a.k.a. [Axe Poridge](https://ru.wikipedia.org/wiki/%D0%9A%D0%B0%D1%88%D0%B0_%D0%B8%D0%B7_%D1%82%D0%BE%D0%BF%D0%BE%D1%80%D0%B0)).

The game is situated in a dark place remote from civilization and surrouned by monsters. The player is a casual owner of a home in the woods. One day, a traveler knocks on the door and asks for shelter and food. The player agrees to give him shelter, but is corrupted by greed and doesn't share a meal.

At that moment the travelers asks if the player has an axe, and claims to cook a bowl of axe soup. Player, being intrigued, agrees to share the axe. The traveler then asks for a carrot, meat, and so on. The player is asked to share all the ingredients, and in the end, the traveler cooks a delicious soup, which they share.


| Title screen | Language selection | Game itself | 
|--|--|--|
|<img src="/assets/preview_1.png">|<img src="/assets/preview_2.png">|<img src="/assets/preview_3.png">|


## Assignment

For the final project of Object Oriented Programming, your team should create an RPG game. While there are some requirements to the outcome, which are listed below, you are mostly free in how you give shape to this project. For example, you may choose to have your RPG reflect an epic quest in a fantasy setting, an anxious protagonist buying groceries from a list, or fireside conversations among friends camping in the wild.

To take full advantage of object oriented programming, each member of the team should be responsible for different aspects of the game. Unlike most assignments, where you are expected to work together for every step on the assignment, this final project explicitly asks you to divide responsibilities among your team members. To facilitate this process, there are four sets of requirements outlined below. Each team member is responsible for one of these sets. Of course, you should still discuss, cooperate, and help one another out to create a whole from its parts. 

Below are the requirements for your RPG game. Assessment focuses on the design choices you have made while making the game. This includes your use of interfaces and abstract classes, proper encapsulation, design patterns, as well as how easy it is for a third party to understand the game logic and expand it with new variations and features. In addition, we look at programming style (indentation, variable naming, etc) and the level of ambition. That is, if your final program meets all the requirements below, but only just barely, it may not result in a sufficient grade.

Your RPG should have the features listed below. Each team member takes responsibility for one of these features, which determines most of their grade. For example, the team member taking responsibility for the inventory system will not be graded on the quality of the location system. The features to implement are:
- Inventory
	- The game should have an inventory system. This could represent a bag of items, a party of team members,  conversation topics, or clues obtained in the game world.
	- It should be possible to add and remove elements from the inventory system.
	- The player should be able to interact with elements of the inventory system. For example, they should be able to inspect objects, rearrange team members, or combine clues.
- Locations
	- The game should feature distinct locations.
	- Players should be able to move between locations.
	- It must be possible to add conditions to movement. For example, movement across water is only possible by boat, or movement into a subway system is only possible in exchange for a subway token.
- Non-player characters (NPCs)
	- The game should include NPCs that exist independently from locations and inventory.
	- Players must be able to interact with NPCs. For example, you could allow players to fight monsters, talk to suspects, or bribe bouncers.
	- It must be possible for interaction with NPCs to have lasting effects on the game world outside of the NPCs themselves. For example, interacting with NPCs may unlock locations, or add items to the inventory system.
- Player classes
	- Players must have a free choice among several player classes. 
	- Player classes should meaningfully change the game. For example, you may implement warriors that deal high damage at short range and archers that deal lower damage at long range. Alternatively, you could implement traits such as charisma or intelligence, which may change what conversation options are open to users ay key points in the game.

In addition, there are some general requirements that every team member should implement for their own feature:
- The player should be able to save and restore their game state at any time.
- The game logic itself should be independent of the way it is presented to the player. That is, your game should follow the guidelines of the Model-View-Controller pattern. Note, however, that you are free to choose whether the game has a text-based interface, a GUI, or even some other way of connecting the player to the game. The game should be playable on the university computers without additional hardware or software.
- The program makes proper use of exceptions. That is, exceptions are added where appropriate, without abusing the exception mechanism.
- The game should completely playable in at least two languages, one of them being English.
- Each feature should be implemented in its own package. 
- Each class should contain the `@author` Javadoc tag to indicate the main author. During the demo, questions about that class will be asked to the listed `@author`. Additionally, the progamming style and internal design choices of a class contribute only to the grade of the listed `@author`.

