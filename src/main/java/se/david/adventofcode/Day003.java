package se.david.adventofcode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day003 {
    public int solve(List<String> input) {
        int answer = 0;

        Map<String, Integer> map = new HashMap<>();

        map.put(0 + ":" + 0, 1);

        for(String row: input) {
            int currentX = 0;
            int currentY = 0;

            for(char way : row.toCharArray()) {
                if(WAY.NORTH.equals(WAY.getEnumFromCode(way))) {
                    currentX++;
                } else if(WAY.SOUTH.equals(WAY.getEnumFromCode(way))) {
                    currentX--;
                } else if(WAY.EAST.equals(WAY.getEnumFromCode(way))) {
                    currentY++;
                } else if(WAY.WEST.equals(WAY.getEnumFromCode(way))) {
                    currentY--;
                }

                String coords = currentX + ":" + currentY;

                Integer currentPlace = map.get(coords);
                if(currentPlace != null){
                    map.replace(coords, currentPlace++);
                } else {
                    map.put(coords, 1);
                }
            }
        }

        answer = map.size();

        return answer;

    }

    public enum WAY {
        NORTH('^'),
        EAST('>'),
        SOUTH('v'),
        WEST('<');

        private char code;

        WAY(char code) {
            this.code = code;
        }

        public static WAY getEnumFromCode(char code) {
            WAY way = null;

            if(NORTH.code == code) {
                way = NORTH;
            } else if(EAST.code == code) {
                way = EAST;
            } else if(SOUTH.code == code) {
                way = SOUTH;
            } else if(WEST.code == code) {
                way = WEST;
            }

            return way;
        }

    }
}
