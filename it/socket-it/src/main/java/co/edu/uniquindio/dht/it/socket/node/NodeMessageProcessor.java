package co.edu.uniquindio.dht.it.socket.node;

import co.edu.uniquindio.chord.ChordKey;
import co.edu.uniquindio.chord.node.ChordNode;
import co.edu.uniquindio.dhash.node.DHashNode;
import co.edu.uniquindio.dhash.resource.BytesResource;
import co.edu.uniquindio.dht.it.socket.Protocol;
import co.edu.uniquindio.storage.StorageException;
import co.edu.uniquindio.utils.communication.message.Address;
import co.edu.uniquindio.utils.communication.message.Message;
import co.edu.uniquindio.utils.communication.transfer.MessageProcessor;
import lombok.extern.slf4j.Slf4j;
import org.apache.log4j.Logger;

import javax.swing.text.html.Option;
import java.math.BigInteger;
import java.util.Optional;

@Slf4j
public class NodeMessageProcessor implements MessageProcessor {

    private final DHashNode storageNode;

    public NodeMessageProcessor(DHashNode storageNode) {
        this.storageNode = storageNode;
    }

    @Override
    public Message process(Message request) {
        Message response = null;

        if (request.getMessageType().equals(Protocol.GET)) {
            response = processGet(request);
        }
        if (request.getMessageType().equals(Protocol.GET_SUCCESSOR)) {
            response = processGetSuccessor(request);
        }
        if (request.getMessageType().equals(Protocol.PUT)) {
            response = processPut(request);
        }
        if (request.getMessageType().equals(Protocol.LEAVE)) {
            response = processLeave(request);
        }

        return response;
    }

    private Message processLeave(Message request) {
        try {
            storageNode.leave();

            return Message.builder()
                    .sendType(Message.SendType.RESPONSE)
                    .messageType(Protocol.LEAVE_RESPONSE)
                    .address(Address.builder()
                            .source(request.getAddress().getDestination())
                            .destination(request.getAddress().getSource())
                            .build())
                    .param(Protocol.LeaveResponseParams.MESSAGE.name(), "OK")
                    .build();
        } catch (StorageException e) {
            log.error("Problem doing put", e);
            return Message.builder()
                    .sendType(Message.SendType.RESPONSE)
                    .messageType(Protocol.LEAVE_RESPONSE)
                    .address(Address.builder()
                            .source(request.getAddress().getDestination())
                            .destination(request.getAddress().getSource())
                            .build())
                    .param(Protocol.LeaveResponseParams.MESSAGE.name(), e.getMessage())
                    .build();
        }
    }

    private Message processGetSuccessor(Message request) {
        ChordNode overlayNode = (ChordNode) storageNode.getOverlayNode();

        log.info("Querying current successor: " + Optional.ofNullable(overlayNode.getSuccessor()).orElse(new ChordKey(BigInteger.ZERO)).getValue());

        return Message.builder()
                .sendType(Message.SendType.RESPONSE)
                .messageType(Protocol.GET_SUCCESSOR_RESPONSE)
                .address(Address.builder()
                        .source(request.getAddress().getDestination())
                        .destination(request.getAddress().getSource())
                        .build())
                .param(Protocol.GetSuccessorResponseParams.SUCCESSOR.name(), overlayNode.getSuccessor().getValue())
                .build();
    }

    private Message processPut(Message request) {
        BytesResource resource = new BytesResource(request.getParam(Protocol.PutParams.RESOURCE_NAME.name()), request.getData(Protocol.PutDatas.RESOURCE.name()));

        try {
            boolean success = storageNode.put(resource);

            if (success){
                return Message.builder()
                        .sendType(Message.SendType.RESPONSE)
                        .messageType(Protocol.PUT_RESPONSE)
                        .address(Address.builder()
                                .source(request.getAddress().getDestination())
                                .destination(request.getAddress().getSource())
                                .build())
                        .param(Protocol.PutResponseParams.MESSAGE.name(), "OK")
                        .build();
            }else{
                return Message.builder()
                        .sendType(Message.SendType.RESPONSE)
                        .messageType(Protocol.PUT_RESPONSE)
                        .address(Address.builder()
                                .source(request.getAddress().getDestination())
                                .destination(request.getAddress().getSource())
                                .build())
                        .param(Protocol.PutResponseParams.MESSAGE.name(), "Put unsuccessful")
                        .build();
            }
        } catch (StorageException e) {
            log.error("Problem doing put", e);
            return Message.builder()
                    .sendType(Message.SendType.RESPONSE)
                    .messageType(Protocol.PUT_RESPONSE)
                    .address(Address.builder()
                            .source(request.getAddress().getDestination())
                            .destination(request.getAddress().getSource())
                            .build())
                    .param(Protocol.PutResponseParams.MESSAGE.name(), e.getMessage())
                    .build();
        }
    }

    private Message processGet(Message request) {
        try {
            BytesResource resource = (BytesResource) storageNode.get(request.getParam(Protocol.GetParams.RESOURCE_NAME.name()));

            return Message.builder()
                    .sendType(Message.SendType.RESPONSE)
                    .messageType(Protocol.GET_RESPONSE)
                    .address(Address.builder()
                            .source(request.getAddress().getDestination())
                            .destination(request.getAddress().getSource())
                            .build())
                    .param(Protocol.GetResponseParams.MESSAGE.name(), "OK")
                    .data(Protocol.GetResponseDatas.RESOURCE.name(), resource.getBytes())
                    .build();
        } catch (StorageException e) {
            log.error("Problem doing get", e);
            return Message.builder()
                    .sendType(Message.SendType.RESPONSE)
                    .messageType(Protocol.GET_RESPONSE)
                    .address(Address.builder()
                            .source(request.getAddress().getDestination())
                            .destination(request.getAddress().getSource())
                            .build())
                    .param(Protocol.GetResponseParams.MESSAGE.name(), e.getMessage())
                    .build();
        }
    }
}
