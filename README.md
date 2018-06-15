# What is this?

This is a repository to document the enhancement of a Java socket chat application.
The original CLI code is taken from [this codejava.net page](http://www.codejava.net/java-se/networking/how-to-create-a-chat-console-application-in-java-using-socket). The original code is represented by the the first commit in this repository.

# What is the enhancement?
The application has been enhanced with a JavaFX GUI.

# How do I test this?

- Clone the repository:
`git clone https://github.com/abrie/JavaChatGUI.git`

- Change into the repository directory, and build the components:
`make all`

- Start the server:
`java server.ChatServer 9999`
![Chat Server](/screenshots/server.png)

- Open a new terminal window, change to the repository directory, and start the frontend:
`java frontend.Frontend`
![Frontend CLI](/screenshots/frontend-cli.png)

The frontend application will spawn a GUI window. Change the connect parameters to match your server configuration. Use the defaults if you followed the above directions. Now set a username, and click connect.
![Frontend CLI](/screenshots/frontend-settings.png)

If successful, you'll see a chat window. If not, check the console window for an error message.

Launch additional instances using `java frontend.Frontend` to add more users. Other computers on the LAN may also connect, assuming you know the server machine's IP and have a suitable firewall configuration (left as an exercise to the reader.)

![Frontend Chat](/screenshots/frontend-chat.png)

To disconnect, type 'quit' as a chat message.

To completely exit, either hit CTRL-C in the terminal window, close the chat window, or turn off your computer. ;)

# Why was this made?

The reasons are vague and might never be satisfactorily explained.

Questions, email ***abrhie@gmail.com***
