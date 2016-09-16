[v.01.2016]
--------------------------------------------------------
-------------------- Language Generation README ---------------------
--------------------------------------------------------

I.   Acknowledgements
II.  Instructions for Running
III. Other Ideas
--------------------------------------------------------

I.  Acknowledgements
Wayne Iba
--------------------------------------------------------
II.  Instructions for running
Open the project and enter a string representing the path to which the
text document that you want to use for the document is located
For example If I wanted to use the text doucment Crime and Punishment I would
enter the path to where I have the text document stored. In my example I saved
the file to my desktop

	$ "C:\\Users\\natalie\\Desktop\\textcorpus\\CrimeAndPunishment.txt"

From there create a languageGeneration object as follows

	$ LanguageGenerator l = new LanguageGenerator();

Now we can call the generateSentance method with an integer n, the n you want to
be used for the n gram, integer s null, and length that you want the randomly generated
text to be. For example if I wanted n to be 2, and the length to be n I would create the
following line in the main mehod of the program.
	
 	$ l.generateSentance(2, null, 10);	

--------------------------------------------------------
III.  Other Ideas
-The text could be processed more completely (see report)
-Other approach could be used to implement the n-gram generator