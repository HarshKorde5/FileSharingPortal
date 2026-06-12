package com.fileshare.domain.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Room {

    private final UUID roomId;
    private final String roomCode;
    private final List<Peer> members;

    public Room(UUID roomId, String roomCode, Peer host) {

        this.roomId = Objects.requireNonNull(roomId, "Room ID cannot be null.");

        if (roomCode == null || roomCode.trim().isEmpty()) {
            throw new IllegalArgumentException("Room code cannot be null or empty.");
        }

        Objects.requireNonNull(host, "Host cannot be null.");

        this.roomCode = roomCode.trim();
        this.members = new ArrayList<>();

        addMember(host);
    }

    public UUID getRoomId() {
        return roomId;
    }

    public String getRoomCode() {
        return roomCode;
    }

    public List<Peer> getMembers() {
        return Collections.unmodifiableList(members);
    }

    public void addMember(Peer peer) {

        Objects.requireNonNull(peer, "Peer cannot be null.");

        if (!members.contains(peer)) {
            members.add(peer);
        }
    }

    public void removeMember(Peer peer) {

        Objects.requireNonNull(peer, "Peer cannot be null.");

        members.remove(peer);
    }

    public boolean containsMember(Peer peer) {
        return members.contains(peer);
    }

    public int memberCount() {
        return members.size();
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomId=" + roomId +
                ", roomCode='" + roomCode + '\'' +
                ", members=" + members.size() +
                '}';
    }
}