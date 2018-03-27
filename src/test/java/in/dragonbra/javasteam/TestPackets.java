package in.dragonbra.javasteam;

import com.google.protobuf.ByteString;
import in.dragonbra.javasteam.base.ClientMsg;
import in.dragonbra.javasteam.base.ClientMsgProtobuf;
import in.dragonbra.javasteam.enums.*;
import in.dragonbra.javasteam.generated.*;
import in.dragonbra.javasteam.protobufs.steamclient.SteammessagesClientserver.CMsgClientChatInvite;
import in.dragonbra.javasteam.protobufs.steamclient.SteammessagesClientserver.CMsgClientSessionToken;
import in.dragonbra.javasteam.protobufs.steamclient.SteammessagesClientserverFriends.*;
import in.dragonbra.javasteam.protobufs.steamclient.SteammessagesClientserverLogin.*;
import in.dragonbra.javasteam.types.SteamID;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * @author lngtr
 * @since 2018-03-24
 */
public abstract class TestPackets {

    public static byte[] getPacket(EMsg msgType, boolean isProto) {
        switch (msgType) {
            case ClientLogOnResponse:
                return clientLogOnResponse(isProto);
            case ClientLoggedOff:
                return clientLoggedOff(isProto);
            case ClientNewLoginKey:
                return clientNewLoginKey();
            case ClientSessionToken:
                return clientSessionToken();
            case ClientUpdateMachineAuth:
                return clientUpdateMachineAuth();
            case ClientAccountInfo:
                return clientAccountInfo();
            case ClientWalletInfoUpdate:
                return clientWalletInfoUpdate();
            case ClientRequestWebAPIAuthenticateUserNonceResponse:
                return clientRequestWebAPIAuthenticateUserNonceResponse();
            case ClientMarketingMessageUpdate2:
                return clientMarketingMessageUpdate2();
            case ClientFriendMsgIncoming:
                return clientFriendMsgIncoming();
            case ClientFriendMsgEchoToSender:
                return clientFriendMsgEchoToSender();
            case ClientFSGetFriendMessageHistoryResponse:
                return clientFSGetFriendMessageHistoryResponse();
            case ClientFriendsList:
                return clientFriendsList();
            case ClientPersonaState:
                return clientPersonaState();
            case ClientClanState:
                return clientClanState();
            case ClientAddFriendResponse:
                return clientAddFriendResponse();
            case ClientChatEnter:
                return clientChatEnter();
            case ClientChatMsg:
                return clientChatMsg();
            case ClientChatMemberInfo:
                return clientChatMemberInfo();
            case ClientChatRoomInfo:
                return clientChatRoomInfo();
            case ClientChatActionResult:
                return clientChatActionResult();
            case ClientChatInvite:
                return clientChatInvite();
            case ClientSetIgnoreFriendResponse:
                return clientSetIgnoreFriendResponse();
            case ClientFriendProfileInfoResponse:
                return clientFriendProfileInfoResponse();
            case ClientPersonaChangeResponse:
                return clientPersonaChangeResponse();
            case ClientPlayerNicknameList:
                return clientPlayerNicknameList();
            case AMClientSetPlayerNicknameResponse:
                return aMClientSetPlayerNicknameResponse();
            case ClientAMGetPersonaNameHistoryResponse:
                return clientAMGetPersonaNameHistoryResponse();
            default:
                throw new NullPointerException();
        }
    }

    private static byte[] loadFile(String name) {
        try {
            return IOUtils.toByteArray(TestPackets.class.getClassLoader().getResourceAsStream("testpackets/" + name));
        } catch (IOException e) {
            return null;
        }
    }

    // region SteamUser

    private static byte[] clientLogOnResponse(boolean isProto) {
        if (isProto) {
            ClientMsgProtobuf<CMsgClientLogonResponse.Builder> msgProto =
                    new ClientMsgProtobuf<>(CMsgClientLogonResponse.class, EMsg.ClientLogOnResponse);

            msgProto.getBody().setEresult(EResult.OK.code());

            return msgProto.serialize();
        } else {
            ClientMsg<MsgClientLogOnResponse> msg = new ClientMsg<>(MsgClientLogOnResponse.class);

            msg.getBody().setResult(EResult.OK);

            return msg.serialize();
        }
    }

    private static byte[] clientLoggedOff(boolean isProto) {
        if (isProto) {
            ClientMsgProtobuf<CMsgClientLoggedOff.Builder> msgProto =
                    new ClientMsgProtobuf<>(CMsgClientLoggedOff.class, EMsg.ClientLoggedOff);

            msgProto.getBody().setEresult(EResult.OK.code());

            return msgProto.serialize();
        } else {
            ClientMsg<MsgClientLoggedOff> msg = new ClientMsg<>(MsgClientLoggedOff.class);

            msg.getBody().setResult(EResult.OK);

            return msg.serialize();
        }
    }

    private static byte[] clientNewLoginKey() {
        ClientMsgProtobuf<CMsgClientNewLoginKey.Builder> msg =
                new ClientMsgProtobuf<>(CMsgClientNewLoginKey.class, EMsg.ClientNewLoginKey);

        msg.getBody().setLoginKey("testloginkey");
        msg.getBody().setUniqueId(69);

        return msg.serialize();
    }

    private static byte[] clientSessionToken() {
        ClientMsgProtobuf<CMsgClientSessionToken.Builder> msg =
                new ClientMsgProtobuf<>(CMsgClientSessionToken.class, EMsg.ClientSessionToken);

        msg.getBody().setToken(123);

        return msg.serialize();
    }

    private static byte[] clientUpdateMachineAuth() {
        return loadFile("ClientUpdateMachineAuth.bin");
    }

    private static byte[] clientAccountInfo() {
        ClientMsgProtobuf<CMsgClientAccountInfo.Builder> msg =
                new ClientMsgProtobuf<>(CMsgClientAccountInfo.class, EMsg.ClientAccountInfo);

        msg.getBody().setPersonaName("testpersonaname");
        msg.getBody().setIpCountry("XX");

        return msg.serialize();
    }

    private static byte[] clientWalletInfoUpdate() {
        return loadFile("ClientWalletInfoUpdate.bin");
    }

    private static byte[] clientRequestWebAPIAuthenticateUserNonceResponse() {
        ClientMsgProtobuf<CMsgClientRequestWebAPIAuthenticateUserNonceResponse.Builder> msg =
                new ClientMsgProtobuf<>(CMsgClientRequestWebAPIAuthenticateUserNonceResponse.class, EMsg.ClientRequestWebAPIAuthenticateUserNonceResponse);

        msg.getBody().setEresult(EResult.OK.code());
        msg.getBody().setWebapiAuthenticateUserNonce("testnonce");

        return msg.serialize();
    }

    private static byte[] clientMarketingMessageUpdate2() {
        return loadFile("ClientMarketingMessageUpdate2.bin");
    }

    private static byte[] clientFriendMsgIncoming() {
        ClientMsgProtobuf<CMsgClientFriendMsgIncoming.Builder> msg =
                new ClientMsgProtobuf<>(CMsgClientFriendMsgIncoming.class, EMsg.ClientFriendMsgIncoming);

        try {
            msg.getBody().setMessage(ByteString.copyFrom("testmessage", "utf-8"));
        } catch (UnsupportedEncodingException ignored) {
        }

        msg.getBody().setSteamidFrom(123);
        return msg.serialize();
    }

    private static byte[] clientFriendMsgEchoToSender() {
        ClientMsgProtobuf<CMsgClientFriendMsgIncoming.Builder> msg =
                new ClientMsgProtobuf<>(CMsgClientFriendMsgIncoming.class, EMsg.ClientFriendMsgEchoToSender);

        try {
            msg.getBody().setMessage(ByteString.copyFrom("testmessage", "utf-8"));
        } catch (UnsupportedEncodingException ignored) {
        }

        msg.getBody().setSteamidFrom(123);
        return msg.serialize();
    }

    private static byte[] clientFSGetFriendMessageHistoryResponse() {
        return loadFile("ClientFSGetFriendMessageHistoryResponse.bin");
    }

    private static byte[] clientFriendsList() {
        return loadFile("ClientFriendsList.bin");
    }

    private static byte[] clientPersonaState() {
        return loadFile("ClientPersonaState.bin");
    }

    private static byte[] clientClanState() {
        return loadFile("ClientClanState.bin");
    }

    private static byte[] clientAddFriendResponse() {
        ClientMsgProtobuf<CMsgClientAddFriendResponse.Builder> msg =
                new ClientMsgProtobuf<>(CMsgClientAddFriendResponse.class, EMsg.ClientAddFriendResponse);

        msg.getBody().setEresult(EResult.OK.code());

        return msg.serialize();
    }

    private static byte[] clientChatEnter() {
        ClientMsg<MsgClientChatEnter> msg = new ClientMsg<>(MsgClientChatEnter.class);

        msg.getBody().setSteamIdChat(new SteamID(123));
        msg.getBody().setChatRoomType(EChatRoomType.Friend);
        msg.getBody().setEnterResponse(EChatRoomEnterResponse.Success);

        return msg.serialize();
    }

    private static byte[] clientChatMsg() {
        ClientMsg<MsgClientChatMsg> msg = new ClientMsg<>(MsgClientChatMsg.class);

        msg.getBody().setSteamIdChatter(new SteamID(123));

        return msg.serialize();
    }

    private static byte[] clientChatMemberInfo() {
        ClientMsg<MsgClientChatMemberInfo> msg = new ClientMsg<>(MsgClientChatMemberInfo.class);

        msg.getBody().setSteamIdChat(new SteamID(123));
        msg.getBody().setType(EChatInfoType.StateChange);

        return msg.serialize();
    }

    private static byte[] clientChatRoomInfo() {
        ClientMsg<MsgClientChatRoomInfo> msg = new ClientMsg<>(MsgClientChatRoomInfo.class);

        msg.getBody().setSteamIdChat(new SteamID(123));
        msg.getBody().setType(EChatInfoType.InfoUpdate);

        return msg.serialize();
    }

    private static byte[] clientChatActionResult() {
        ClientMsg<MsgClientChatActionResult> msg = new ClientMsg<>(MsgClientChatActionResult.class);

        msg.getBody().setSteamIdChat(new SteamID(123));
        msg.getBody().setChatAction(EChatAction.Ban);
        msg.getBody().setActionResult(EChatActionResult.Success);

        return msg.serialize();
    }

    private static byte[] clientChatInvite() {
        ClientMsgProtobuf<CMsgClientChatInvite.Builder> msg = new ClientMsgProtobuf<>(CMsgClientChatInvite.class, EMsg.ClientChatInvite);

        msg.getBody().setSteamIdChat(123);

        return msg.serialize();
    }

    private static byte[] clientSetIgnoreFriendResponse() {
        ClientMsg<MsgClientSetIgnoreFriendResponse> msg = new ClientMsg<>(MsgClientSetIgnoreFriendResponse.class);

        msg.getBody().setResult(EResult.OK);

        return msg.serialize();
    }

    private static byte[] clientFriendProfileInfoResponse() {
        ClientMsgProtobuf<CMsgClientFriendProfileInfoResponse.Builder> msg = new ClientMsgProtobuf<>(CMsgClientFriendProfileInfoResponse.class, EMsg.ClientFriendProfileInfoResponse);

        msg.getBody().setEresult(EResult.OK.code());

        return msg.serialize();
    }

    private static byte[] clientPersonaChangeResponse() {
        ClientMsgProtobuf<CMsgPersonaChangeResponse.Builder> msg = new ClientMsgProtobuf<>(CMsgPersonaChangeResponse.class, EMsg.ClientPersonaChangeResponse);

        msg.getBody().setResult(EResult.OK.code());

        return msg.serialize();
    }

    private static byte[] clientPlayerNicknameList() {
        ClientMsgProtobuf<CMsgClientPlayerNicknameList.Builder> msg = new ClientMsgProtobuf<>(CMsgClientPlayerNicknameList.class, EMsg.ClientPlayerNicknameList);

        msg.getBody().addNicknames(CMsgClientPlayerNicknameList.PlayerNickname.newBuilder().setNickname("testnickname"));

        return msg.serialize();
    }

    private static byte[] aMClientSetPlayerNicknameResponse() {
        ClientMsgProtobuf<CMsgClientSetPlayerNicknameResponse.Builder> msg = new ClientMsgProtobuf<>(CMsgClientSetPlayerNicknameResponse.class, EMsg.AMClientSetPlayerNicknameResponse);

        msg.getBody().setEresult(EResult.OK.code());

        return msg.serialize();
    }

    private static byte[] clientAMGetPersonaNameHistoryResponse() {
        return loadFile("ClientAMGetPersonaNameHistoryResponse.bin");
    }

    // endregion
}