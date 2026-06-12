package com.fileshare.domain.models;

import java.util.Objects;
import java.util.UUID;

import com.fileshare.domain.enums.PeerRole;

public class Peer {

    private final UUID peerId;
    private final String username;
    private final String ipAddress;
    private final PeerRole role;

    public Peer(UUID peerId, String username, String ipAddress, PeerRole role) {

        this.peerId = Objects.requireNonNull(peerId, "Peer ID cannot be null.");

        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be null or empty.");
        }

        if (ipAddress == null || ipAddress.trim().isEmpty()) {
            throw new IllegalArgumentException("IP Address cannot be null or empty.");
        }

        this.role = Objects.requireNonNull(role,"Role cannot be null.");

        this.username = username.trim();
        this.ipAddress = ipAddress.trim();
    }

    public UUID getPeerId() {
        return peerId;
    }

    public String getUsername() {
        return username;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public PeerRole getRole() {
        return role;
    }
    @Override
    public String toString() {
        return "Peer{" +
                "peerId=" + peerId +
                ", username='" + username + '\'' +
                ", ipAddress='" + ipAddress + '\'' +
                ", role=" + role +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Peer peer)) return false;
        return peerId.equals(peer.peerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(peerId);
    }
}