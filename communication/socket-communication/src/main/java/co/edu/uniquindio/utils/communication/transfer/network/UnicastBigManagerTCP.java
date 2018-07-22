/*
 *  Communication project implement communication point to point and multicast
 *  Copyright (C) 2010  Daniel Pelaez, Daniel Lopez, Hector Hurtado
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Affero General Public License as
 *  published by the Free Software Foundation, either version 3 of the
 *  License, or (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Affero General Public License for more details.
 *
 *  You should have received a copy of the GNU Affero General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package co.edu.uniquindio.utils.communication.transfer.network;

import co.edu.uniquindio.utils.communication.message.Message;
import co.edu.uniquindio.utils.communication.transfer.Communicator;
import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * The <code>UnicastBigManagerTCP</code> class is an
 * <code>Communicator</code>. Implemented the communication for network on TCP
 * protocol.
 *
 * @author Daniel Pelaez
 * @version 1.0, 17/06/2010
 * @since 1.0
 */
@Slf4j
public class UnicastBigManagerTCP implements Communicator {

    /**
     * The server socket that will be waiting for connection.
     */
    private ServerSocket serverSocket;

    /**
     * The value of the port used to create the socket.
     */
    private int portTcp;

    private final MessageSerialization messageSerialization;

    public UnicastBigManagerTCP(int portTcp, MessageSerialization messageSerialization) {
        this.portTcp = portTcp;
        this.messageSerialization = messageSerialization;

        try {
            this.serverSocket = new ServerSocket(portTcp);
        } catch (IOException e) {
            log.error("The communication socket could not be created", e);
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * co.edu.uniquindio.utils.communication.transfer.Communicator#reciever()
     */
    public Message reciever() {
        String stringMessage;
        Message message = null;
        ObjectInputStream objectInputStream;

        try (Socket socket = serverSocket.accept()) {
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            stringMessage = (String) objectInputStream.readObject();

            message = messageSerialization.decode(stringMessage);

        } catch (IOException e) {
            log.error("Error reading socket", e);
        } catch (ClassNotFoundException e) {
            log.error("Error reading socket", e);
        }

        return message;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * co.edu.uniquindio.utils.communication.transfer.Communicator#send(co.edu
     * .uniquindio.utils.communication.message.Message)
     */
    public void send(Message message) {

        Socket socket = null;
        try {
            socket = new Socket(message.getAddress().getDestination(), portTcp);

            ObjectOutputStream objectOutputStream = new ObjectOutputStream(
                    socket.getOutputStream());
            objectOutputStream.writeObject(messageSerialization.encode(message));
            objectOutputStream.flush();

        } catch (UnknownHostException e) {
            log.error("Error sending message", e);
        } catch (IOException e) {
            log.error("Error sending message", e);
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                log.error("Error closed socket", e);
            }
        }

    }

    /**
     * Gets port TCP
     *
     * @return Port TCP
     */
    public int getPortTcp() {
        return portTcp;
    }

    /**
     * Sets port TCP
     *
     * @param portTcp Port TCP
     */
    public void setPortTcp(int portTcp) {
        this.portTcp = portTcp;
    }

    /*
     * (non-Javadoc)
     *
     * @see co.edu.uniquindio.utils.communication.transfer.Stoppable#stop()
     */
    public void stop() {
        try {
            serverSocket.close();
        } catch (IOException e) {
            log.error("Error closed socked", e);
        }
    }

}
