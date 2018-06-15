# What is this?

This is a repository to document the enhancement of a Java socket chat application.
The original CLI code is taken from [this codejava.net page](http://www.codejava.net/java-se/networking/how-to-create-a-chat-console-application-in-java-using-socket). The original code is represented by the the first commit in this repository.

# What is the enhancement?
The application has been enhanced with a GUI for the ChatClient component.

# How do I use this?

- Clone the repository:
`https://github.com/abrie/JavaChatGUI.git`

- Compile the components:
`make all`

- Start the server:
`java server.ChatServer 9999`

- Open another terminal window, and start the frontend chat GUI:
`java frontend.Frontend`

Change the connect parameters if the defaults are not suitable. Note the default hostname of `localhost` and the default port of `9999` match the server invocation described above. 

Set a username, click connect. 

If successful, you'll see a chat window. If not, check the console window for an error message. 

Launch additional instances using `java frontend.Frontend` to add more users. Other computers on the LAN may also connect, assuming you know the server machine's IP and have a suitable firewall configuration (left as an exercise to the reader.) 

To disconnect, type 'quit' as a chat message.

To completely exit, either hit CTRL-C in the terminal window, close the chat window, or turn off your computer. ;)

# Why was this made?

The reasons are vague.

Questions, email ***abrhie@gmail.com***
