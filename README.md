# leword

## Small helper application for [wordle](https://www.powerlanguage.co.uk/wordle/)

This is a _very_ simple java command line app to help solving wordle puzzles. It does not use any code or resources from the wordle game.

Essentially, the user follows a series of prompts and enters the letters they know, and their positions. They also enter the letters they know are not in the solution, and the letters they know are not in specific positions. Once done, the app presents the user with a list of suggested words, and highlights words from that result set which are in the 20,000 most used english-language words. The highlighted words are not guaranteed to be *correct*, they are merely _more likely_ to be a correct solution.

Datasets used are:-

* [Collins Scrabble Words 2021](https://ia903406.us.archive.org/31/items/csw21/CSW21.txt)  
* [20,000 most common english words](https://github.com/first20hours/google-10000-english)

To use the packaged .jar file, the two text files, `top.txt` and `wordlist.txt` must be available in the runtime drawer. This also allows the user to change the datasets if they wish, as opposed to packaging them in the .jar file.

This project was to scratch a personal itch, whereby when using word search tools it was not possible to place `blocks` in the manner which wordle-solving requires. If you find it as useful as I do, then let me know.
