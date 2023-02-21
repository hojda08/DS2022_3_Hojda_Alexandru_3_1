import io.grpc.stub.StreamObserver;

import java.util.ArrayList;
import java.util.List;

public class ChatService extends ChatServiceGrpc.ChatServiceImplBase {

    private List<Messages.Message> messages = new ArrayList<>();

    @Override
    public void sendMessage(Messages.Message request, StreamObserver<Messages.Empty> responseObserver) {
        System.out.println("Inside sendMessage");
        messages.add(request);
        responseObserver.onNext(Messages.Empty.newBuilder().build());
        responseObserver.onCompleted();
    }

    @Override
    public void getMessages(Messages.Empty request, StreamObserver<Messages.MessageList> responseObserver) {
        System.out.println("Inside getMessages");
        Messages.MessageList messageList = Messages.MessageList.newBuilder().addAllMessages(messages).build();
        responseObserver.onNext(messageList);
        messages = new ArrayList<>();
        responseObserver.onCompleted();
    }
}
