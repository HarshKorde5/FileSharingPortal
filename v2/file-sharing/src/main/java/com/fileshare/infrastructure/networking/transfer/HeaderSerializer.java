package com.fileshare.infrastructure.networking.transfer;

import com.fileshare.infrastructure.networking.protocol.JsonMapper;

public final class HeaderSerializer {

    private HeaderSerializer() {
    }

    public static String serialize(
            TransferHeader header)
            throws Exception {

        return JsonMapper.getInstance()
                .writeValueAsString(header);
    }

    public static TransferHeader deserialize(
            String json)
            throws Exception {

        return JsonMapper.getInstance()
                .readValue(
                        json,
                        TransferHeader.class);
    }
}