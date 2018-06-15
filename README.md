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

- Start the frontend chat GUI:
`java frontend.Frontend`

Change the parameters (if necessary), set a username, click connect. Type a message, click send.

Close the window to quit, or type 'quit' as a message to disconnect.

