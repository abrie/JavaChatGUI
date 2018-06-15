# What is this?

This is a repository to document the enhancement of a Java socket chat application.
The original code is taken from [this codejava.net page](http://www.codejava.net/java-se/networking/how-to-create-a-chat-console-application-in-java-using-socket). The original code is represented by the the first commit in this repository.

# What is the enhancement?
The application will be enhanced with a GUI for the ChatClient component.

# How do I use this?

- Clone the repository:
`https://github.com/abrie/JavaChatGUI.git`

- Compile the components:
`make all`

- Start the server:
`java server.ChatServer 9999`

- Open another terminal window, and start the frontend chat GUI:
`java frontend.Frontend`

Change the parameters (if necessary). Note the default hostname is `localhost` and the default port is `9999`. Set a username, click connect. If successful, you'll see a chat window. If not, check the console for an error message.

To disconnect, type 'quit' as a chat message.

To completely exit, either hit CTRL-C in the terminal window, close the chat window, or turn off your computer. ;)

Questions, email ***abrhie@gmail.com***
