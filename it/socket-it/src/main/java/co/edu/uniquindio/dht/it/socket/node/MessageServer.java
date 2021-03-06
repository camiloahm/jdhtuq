package co.edu.uniquindio.dht.it.socket.node;

import co.edu.uniquindio.utils.communication.message.Message;
import co.edu.uniquindio.utils.communication.transfer.MessageProcessor;
import co.edu.uniquindio.utils.communication.transfer.network.MessageSerialization;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MessageServer implements Runnable {
    private static final Logger logger = Logger
            .getLogger(MessageServer.class);
    private ServerSocket serverSocket;
    private MessageSerialization messageSerialization;
    private MessageProcessor messageProcessor;
    private boolean isRunning = true;

    public MessageServer(Integer portTcp, MessageSerialization messageSerialization, MessageProcessor messageProcessor) {
        try {
            this.messageProcessor = messageProcessor;
            this.messageSerialization = messageSerialization;
            this.serverSocket = new ServerSocket(portTcp);
        } catch (IOException e) {
            logger.error("Error creating server socket", e);
        }
    }

    @Override
    public void run() {
        String stringMessage;
        Message message = null;
        ObjectInputStream objectInputStream;

        Message response = null;
        while (isRunning) {
            try (Socket socket = serverSocket.accept()) {
                objectInputStream = new ObjectInputStream(socket.getInputStream());
                stringMessage = (String) objectInputStream.readObject();

                ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                        socket.getOutputStream());

                message = messageSerialization.decode(stringMessage);

                response = messageProcessor.process(message);

                objectOutputStream.writeObject(messageSerialization.encode(response));
            } catch (IOException | ClassNotFoundException e) {
                logger.error("Error reading socket", e);
            }
        }
    }
}
