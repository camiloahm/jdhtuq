package co.edu.uniquindio.utils.communication.transfer;

import co.edu.uniquindio.utils.communication.Observer;
import co.edu.uniquindio.utils.communication.message.Message;

/**
 * The {@code CommunicationManager} interface is used to send messages
 * regardless of the implementation of the way in which they are sent.
 *
 * @author Daniel Pelaez
 * @author Hector Hurtado
 * @author Daniel Lopez
 * @version 1.0.2, 17/06/2010
 * @since 1.0.2
 */
public interface CommunicationManager {

    /**
     * Creates and sends a message specifying its type, the type of the response
     * and the data. Used <code>sendMessage(message)</code> to send message
     *
     * @param message    The type of the message that will be sent.
     * @param typeReturn The type of the response
     * @return An object <T> of the specified type.
     */
    <T> T sendMessageUnicast(Message message, Class<T> typeReturn);

    /**
     * Creates and sends a message specifying its type, the type of the response
     * and the data. Used <code>sendMessage(message)</code> to send message
     *
     * @param message         The type of the message that will be sent.
     * @param typeReturn      The type of the response
     * @param paramNameResult Param name of result
     * @return An object <T> of the specified type.
     */
    <T> T sendMessageUnicast(Message message, Class<T> typeReturn,
                             String paramNameResult);

    /**
     * Sends a message specifying its type, the type of the response and the
     * data. Used Communicator instance called unicastManager to send message
     *
     * @param message Messages to send
     */
    void sendMessageUnicast(Message message);

    /**
     * Creates and sends a multicast message specifying its type, the type of
     * the response and the data. Used
     * <code>sendMessageMultiCast(message)</code> to send message
     *
     * @param <T>        Type return
     * @param message    Message
     * @param typeReturn Type return
     * @return Response
     */
    <T> T sendMessageMultiCast(Message message, Class<T> typeReturn);

    /**
     * Creates and sends a multicast message specifying its type, the type of
     * the response and the data. Used
     * <code>sendMessageMultiCast(message)</code> to send message
     *
     * @param <T>             Type return
     * @param message         Message
     * @param typeReturn      Type return
     * @param paramNameResult Param name of result
     * @return Response
     */
    <T> T sendMessageMultiCast(Message message, Class<T> typeReturn,
                               String paramNameResult);

    /**
     * Sends a multicast message specifying its type, the type of the response
     * and the data. Used Communicator instance called multicastManager to send
     * message
     *
     * @param message Messages to send
     */
    void sendMessageMultiCast(Message message);

    /**
     * Stop all process
     */
    void stopAll();

    /**
     * Adds observer to communication
     *
     * @param observer Observer to add
     */
    void addObserver(Observer<Message> observer);

    /**
     * Remove observer to communication
     *
     * @param observer Observer to remove
     */
    void removeObserver(Observer<Message> observer);

    /**
     * Remove observer by name
     *
     * @param name Observer name
     */
    void removeObserver(String name);

    /**
     * Add a message processor to handle any kind of message
     *
     * @param name             of the message processor
     * @param messageProcessor message processor
     */
    void addMessageProcessor(String name, MessageProcessor messageProcessor);

    /**
     * Remove a message processor
     *
     * @param name of the message processor
     */
    void removeMessageProcessor(String name);

    /**
     * Initialize communication manager.
     */
    void init();
}
