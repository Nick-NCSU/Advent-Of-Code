package AOC.Y2021.D16;

import java.util.List;

public class Packet {
    int version;
    int type;
    long literal;
    List<Packet> subPackets;
    Packet(int version, int type, long literal, List<Packet> subPackets) {
        this.version = version;
        this.type = type;
        this.literal = literal;
        this.subPackets = subPackets;
    }

    public int sumVersion() {
        int total = 0;
        for(Packet subPacket : subPackets)
            total += subPacket.sumVersion();
        return version + total;
    }

    public long getValue() {
        long num = 0;
        switch(type) {
            case 0:
                for(Packet subPacket : subPackets)
                    num += subPacket.getValue();
                break;
            case 1:
                num = 1;
                for(Packet subPacket : subPackets)
                    num *= subPacket.getValue();
                break;
            case 2:
                num = Long.MAX_VALUE;
                for(Packet subPacket : subPackets)
                    num = Math.min(subPacket.getValue(), num);
                break;
            case 3:
                for(Packet subPacket : subPackets)
                    num = Math.max(subPacket.getValue(), num);
                break;
            case 4:
                num = literal;
                break;
            case 5:
                num = subPackets.get(0).getValue() > subPackets.get(1).getValue() ? 1 : 0;
                break;
            case 6:
                num = subPackets.get(0).getValue() < subPackets.get(1).getValue() ? 1 : 0;
                break;
            case 7:
                num = subPackets.get(0).getValue() == subPackets.get(1).getValue() ? 1 : 0;
                break;
        }
        return num;
    }
}
